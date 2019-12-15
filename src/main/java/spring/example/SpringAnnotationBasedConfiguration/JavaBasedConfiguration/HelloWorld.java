package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class HelloWorld {

    private String message;

    //===== getter and setter =====//
    public void setMessage(String message) {
        this.message  = message;
    }

    public String getMessage() {
        System.out.println("Your Message : " + message);
        return message;
    }

}
