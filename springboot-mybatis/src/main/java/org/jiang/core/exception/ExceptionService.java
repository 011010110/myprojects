package org.jiang.core.exception;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author li.linhua
 * @Date 2019/12/30
 * @Version 1.0
 */
@Service
public class ExceptionService {


    @Transactional
    public String exceptionMethod() {
        try {
            String[] array = {"1"};
            String index1 = array[0];
            String index2 = array[1];
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" index error! ");
            return "error";
        }
    }

    public void exceptionNoCatch() {
        String[] array = {"1"};
        String index1 = array[0];
        String index2 = array[1];
    }
}
