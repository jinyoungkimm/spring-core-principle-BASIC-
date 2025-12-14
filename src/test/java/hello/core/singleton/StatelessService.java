package hello.core.singleton;

/**
 * stateless設計の例
 */
class StatelessService {

    // private int price;

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        //this.price = price;
        return price;
    }
}



