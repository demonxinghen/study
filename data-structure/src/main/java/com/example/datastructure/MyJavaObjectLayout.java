package com.example.datastructure;

import lombok.Data;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author: xuh
 * @date: 2022/12/4 20:00
 * @description: ClassLayout 计算一个对象占用多少字节<br>
 * 首先一个对象的总字节数一定是8的倍数，对象头12字节(markword8字节+type类型指针4字节)+实例数据(int4字节，long8字节，引用对象4字节(对象中的数据不算)，boolean1字节)+字节填充
 */
public class MyJavaObjectLayout {

    @Data
    private static class T {
        Long e;
        long a;
        W obj = new W();
//        boolean flag;
    }

    private static class W{
        int b;
        int c;
        int d;
    }

    public static void main(String[] args) {
        T t = new T();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        System.out.println(t.getObj().b);
    }
}
