package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

    @Test
    @DisplayName("Spring Containerとシングルトン")
    void springContainer() {

        /**
         * 【Spring Containerが代わりにすべてのBeanをシングルトンで保持】
         * ・直接シングルトンのためのコード作成が不要になる
         * → MemberServiceImplクラスは実装クラスに依存していない(DIP遵守)
         *
         * ・MemberServiceImplクラスの依存関係注入のために【シングルトン.getInstance()】コードが不要になる
         * → OCP遵守
         *
         * ・テストコード作成が容易になる
         * → DIP遵守により他の実装クラス注入できるようになり、多様な依存関係のテストケースが作れる
         */

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // クライアントAのリクエスト → オブジェクト生成
        MemberService memberServiceA = ac.getBean("memberService",MemberService.class);

        // クライアントBのリクエスト → オブジェクト生成
        MemberService memberServiceB = ac.getBean("memberService",MemberService.class);

        // memberServiceA == memberServiceB
        assertThat(memberServiceA).isSameAs(memberServiceB);
    }
}