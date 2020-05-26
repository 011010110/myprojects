package org.jiang.core.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author li.linhua
 * @Date 2019/12/31
 * @Version 1.0
 */
@Aspect
@Component
public class MyAspect {

    @Before("execution(public void save(..))")
    public void allMethod(){
        System.out.println("方法拦截！");
    }
}
