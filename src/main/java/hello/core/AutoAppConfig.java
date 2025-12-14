package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /**
         * @ComponentScan 実行時、
         * @Configuration が付与されたクラスをスキャン対象から除外する
         * ・ AppConfig や TestConfig などの設定クラスがBean として重複登録されるのを防ぐため
         * ・ 学習目的のため、設定クラスは削除せず、excludeFilters を使用して制御している
         * → 実務ではexcludeFiltersを滅多に使わずスキャン対象外を削除する
        */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
        /**
         * AppConfig と AutoAppConfig の違い
         * ・@Bean 定義や依存関係の注入を開発者が明示的に記述しない
         * ・@ComponentScan により @Component が付与されたクラスを自動検出し、
         *   Spring コンテナに Bean として自動登録する
         * ・@Autowired を使用して、@Component クラス内で依存関係を自動注入する
         * ※@Autowiredの詳細は後説明する
         */
}

/**
 *  実は、CoreApplicationの@SpringBootApplicationアノテーションがすべてのパッケージを探索し@ComponentScan機能を持っているため
 *  わざわざAutoAppConfigクラスを作成する必要はないが
 *  そうするとテスト時、@SpringBootApplicationTestによって、アプリケーション実行に必要な一度すべてローディングをしなければならないため時間コーストが掛かる
 *  → だからAutoAppConfigクラスを作成して、JAVAコードでテストした。
 */
