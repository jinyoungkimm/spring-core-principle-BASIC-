package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy") // 主に、使う割引政策という意味で"main"と名付けた。
                                 // もし主な割引政策がFixDiscountPolicyの場合はそのクラスの@Qqualifier("mainDiscountPolixy")に変更すれば良い。
public class RateDiscountPolicy implements DiscountPolicy{
    private final int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
