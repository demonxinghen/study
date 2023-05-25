package com.example.call;

/**
 * @author: xuh
 * @date: 2023/5/25 09:26
 * @description:
 */
public class CallOneBeanService2 {

    private CallOneBeanDao callOneBeanDao;
    public CallOneBeanService2(CallOneBeanDao callOneBeanDao) {
        this.callOneBeanDao = callOneBeanDao;
    }

    public CallOneBeanDao getCallOneBeanDao(){
        return callOneBeanDao;
    }
}
