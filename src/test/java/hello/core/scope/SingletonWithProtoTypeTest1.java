package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    static class ClientBeanTemp {

        private final AnnotationConfigApplicationContext ac; // DIP違反

        ClientBeanTemp(AnnotationConfigApplicationContext ac) {
            this.ac = ac;
        }

        public int logic() {
            ProtoTypeBean protoTypeBean = ac.getBean(ProtoTypeBean.class); // logic()が呼び出される度に新しいprotoTypeBeanオブジェクト生成。
            protoTypeBean.addCount();
            int count = protoTypeBean.getCount();
            return count;
        }
    }

    @Test
    void singletonWithProtoType2() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanTemp.class, ProtoTypeBean.class);
        ClientBeanTemp bean1 = ac.getBean(ClientBeanTemp.class);
        int count1 = bean1.logic(); // 新しいprotoTypeBean生成
        assertThat(count1).isEqualTo(1);

        ClientBeanTemp bean2 = ac.getBean(ClientBeanTemp.class);
        int count2 = bean2.logic(); // また新しいprotoTypeBean生成
        assertThat(count2).isEqualTo(1);
    }
}