package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();
        /**
         * ・ApplicationContext = Spring Container(IoC,DI Container)
         * ・new AnnotationConfigApplicationContext(AppConfig)の時、コンパイラーエラー発生の理由
         * 　・AppConfigはただのデータタイプであり、値ではないのでコンストラクタのパラメーターになり得ない
         * 　・AppConfig.classはクラスのメタデータを格納した”Class オブジェクト”である
         * 　　→ 下記はSpring Containerのコンストラクタである
         * 　　public AnnotationConfigApplicationContext(Class<?>... annotatedClasses)
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "jyk", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member, "キムのスプリング講義", 12000);
        System.out.println("order = " + order);

    }
}
