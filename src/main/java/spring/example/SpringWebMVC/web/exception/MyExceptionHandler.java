package spring.example.SpringWebMVC.web.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 定制化异常信息处理类
 */
@Component
public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(javax.servlet.http.HttpServletRequest request
            , javax.servlet.http.HttpServletResponse response
            , Object handler
            , Exception ex) {
        //--- 可以针对异常类型使用状态机
        // 获取异常对象的堆栈跟踪信息
        //-> 该解决方案参考自 <a href="https://stackoverflow.com/questions/1149703/how-can-i-convert-a-stack-trace-to-a-string">
        //-> 【Stack Overflow】How can I convert a stack trace to a string?</a>
        StringWriter exStringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(exStringWriter));
        request.setAttribute("ex", ex);
        request.setAttribute("exStackTrace", exStringWriter.toString());
        System.out.println("异常已经被处理了");
        System.out.println("异常的类型是:" + ex.getClass().getName());
        return new ModelAndView("runtimeExceptionPage");
    }

}
