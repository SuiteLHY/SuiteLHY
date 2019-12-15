package spring.example.SpringAnnotationBasedConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @PostConstruct 和 @PreDestroy 注释 - 使用示例
 */
public class HelloWorld {

    private String message;

    @PostConstruct
    public void init() {
        System.out.println("Bean is going through init.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean will destroy now.");
    }

    //===== getter and setter =====//
    public void setMessage(String message) {
        this.message  = message;
    }

    public String getMessage() {
        System.out.println("Your Message : " + message);
        return message;
    }

}
