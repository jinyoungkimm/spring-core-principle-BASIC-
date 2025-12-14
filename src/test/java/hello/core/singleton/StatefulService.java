package hello.core.singleton;

class StatefulService {

    private int price; // stateful

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price ; // ここで致命的なエラー発生
    }

    public int getPrice() {
        return this.price;
    }
}
