package spring.example.SpringDI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring/example/SpringDI/Beans.xml");
        System.out.println("=====");

        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();
        System.out.println("=====");

        ExampleBean exampleBean = (ExampleBean) context.getBean("exampleBean");
        exampleBean.hello();
        System.out.println("=====");

        OuterBean outerBean = (OuterBean) context.getBean("outerBean");
        outerBean.showInnerBean();
        System.out.println("=====");

        AddressCollection addressCollection = (AddressCollection) context.getBean("addressCollection");
        addressCollection.getAddressNameList();
        addressCollection.getAddressNameSet();
        addressCollection.getAddressInfoMap();
        addressCollection.getAddressProperties();
        addressCollection.getAddressList();
        addressCollection.getAddressSet();
        addressCollection.getAddressMap();
        System.out.println("=====");
    }

}
