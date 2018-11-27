package lab.ride.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lab.ride.dto.User;
import lab.ride.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cwz
 * @date 2018/11/25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User create(@Valid @RequestBody User user, BindingResult errors) throws Exception {
//        if(StringUtils.isBlank(user.getPassword())){
//            throw new Exception("no password");
//        }
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "hhh")
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable page){
        System.out.println(ReflectionToStringBuilder.toString(condition));
        System.out.println(page.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }


    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户id") @PathVariable String id){
//        throw new UserNotExistException("1");
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    @JsonView(User.UserSimpleView.class)
    public User update(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError)error;
                System.out.println(fieldError.getField() + ":" + fieldError.getDefaultMessage());
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public String delete(@PathVariable String id){
        System.out.println(id);
        return id;
    }
}
