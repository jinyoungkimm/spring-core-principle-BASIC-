package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("コンストラクタ呼び出し、URL : " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("初期化メッセージ");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient destroy()");
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