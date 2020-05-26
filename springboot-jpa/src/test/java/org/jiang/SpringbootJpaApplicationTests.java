package org.jiang;

import org.jiang.core.bean.User;
import org.jiang.core.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class SpringbootJpaApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUserName("jpatest3");
        user.setPassword("111111");
        userService.save(user);
    }

    @Test
    void userList(){
        List<User> users = userService.findAll();
        users.forEach((user -> {
            System.out.println(user.toString());
        }));
    }

    @Test
    void pageList(){
        PageRequest pageRequest = PageRequest.of(1, 2);
        Page<User> pageUsers = userService.findAll(pageRequest);
        System.out.println("总页数:"+pageUsers.getTotalPages());
        pageUsers.forEach((user -> {
            System.out.println(user.toString());
        }));
    }
}
