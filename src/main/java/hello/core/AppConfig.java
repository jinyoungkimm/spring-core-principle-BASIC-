package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() { // 役割
        return new MemberServiceImpl(memberRepository()); // 実装
    }

    @Bean
    public OrderService orderService() {// 役割
//        return new OrderServiceImpl(// 実装
//                memberRepository(),
//                discountPolicy()
//        );
    return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {// 役割
        // return new FixDiscountPolicy();// 実装
        return new RateDiscountPolicy();// 新しい割引政策定期用
    }

    @Bean
    public MemberRepository memberRepository() {// 役割
        return new MemoryMemberRepository();// 実装
    }
}
