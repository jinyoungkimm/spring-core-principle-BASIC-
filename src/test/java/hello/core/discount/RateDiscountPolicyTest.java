package hello.core.discount;

import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIPである場合１０割の割引が適用される")
    void vip_o() {
        //given
        Member member = new Member(1L, "jyk", Grade.VIP);

        //when
        int discountPrice = discountPolicy.discount(member, 12000);

        //then
        assertThat(discountPrice).isEqualTo(1200);
    }

    @Test
    @DisplayName("VIPでない場合１０割の割引が適用されない")
    void vip_x() {
        //given
        Member member = new Member(1L, "jyk", Grade.BASIC);

        //when
        int discountPrice = discountPolicy.discount(member, 12000);

        //then
        assertThat(discountPrice).isNotEqualTo(1200);
    }
}