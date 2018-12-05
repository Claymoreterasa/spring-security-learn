package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cwz
 * @date 2018/11/24
 */
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TestApplication.class, args);
        Object o = context.getBean("getBean");
        System.out.println(o);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }
}
