package test;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author cwz
 * @date 2018/11/30
 */
@Component
public class BeanTest {
    @Bean
    public  BeanTest getBean(){
        BeanTest bean = new  BeanTest();
        System.out.println("调用方法："+bean);
        return bean;
    }

}
