package hello.core.autoscan;

import hello.core.AutoAppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class AutoConfigTest {

    @Test
    void basicScan() {
        /**
         * new Annot....ationContext(AutoAppConfig.class)で下記のエラーメッセージが発生する
         * No qualifying bean of type 'hello.core.discount.DiscountPolicy' available: expected single matching bean but found 2: fixDiscountPolicy,rateDiscountPolicy
         * →@Autowiredは "ac.getBean(DiscountPolicy.class)"のようにデータタイプでBeanを照会するのでDiscountPolicyを継承するBeanが二つ存在するので二つのうちどれを
         * 注入すれば良いかが分からない。
         * 注意点
         * @ComponentScanで自動スキャンを使わず、@Beanによる手動で登録して解決も出来るが、自動スキャンを維持しながら解決する方法がある。
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}