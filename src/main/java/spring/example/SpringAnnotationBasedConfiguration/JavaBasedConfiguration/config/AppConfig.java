package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.config;

import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.Bar;
import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * 注入 Bean 的依赖性, 示例
 */
@Configuration
// @Import 注解引入其他注解配置类, 允许从这些类中加载 @Bean 定义
@Import({HelloWorldConfig.class, TextEditorConfig.class})
public class AppConfig {

    // @Bean 注解支持指定任意的初始化和销毁的回调方法，
    //-> 就像在 bean 元素中 Spring 的 XML 的初始化方法和销毁方法的属性
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    // @Scope 指定 Bean 的范围；
    //-> 默认范围是单实例，但是你可以重写带有 @Scope 注解的方法，
    //-> 如下所示：
    @Scope("prototype")
    public Foo foo() {
        // 注入 Bean 的依赖性
        return new Foo(bar());
    }

    @Bean
    public Bar bar() {
        return new Bar();
    }

}
