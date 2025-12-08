package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
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
        Member member = new Member(1L, "jyk", Grade.BASIC);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member);
        System.out.println("findMember = " + findMember);

    }
}
