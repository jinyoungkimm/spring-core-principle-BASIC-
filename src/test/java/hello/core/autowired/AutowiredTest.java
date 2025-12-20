package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

class AutowiredTest {

    @Test
    void setNoBean() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    /**
     * @Autowiredによって、注入されるBeanがSpring Containerにない場合(MemberクラスはSpring Containerに登録されない)
     * ・required = false : 登録Beanがない場合, エラーを出してメソッド自体を実行させない
     * ・@Nullalbe        : 登録Beanがない場合、エラー出さずメソッドを実行させる
     * ・Optional<>       : JAVA8バージョンからサポートするAPIでパラメーターがnullでもExceptionを投げず、メソッドを実行させる
     * → @Nullable, Optionalはアプリケーションコードで有用に使用される。例えば、コンストラクタ注入時、特定のパラメーターに @Nullable, Optionalを
     * 付けることで依存関係を選択的に注入出来る。
     */

    static class TestBean {

        @Autowired(required = false)
        public void setBean1(Member Bean1) {// 実行されない
            System.out.println("Bean1 = " + Bean1);
        }

        @Autowired
        public void setBean2(@Nullable Member Bean2) {// 実行される
            System.out.println("Bean2 = " + Bean2);
        }

        @Autowired
        public void setBean3(Optional<Member> Bean3) {// 実行される
            System.out.println("Bean3 = " + Bean3);
        }
    }
}
