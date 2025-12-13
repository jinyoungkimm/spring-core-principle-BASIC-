package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("【Bean名+データタイプ】で照会")
    void findBeanByName() {

        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("【データタイプ】で照会")
    void findBeanByType1() {

        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("【実装クラス】で照会(非推奨)")
    void findBeanByType2() {
        /**
         * 実装クラスへ依存している
         * → DIP違反であるので、出来るだけ止揚
         */
        MemberServiceImpl memberService = ac.getBean("memberService",MemberServiceImpl.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("DI ContainerにないBean照会")
    void findBeanFaultCase() {
        assertThrows(NoSuchBeanDefinitionException.class,
                     () ->ac.getBean("NoSuchBeanDefinitionException",MemberService.class)
        );
    }
}
