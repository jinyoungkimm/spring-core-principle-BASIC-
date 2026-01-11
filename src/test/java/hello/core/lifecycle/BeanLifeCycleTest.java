package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // Spring Containerを終了させる機能は滅多に使わないため、ApplicationContextインターフェースで宣言されて
                    // なくて、実装クラスAnnotationConfigApplicationContextでclose()が実装されている
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close") // Beanではなく設定ファイルで初期化・消滅メソッドを指定
        NetworkClient networkClient() {                     // ・外部ライブラリのクラスであっても初期化・消滅メソッドスペックを確認して設定ファイルで指定すれば
                                                            // 初期化・消滅メソッドを実行させることが出来るようになった
                                                            // destroyMethodのデフォルトはdestroyMethod＝"(inferred)"(推論)である
                                                            // 外部ライブラリの消滅メソッド名はほとんどclose(), shutdown()になっている。そこでSpringはdestroyMethodを指定しなくても、
                                                            // 外部ライブラリにあるclose(),shutdown()を探して自動で呼び出す
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://hello-spring.dev");
            return networkClient;
        }
    }
}
