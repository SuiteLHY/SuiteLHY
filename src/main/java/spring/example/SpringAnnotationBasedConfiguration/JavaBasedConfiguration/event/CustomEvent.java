package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.event;

import org.springframework.context.ApplicationEvent;

/**
 * Spring 自定义事件类
 */
public class CustomEvent extends ApplicationEvent {

    // 通过 ApplicationEvent 拓展的事件类必须定义一个
    //-> 默认的构造函数, 这个构造函数应该使用从
    //-> ApplicationEvent 类中继承的构造函数。
    public CustomEvent(Object source) {
        super(source);
    }

    public String toString(){
        return "My Custom Event";
    }

}
