package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    /**
     * setterによる注入の場合、Springフレームワークなしでテストコード作成時どんな問題が出るか確認
     */
    @Test
    void createOrderFail() {

        OrderServiceImpl orderService = new OrderServiceImpl();
        // NullPointerException発生
        Assertions.assertThrows(NullPointerException.class,
                () -> orderService.createOrder(new Member(1L,"jyk", Grade.VIP),"itemA", 10000)
        );
        /**
         * エラーの原因 : OrderServiceImplの依存関係注入をしていない
         * ・createOrderFailメソッドを見ただけではOrderServiceImplにどんな依存関係を注入すべきか見えない
         * →開発者がOrderServiceImplクラスを探って直接確認しなければならない煩雑さがある
         * →開発者がsetterによる依存関係注入を忘れる場合がある
         * →開発者がsetterによって依存関係を注入したとしても、注入の漏れがあるかもしれない
         */
    }
}