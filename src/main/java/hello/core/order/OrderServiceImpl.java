package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscounPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /**
     * 多態性により役割と実装を分離したが二点の問題点が存在
     * ・DIP違反 : インターフェースに依存しているが同時に実装体にも依存
     * ・OCP違反 : 機能の拡張と共にクライアント側のコード変更も発生
     * →純粋なJAVAだけでは多態性は守れない(この問題は段階的に解決していく予定、最終的にはSpringのDI Containerを使って解決)
     */
    //private final DiscountPolicy discountPolicy = new FixDiscounPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Override
    public Order createOrder(Member member, String itemName, int itemPrice) {

        Member findMember = memberRepository.findById(member.getId());
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        return new Order(member.getId(), itemName,itemPrice,discountPrice);
    }
}
