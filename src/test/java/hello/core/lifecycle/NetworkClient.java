package hello.core.lifecycle;

// jakarataパッケージ : JAVA標準のパッケージ
// Spring Framework 6 / Spring Boot 3以降は"javax"使用は禁止されて、"jakarta"というパッケージ名を強制するようになった
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("コンストラクタ呼び出し、URL : " + url);
    }

    /**
     * @PostConstruct, @PreDestroy : Springで推奨する方法
     * →どれも純粋JAVA標準アノテーション
     * ・唯一の短所
     * 外部ライブラリに適用不可能
     * →外部ライブラリはそもそもコードの変更が不可能だったことを思い出そう。よってもしNetWorkClinetが外部ライブラリの場合@PostConstruct, @PreDestoryをコードに追加しなければならない
     * が外部外部ライブラリであるため不可能。
     * 方針としては@PostConstruct, @PreDestroyをベースとしてもし初期化・消滅メソッドを使わねばならない時は@Bean(initMethod, DestoryMethod)を使えばよい
     */
    @PostConstruct
    public void init() {
        connect();
        call("初期化メッセージ");
    }

    @PreDestroy
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