package com.example.conditional;

/**
 * @author: xuh
 * @date: 2023/5/25 10:58
 * @description:
 */
public class LinuxCondition extends BaseCondition {
    @Override
    protected boolean match(String os) {
        return os.contains("Linux");
    }
}