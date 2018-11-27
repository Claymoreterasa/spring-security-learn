package lab.ride.validator;

import lab.ride.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author cwz
 * @date 2018/11/26
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o != null) {
            helloService.greeting(o.toString());
        }
        return true;
    }
}
