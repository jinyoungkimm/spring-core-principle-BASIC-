package hello.core;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


/**
 * 関心の分離(Soc : Separation Of Concerns)
 * →　各クラスの依存関係の生成・注入はAppConfigクラスが担当
 * →　それによりMemberServiceImpl, OrderServiceImpl等のクライアント側はサーバー側を気にせず、各クラス本来の本来の機能実装だけ気にすればよし
 * → 　例えば、MemberServiceImplクラスはMemberRepositoryクラスがメモリーDBであろうがRDBMSだろうが全然気にしなくてよし
 */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new RateDiscountPolicy()
        );
    }
}
