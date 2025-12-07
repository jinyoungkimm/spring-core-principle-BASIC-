package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


/**
 * ２点の問題点
 * ・アプリケーションの全体構成が把握しにくい
 * →　役割　: メソッドのリターンタイプとメソッド名で把握
 * →　実装　: メソッド内の "return new ~~"部分で把握
 * →　上記の箇所で役割と実装が明白に把握できるようにする
 * 
 * ・new MemoryMemberRepository()という重複が存在
 * →　容易な変更のため、メソッド化
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
        return new RateDiscountPolicy();// 実装
    }

    private MemberRepository memberRepository() {// 役割
        return new MemoryMemberRepository();// 実装
    }


}
