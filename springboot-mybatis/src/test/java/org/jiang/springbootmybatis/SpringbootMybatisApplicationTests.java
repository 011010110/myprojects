package org.jiang.springbootmybatis;

import org.jiang.core.bean.User;
import org.jiang.core.exception.ExceptionService;
import org.jiang.core.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.lin.HelloService;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SpringbootMybatisApplicationTests {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HelloService helloService;
    @Autowired
    private ExceptionService exceptionService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserName("test");
        user.setPassword("123456");
        userMapper.save(user);
    }

    @Test
    void myStarterTest(){
        helloService.testMethod();
    }

    @Test
    void exceptionTest(){
        /*try{
            exceptionService.exceptionMethod();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("exception");
        }*/
        String method = exceptionService.exceptionMethod();
        System.out.println(method);
    }

    @Test
    void beanWrapperTest(){
        User user = new User();
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(user);
        beanWrapper.setPropertyValue("userName","beanwrapper_userName");
        beanWrapper.setPropertyValue("password","beanwrapper_password");
        Object wrappedInstance = beanWrapper.getWrappedInstance();
        System.out.println(wrappedInstance.toString());
    }
}
