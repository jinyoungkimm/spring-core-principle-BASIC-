package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulServiceA = ac.getBean(StatefulService.class);
        StatefulService statefulServiceB = ac.getBean(StatefulService.class);

        //クライアントA(ThreadA): Aは10000円を注文
        statefulServiceA.order("クライアントA",10000);

        //クライアントB(ThreadB): Bは20000円を注文
        statefulServiceB.order("クライアントB",20000);

        // Aの注文金額が20000円になってしまう　→　致命的なエラー
        assertThat(statefulServiceA.getPrice()).isNotEqualTo(10000);
    }
}