package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.event;

import org.springframework.context.ApplicationListener;

/**
 * Spring 自定义事件操作类
 */
public class CustomEventHandler implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(event.toString());
    }

}
