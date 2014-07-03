package example.web;

import example.domain.User;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class RestSampleController {

    @Autowired
    private UserService userService;

    @RequestMapping("/rest")
    List<User> home() {
        return userService.getUser();
    }
}
