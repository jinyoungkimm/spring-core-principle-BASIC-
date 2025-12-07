package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    private MemberService memberService;
    private OrderService orderService;

    /**
     *  @BeforeEach : 各テストの実行の前に実行
     *  → 複数のテストを実行するため、MemberServiceでデータの不一致が発生する恐れが存在
     *  → 各テストごとにMemberServiceオブジェクトを新しく生成するのが望ましい
     */
    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "jyk", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(member, "キムのスプリング講義", 12000);

        //when

        //then
        assertThat(order.getDiscountPrice()).isEqualTo(1200);
    }
}
