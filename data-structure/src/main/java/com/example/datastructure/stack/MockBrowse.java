package com.example.datastructure.stack;

/**
 * @author: xuh
 * @date: 2022/12/8 21:40
 * @description: 模拟浏览器前进后退,模拟失败,大致就是需要两个栈,一个存放可前进,一个存放可后退,如果后退之后,访问了新的站点,则可前进的栈清空
 */
public class MockBrowse {

    static MyStack<String> forward = new MyStack<>();
    static MyStack<String> back = new MyStack<>();

    public static void main(String[] args) {
        access("www.baidu.com");
        access("www.google.com");
        access("www.bing.com");
        back();
    }

    /**
     * 访问一个网站
     */
    private static void access(String webSite){
        System.out.println("访问了网站" + webSite);
        back.push(webSite);
        if (back.size() == 1){
            System.out.print("可后退至：[]");
            System.out.print(",可前进至：" + forward);
            System.out.println();
            return;
        }
        System.out.print("历史记录：" + back);
        System.out.print(",可前进至：" + forward);
        System.out.println();
    }

    /**
     * 后退
     */
    private static void back(){
        if (back.size() <= 1){
            System.out.println("没得后退");
            return;
        }
        String webSite = back.pop();
        System.out.println("后退访问了" + webSite);
        forward.push(webSite);
        System.out.print("历史记录：" + back);
        System.out.print(",可前进至：" + forward);
        System.out.println();
    }
}
