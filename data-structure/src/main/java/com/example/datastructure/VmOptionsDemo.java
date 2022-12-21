package com.example.datastructure;

import sun.misc.VM;

/**
 * 自定义vm参数,-D后面的参数可以通过System.getProperty和sun.misc.VM.getSavedProperty获取<br/>
 * 部分参数例外,不能被System.getProperty获取,因为在sun.misc.VM.saveAndRemoveProperties中remove了<br/>
 * 被remove的有以下几个属性:<br/>
 * sun.nio.PageAlignDirectMemory<br/>
 * sun.nio.MaxDirectMemorySize<br/>
 * sun.lang.ClassLoader.allowArraySyntax<br/>
 * java.lang.Integer.IntegerCache.high<br/>
 * sun.zip.disableMemoryMapping<br/>
 * sun.java.launcher.diag<br/>
 * sun.cds.enableSharedLookupCache<br/>
 */
public class VmOptionsDemo {

    public static void main(String[] args) {
        System.out.println(VmOptions.value);
        System.out.println(VmOptions.value2);
        System.out.println(VmOptions.value3);
        System.out.println(VmOptions.value4);
    }
}

class VmOptions{
    static final String value;
    static final String value2;
    static final String value3;
    static final String value4;

    static {
        value = VM.getSavedProperty("com.example.datastructure.VmOptions.value");
        value2 = System.getProperty("com.example.datastructure.VmOptions.value");
        value3 = System.getProperty("sun.nio.PageAlignDirectMemory");
        value4 = VM.getSavedProperty("sun.nio.PageAlignDirectMemory");
    }
}
