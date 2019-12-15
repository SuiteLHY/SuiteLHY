package spring.example.SpringIoC;

public class HelloChina {

    private String message1;

    private String message2;

    private String message3;

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

    public void setMessage3(String message3){
        this.message3 = message3;
    }

    public String getMessage3(){
        return message3;
    }

    public void showMessage3(){
        System.out.println("Your Message3 : " + message3 + "\n");
    }

}
