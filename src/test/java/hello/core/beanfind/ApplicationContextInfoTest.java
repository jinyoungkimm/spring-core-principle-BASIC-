package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new  AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("すべてのBean出力")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "objcet = " + bean);
        }
    }

    @Test
    @DisplayName("開発者/外部ライブラリにより登録されたBeanだけ出力")
    void findApplicationBean() {

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            // BeanDefinition.ROLE_INFRASTRUCTURE : Spring内部で登録されたBean
            // BeanDefinition.ROLE_APPLICATION : 直接登録したBean(ex.開発者/外部ライブラリにより登録 )
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName + "objcet = " + bean);
            }
        }
    }
}
