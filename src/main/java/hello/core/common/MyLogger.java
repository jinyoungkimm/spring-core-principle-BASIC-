package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // HTTP Request以前まではMyLoggerを継承したProxyMyLoggerを任意的に注入することができる
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + this.uuid + "]" + "[" + this.requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        System.out.println("[" + this.uuid + "]" + "request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + this.uuid + "]" + "request scope bean close:" + this);
    }
}