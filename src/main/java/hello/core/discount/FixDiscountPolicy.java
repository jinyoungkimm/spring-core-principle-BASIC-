package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * 固定金額割引
 */
public class FixDiscountPolicy implements DiscountPolicy{

    // 1,000円
    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        // 割引対象　: VIP会員のみ
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
