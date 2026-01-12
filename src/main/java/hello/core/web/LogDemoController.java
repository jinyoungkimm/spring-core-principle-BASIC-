package hello.core.web;


import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // Springサーバーを立ち上げた時点で、ProxyMyLoggerが注入される
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {

        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL); // このタイミングで本物のMyLoggerが注入されるのではなく
                                            // ProxyMyLoggerでMyLogger.setRequestURL()を呼び出す。(Proxyパターン)
        myLogger.log("controller test");
        logDemoService.logic("testID");
        return "ok";
    }
}