package hello.core.lifecycle;

class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("コンストラクタ呼び出し、URL : " + url);
        connect();
        call("初期化メッセージ");
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