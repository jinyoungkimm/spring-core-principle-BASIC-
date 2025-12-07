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


/**
 * AppConfigの存在意味
 * →　アプリケーション全体を構成領域(AppConfig)と使用領域(MemberServiceImpl, OrderServiceImplなど)に分離
 * →　OCP遵守　: それにより新しい割引政策を適用しても、クライアント側の変更はなくAppConfigだけを変更すれば良くなった
 * →　以前は割引政策の変更のため、クライアント側(OrderServiceImpl)を変更しなければならなかった
 */
public class AppConfig {

    public MemberService memberService() { // 役割
        return new MemberServiceImpl(memberRepository()); // 実装
    }

    public OrderService orderService() {// 役割
        return new OrderServiceImpl(// 実装
                memberRepository(),
                discountPolicy()
        );
    }

    private DiscountPolicy discountPolicy() {// 役割
        // return new FixDiscountPolicy();// 実装
        return new RateDiscountPolicy();// 新しい割引政策定期用
    }

    private MemberRepository memberRepository() {// 役割
        return new MemoryMemberRepository();// 実装
    }
}
