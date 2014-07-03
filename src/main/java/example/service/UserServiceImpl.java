package example.service;

import example.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("userService")
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUser() {
        // TODO DI確認のため仮実装
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setName("tarou");
        user1.setAge(18);
        list.add(user1);

        User user2 = new User();
        user2.setName("hanako");
        user2.setAge(21);
        list.add(user2);

        return list;
    }
}
