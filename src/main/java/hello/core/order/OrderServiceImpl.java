package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {
    /**
     * DIP遵守 : インターフェースだけに依存
     * → fianlでもコンストラクタにより初期化すれば、コンパイラーエラーは発生しない
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * コンストラクタインジェクション
     */
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Member member, String itemName, int itemPrice) {

        Member findMember = memberRepository.findById(member.getId());
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        return new Order(member.getId(), itemName,itemPrice,discountPrice);
    }

    //　シングルトンのテスト用
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}
