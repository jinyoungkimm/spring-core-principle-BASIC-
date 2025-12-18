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
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }

    @Test
    void filedInjection() {
        /**
         * SPringによって注入されたMemberRepositoryではなくMockオブジェクトでテスト
         */
        OrderServiceImpl orderService = new OrderServiceImpl(); // Springを使ってない純粋なJAVAコード

        // Springフレームワークを使ってないので当然依存関係注入はされず、実行するとNullPointerExceptionエラー発生
        orderService.createOrder(new Member(1L,"jyk", Grade.VIP),"itemA",1000);
    }
}