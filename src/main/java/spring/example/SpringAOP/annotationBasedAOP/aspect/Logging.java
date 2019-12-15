package spring.example.SpringAOP.annotationBasedAOP.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志管理切面
 *
 * 自定义切面需要在类上面打上两个注解
 *
 * @Aspect注解 用于标识这个类是一个自定义切面
 * @Component注解 用于将此类交给 Spring 管理
 */
@Aspect
@Component
public class Logging {

    /** Following is the definition for a pointcut to select
     *  all the methods available. So advice will be called
     *  for all the methods.
     */
    @Pointcut("execution(* spring.example.SpringAOP.model.*.*(..))")
    private void selectAll() {}

    /**
     * This is the method which I would like to execute
     * before a selected method execution.
     */
    @Before("selectAll()")
    public void beforeAdvice() {
        System.out.println("[Before Advice] Going to setup student profile.");
    }

    /**
     * This is the method which I would like to execute
     * after a selected method execution.
     */
    @After("selectAll()")
    public void afterAdvice() {
        System.out.println("[After Advice] Student profile has been setup.");
    }

    /**
     * This is the method which I would like to execute
     * when any method returns.
     */
    @AfterReturning(pointcut = "selectAll()", returning = "retVal")
    public void afterReturningAdvice(Object retVal) {
        System.out.println("[After Returning Advice] Returning -> "
                + (null != retVal ? retVal.toString() : null));
    }

    /**
     * This is the method which I would like to execute
     * if there is an exception raised.
     */
    @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
    public void afterThrowingAdvice(IllegalArgumentException ex) {
        System.out.println("[After Throwing Advice] There has been an exception -> "
                + ex.toString());
    }

    /**
     * This is the method which I would like to execute
     * around a selected method execution.
     */
    /*@Around("selectAll()")
    public void aroundAdvice() {
        System.out.println("[Around Advice] A advice around student profile.");
    }*/

}
