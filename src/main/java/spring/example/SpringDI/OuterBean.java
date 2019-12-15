package spring.example.SpringDI;

import org.springframework.beans.factory.annotation.Autowired;

public class OuterBean {

    private InnerBean innerBean;

    private OuterBean() {
        System.out.println("Inside OuterBean constructor.");
    }

    public void showInnerBean() {
        System.out.println("showInnerBean():");
        innerBean.show();
    }

    //===== getter and setter =====//
    public InnerBean getInnerBean() {
        return innerBean;
    }

    // Setter 方法中的 @Autowired
    @Autowired
    public void setInnerBean(InnerBean innerBean) {
        this.innerBean = innerBean;
    }

}
