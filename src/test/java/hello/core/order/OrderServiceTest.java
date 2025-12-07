package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    private final MemberService memberService = new MemberServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();


    @Test
    void createOrder(){
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "jyk", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(member, "キムのスプリング講義", 100000);

        //when

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
