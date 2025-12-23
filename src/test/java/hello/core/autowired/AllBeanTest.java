package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AllBeanTest {

    @Test
    void findAllBean() {
        // AnnotationConfigApplicationContextのコンストラクタの引数は二つ以上渡せる(memo参照)
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "jyk", Grade.VIP);

        int fixDiscountPolicy = discountService.discount(member,20000,"fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixDiscountPolicy).isEqualTo(1000);

        int rateDiscountPolicy = discountService.discount(member,20000,"rateDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(rateDiscountPolicy).isEqualTo(2000);

    }

    /**
     * 同じデータタイプのBeanが二つ以上必要な時、Map<>, List<>を使うとSpringは該当データタイプのすべてのBeanを変換
     */
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap; // String : Bean名、DiscountPolicy : Spring ContainerにあるすべてのDiscountPolicy実装クラス
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}