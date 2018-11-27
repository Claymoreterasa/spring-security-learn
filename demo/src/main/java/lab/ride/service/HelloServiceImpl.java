package lab.ride.service;

import org.springframework.stereotype.Service;

/**
 * @author cwz
 * @date 2018/11/26
 */
@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public String greeting(String name) {
        System.out.println("greeting " + name);
        return "hello " + name;
    }
}
