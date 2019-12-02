package github.com.SuiteLHY.thinkingInJava;

public class DemoUtils {
    public static final void show(Object arg) {
        if (null != arg) {
            String content = (arg instanceof String) ? (String) arg : arg.toString();
            System.out.println(content);
        }else{
            System.out.println();
        }
    }
}
