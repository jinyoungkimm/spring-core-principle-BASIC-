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
class AutoAppConfiguration {}
