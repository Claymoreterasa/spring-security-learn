package lab.ride.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author cwz
 * @date 2018/11/26
 */
@Aspect
@Component
public class TimeAspect {
//    @Around("execution(* lab.ride.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        long start = System.currentTimeMillis();
        for(Object object : pjp.getArgs()){
            System.out.println("arg is " + object);
        }
        Object object = pjp.proceed();
        System.out.println("time aspect cost time: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("time aspect end");
        return object;
    }
}
