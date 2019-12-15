package spring.example.SpringAOP.xmlBasedAOP.aspect;

public class Logging {

    /**
     * This is the method which I would like to execute
     * before a selected method execution.
     */
    public void beforeAdvice() {
        System.out.println("[Before Advice] Going to setup student profile.");
    }

    /**
     * This is the method which I would like to execute
     * after a selected method execution.
     */
    public void afterAdvice() {
        System.out.println("[After Advice] Student profile has been setup.");
    }

    /**
     * This is the method which I would like to execute
     * when any method returns.
     */
    public void afterReturningAdvice(Object retVal) {
        System.out.println("[After Returning Advice] Returning -> "
                + (null != retVal ? retVal.toString() : null));
    }

    /**
     * This is the method which I would like to execute
     * if there is an exception raised.
     */
    public void afterThrowingAdvice(IllegalArgumentException ex) {
        System.out.println("[After Throwing Advice] There has been an exception -> "
                + ex.toString());
    }

    /**
     * This is the method which I would like to execute
     * around a selected method execution.
     */
    /*public void aroundAdvice() {
        System.out.println("[Around Advice] A advice around student profile.");
    }*/

}
