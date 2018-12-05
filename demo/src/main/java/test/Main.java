package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cwz
 * @date 2018/11/30
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        Object bean1 = context.getBean("getBean");

        System.out.println(bean1);
        Object bean2 = context.getBean("getBean");
        System.out.println(bean2);

    }
}
