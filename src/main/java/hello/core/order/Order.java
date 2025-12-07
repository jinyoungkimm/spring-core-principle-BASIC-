package hello.core.order;

public class Order {

    private Long membeId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public Order(Long membeId, String itemName, int itemPrice, int discountPrice) {
        this.membeId = membeId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    /**
     * @return 割引金額が反映された商品金額
     */
    public int calculatePrice() {
        return this.itemPrice - this.discountPrice;
    }

    public Long getMembeId() {
        return membeId;
    }

    public void setMembeId(Long membeId) {
        this.membeId = membeId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "membeId=" + membeId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
