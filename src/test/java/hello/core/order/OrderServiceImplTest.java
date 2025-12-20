package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    /**
     * コンストラクタ注入の場合、setterによる注入で起こり得る問題を未然にコンパイラータイムで知らせるくれる
     */
    @Test
    void createOrderSuccess() {

        MemberRepository memberRepository = new MemoryMemberRepository();
        Member jyk = new Member(1L, "jyk", Grade.VIP);
        memberRepository.save(jyk);

        // 依存関係注入に漏れがある場合、コンパイラーエラーを出す
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(jyk, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}