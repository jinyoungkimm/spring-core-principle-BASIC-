package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ConfigurationSingletonTest {

    @Test
    void annotationConfigurationTest() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        // SpringがMemoryMemberRepositoryをシングルトンで登場したのかテスト
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // memberRepository1 == memberRepository2
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    }

    @Test
    void configurationDeep() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig app = ac.getBean(AppConfig.class);

        System.out.println("AppConfigクラス情報 : " + app.getClass());
        // 出力結果 : AppConfigクラス情報 : class hello.core.AppConfig$$SpringCGLIB$$0
        // → AppConfig名の後ろに【SpringCGLIB】というのが付いている。我々が登録したのはAppConfigであってAppConfig$$SpringCGLIBではない。
        // 一体何が起きたのだろうか。結論から言うとSpringは＠Configurationが付いてクラスCGLIBというバイトコード操作ライブラリを使って継承して
        // シングルトンを担保する(詳細はmemo参照)
    }
}
