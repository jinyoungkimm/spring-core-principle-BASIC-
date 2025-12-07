package hello.core.order;

import hello.core.member.Member;

public interface OrderService {

    /**注文処理メソッド
     * @param member : 注文する会員情報
     * @param itemName　:　注文商品名
     * @param itemPrice : 注文商品の金額
     * @return 最終注文内容
     */
    Order createOrder(Member member, String itemName, int itemPrice);

}
