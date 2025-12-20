package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    /**
     * DIP遵守 : インターフェースだけに依存
     * → fianlでもコンストラクタにより初期化すれば、コンパイラーエラーは発生しない
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired private DiscountPolicy rateDiscountPolicy; // field名で依存関係注入
    /**
     * lomBokの@RequiredArgsConstructorによりコンストラクタコードの省略(最近のトレンドである)
     * →　コードがとてもシンプルになった
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {//パラメーター名名で依存関係注入
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(Member member, String itemName, int itemPrice) {

        Member findMember = memberRepository.findById(member.getId());
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        return new Order(member.getId(), itemName,itemPrice,discountPrice);
    }

    //　テスト用
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}
