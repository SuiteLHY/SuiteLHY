package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.config;

import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.event.CStartEventHandler;
import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.event.CStopEventHandler;
import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.event.CustomEventHandler;
import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.event.CustomEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring IoC容器事件操作 Bean 的配置类
 */
@Configuration
public class EventHandlerConfig {

    @Bean
    public ApplicationListener cStartEventHandler() {
        return new CStartEventHandler();
    }

    @Bean
    public ApplicationListener cStopEventHandler() {
        return new CStopEventHandler();
    }

    @Bean
    public ApplicationListener customEventHandler() {
        return new CustomEventHandler();
    }

    @Bean
    public CustomEventPublisher customEventPublisher() {
        return new CustomEventPublisher();
    }

}
