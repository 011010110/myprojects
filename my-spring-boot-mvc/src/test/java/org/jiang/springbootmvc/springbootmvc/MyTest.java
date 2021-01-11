package org.jiang.springbootmvc.springbootmvc;

import org.junit.jupiter.api.Test;


public class MyTest {

    public static void main(String[] args) {
//        contextLoads();
        idcard();
    }

    public static void contextLoads() {
        Double num = 0D;
        num += 20;
        String tt = "";
        tt.equals("hh");
        System.out.printf("num=" + num);
    }

    public static void idcard() {
        String id = "513029199311175470";
        String year = id.substring(6, 10);
        System.out.println(year);
    }
}
