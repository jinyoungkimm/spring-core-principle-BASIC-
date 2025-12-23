package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy // @Qualifer("maaaainDiscountPolicy") のように文字列を使うと、
                    // タイプミスがあってもコンパイル時にはエラーにならない
                    // このようなタイプミスによるランタイムエラーを防ぐために、
                    // @MainDiscountPolicy というカスタムアノテーションを作成する。
                    // もし @MaaaaainDiscountPolicy のようにアノテーション名を誤って記述した場合、
                    // 参照対象にそういうアノテーションがないためコンパイル時にエラーとして検出される。
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
