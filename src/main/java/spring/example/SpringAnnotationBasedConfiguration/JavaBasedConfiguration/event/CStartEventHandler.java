package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * Spring 内置事件 —— ContextStartedEvent 监听事件的操作管理
 */
public class CStartEventHandler
        implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("ContextStartedEvent Received");
    }

}
