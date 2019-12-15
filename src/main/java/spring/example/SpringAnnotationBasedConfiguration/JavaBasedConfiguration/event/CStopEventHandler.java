package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * Spring 内置事件 —— ContextStoppedEvent 监听事件的操作管理
 */
public class CStopEventHandler
        implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println("ContextStoppedEvent Received");
    }

}
