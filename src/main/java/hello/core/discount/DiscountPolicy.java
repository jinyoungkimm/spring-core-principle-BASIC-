package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**割引金額の処理メソッド
     * @param member 会員情報
     * @param price　商品金額
     * @return 割引金額
     */
    int discount(Member member, int price);
}
