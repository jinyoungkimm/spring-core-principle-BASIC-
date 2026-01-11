package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonWithProtoTypeTest1 {


    @Test
    void protoTypeFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        protoTypeBean1.addCount();
        assertThat(protoTypeBean1.getCount()).isEqualTo(1);


        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);
        protoTypeBean2.addCount();
        assertThat(protoTypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonWithProtoType1() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, ProtoTypeBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic(); // protoTypeBean.addCount()呼び出し
        assertThat(count1).isEqualTo(1);

        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic(); // protoTypeBean.addCount()呼び出し
        assertThat(count2).isEqualTo(2);
    }

    @Scope("singleton")
    static class ClientBean {

        private final ProtoTypeBean protoTypeBean; // 一回だけ依存関係注入が行われるので、シングルトンBean生成時一回だけprotoTypeBeanが注入される
                                                   // よってlogic()が実行される度に同じprotoTypeBeanのaddCount()が実行される。
        @Autowired
        ClientBean(ProtoTypeBean protoTypeBean) {
            this.protoTypeBean = protoTypeBean;
        }

        public int logic() {
            protoTypeBean.addCount();
            int count = protoTypeBean.getCount();
            return count;
        }
    }


    @Scope("prototype")
    static class ProtoTypeBean {

        private int count = 0;

        public void addCount() {
            this.count++;
        }

        public int getCount() {
            return this.count;
        }

        @PostConstruct
        public void init() {
            System.out.println("ProtoTypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("ProtoTypeBean.destroy" + this);
        }
    }


    /**
     * 我々はlogic()が実行される度に新しく注入されたprotoTypeBeanのaddCount()を実行したい。
     * →しかし、SingleTonは生成時依存関係注入が一回しか起こらないので上のコードでは不可能だ
     * ※無理やり下の方法で思い通り実行させることが可能ではあるが立派にDIPを違反しているので望ましくないコードである。
     */

    @Scope("singleton")
    static class ClientBeanTemp1 {

        private final ApplicationContext ac; // 訂正) Spring Containerへの依存問題発生　→　純粋なJAVAテストコード作成不可能等

        ClientBeanTemp1(ApplicationContext ac) {
            this.ac = ac;
        }

        public int logic() {
            ProtoTypeBean protoTypeBean = ac.getBean(ProtoTypeBean.class); // Dependency LookUp(DL) : このように外部(Spring Containerなど)から自動的に外部から
            protoTypeBean.addCount();                                      // 依存関係を注入してもらうのではなく、開発者が必要な依存関係注を直接探して注入すること。
            int count = protoTypeBean.getCount();
            return count;
        }
    }

    @Test
    void singletonWithProtoType2() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanTemp1.class, ProtoTypeBean.class);
        ClientBeanTemp1 bean1 = ac.getBean(ClientBeanTemp1.class);
        int count1 = bean1.logic(); // 新しいprotoTypeBean生成
        assertThat(count1).isEqualTo(1);

        ClientBeanTemp1 bean2 = ac.getBean(ClientBeanTemp1.class);
        int count2 = bean2.logic(); // また新しいprotoTypeBean生成
        assertThat(count2).isEqualTo(1);
    }

    /**
     * ObjectProvider, ObjectFactory : DL機能を提供するSpring API
     * →指定した依存関係のBeanを開発者がSpring Containerで直接探すのではく、代わりSpring Containerから探して注入する
     * ※今回の場合はprotoType Beanを注入するから「探す」という表現ようりはSpring Containerに新しいprotoType Beanの生成を依頼して注入するという理解が正しい。
     */

    @Scope("singleton")
    static class ClientBeanTemp2 {

        private final ObjectProvider<ProtoTypeBean> protoTypeBeanProvier;

        @Autowired
        ClientBeanTemp2(ObjectProvider<ProtoTypeBean> protoTypeBeanProvier) {
            this.protoTypeBeanProvier = protoTypeBeanProvier; // ObjectProviderがSpring ContainerでBeanを自動的に探して注入する
        }

        public int logic() {
            ProtoTypeBean protoTypeBean = protoTypeBeanProvier.getObject(); // 依存関係注入
            protoTypeBean.addCount();
            int count = protoTypeBean.getCount();
            return count;
        }
    }

    @Test
    void singletonWithProtoType3() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanTemp2.class, ProtoTypeBean.class);
        ClientBeanTemp2 bean1 = ac.getBean(ClientBeanTemp2.class);
        int count1 = bean1.logic(); // 新しいprotoTypeBean生成
        assertThat(count1).isEqualTo(1);

        ClientBeanTemp2 bean2 = ac.getBean(ClientBeanTemp2.class);
        int count2 = bean2.logic(); // また新しいprotoTypeBean生成
        assertThat(count2).isEqualTo(1);
    }

    /**
     * DL機能を使うより、相変わらずSpringへ依存はしているが(ObjectProvider, ObjectFactory), Spring Container機能を最小限だけ使うので
     * 以前と比べて相対的にテストコードの作成がしやすくなった。
     * →しかし、Springへの依存は残っているのでテストコード作成制限などの制約が残ってはいる。
     * このために誕生したのが次回学習するjakarta.inject.ProviderというJSR-330 JAVA標準 APIを使うことである。
     */
}