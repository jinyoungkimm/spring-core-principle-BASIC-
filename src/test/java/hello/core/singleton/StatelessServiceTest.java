package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.assertj.core.api.Assertions.assertThat;

class StatelessServiceTest {

    @Configuration
    static class TestConfig {
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }

    @Test
    void statelessService() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statefulServiceA = ac.getBean(StatelessService.class);
        StatelessService statefulServiceB = ac.getBean(StatelessService.class);

        //クライアントA(ThreadA): Aは10000円を注文
        int priceA = statefulServiceA.order("クライアントA",10000);

        //クライアントB(ThreadB): Bは20000円を注文
        int priceB = statefulServiceB.order("クライアントB",20000);

        assertThat(priceA).isEqualTo(10000);
        assertThat(priceB).isEqualTo(20000);
    }
}