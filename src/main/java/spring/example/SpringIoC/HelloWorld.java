package spring.example.SpringIoC;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class HelloWorld
        implements InitializingBean, DisposableBean {

    private String message1;

    private String message2;

    private void init() {
        System.out.println("HelloWorld initHandling!");
    }

    //===== getter and setter =====//
    public void setMessage1(String message1){
        this.message1 = message1;
    }

    public String getMessage1(){
        return message1;
    }

    public void showMessage1(){
        System.out.println("Your Message1 : " + message1 + "\n");
    }

    public void setMessage2(String message2){
        this.message2 = message2;
    }

    public String getMessage2(){
        return message2;
    }

    public void showMessage2(){
        System.out.println("Your Message2 : " + message2 + "\n");
    }

    //===== InitializingBean & DisposableBean =====//
    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("HelloWorld destroying!");
    }

}
