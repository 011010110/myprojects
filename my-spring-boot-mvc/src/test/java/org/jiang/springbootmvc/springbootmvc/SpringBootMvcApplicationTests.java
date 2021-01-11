package org.jiang.springbootmvc.springbootmvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootMvcApplicationTests {

    @Test
    void contextLoads() {
        Double num = 0D;
        num += 20;
        System.out.printf("num=" + num);
    }

}
