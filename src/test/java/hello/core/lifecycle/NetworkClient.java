package hello.core.lifecycle;

class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("コンストラクタ呼び出し、URL : " + url);
    }

    /**
     * ・これでSpraing Bean(NetworkClient)コードにSpring Containerへの依存性がなくなった
     * ・これで初期化・消滅メソッド名を自由に変更できる
     */
    public void init() {
        connect();
        call("初期化メッセージ");
    }

    public void close() {
        System.out.println("NetworkClient close()");
        disconnect();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call :  " + url + " message: " + message);
    }

    public void disconnect() {
        System.out.println("disconnect :  " + url);
    }
}