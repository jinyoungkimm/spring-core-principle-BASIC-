package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // memberパッケージ配下が自動スキャン対象
        basePackages = "helo.core.member",

        // AutoAppConfigクラスのパッケージ配下が自動スキャン対象(package hello.coreパッケージ配下)
        basePackageClasses = AutoAppConfig.class,

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {}
/**
 * AutoAppConfigクラスのような設定ファイルはプロジェクトルートに置くことが慣例
 * → そういう理由でSpring Bootは@SpringBootApplicationの付いているCoreApplicationクラスはルートに位置づけて、CoreApplicationのパッケージ配下を自動スキャンする
 * ※　@SpringBootApplicationの内部に@ComponentScanが含まれている
 */