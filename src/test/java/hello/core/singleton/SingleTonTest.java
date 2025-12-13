package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingleTonTest {

    @Test
    @DisplayName("Springコンテナを使用しない純粋なDIコンテナ（シングルトンx）")
    void pureContainer() {

        /**
         * シングルトンではないため、
         * クライアントからのWebリクエストごとに
         * 新しいオブジェクトが生成される。
         * → ウェブアプリケーションの特徴はリクエストが頻繁に起こるということ
         */
        AppConfig appConfig = new AppConfig();

        // クライアントAのリクエスト → オブジェクト生成
        MemberService memberServiceA = appConfig.memberService();

        // クライアントBのリクエスト → オブジェクト生成
        MemberService memberServiceB = appConfig.memberService();

        // memberServiceA != memberServiceB
        assertThat(memberServiceA).isNotSameAs(memberServiceB);
    }

    @Test
    @DisplayName("シングルトンを使ったオブジェクト生成")
    void singleTonService() {

        // クライアントAのリクエスト → オブジェクト生成
        SingleTonService instanceA = SingleTonService.getInstance();

        // クライアントBのリクエスト → オブジェクト生成
        SingleTonService instanceB = SingleTonService.getInstance();

        // instanceA == instanceB
        assertThat(instanceA).isSameAs(instanceB);
    }
}