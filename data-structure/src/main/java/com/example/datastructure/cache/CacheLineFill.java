package com.example.datastructure.cache;

/**
 * 知识基础: 一个CPU有多核,每个核有单独的L1和L2缓存,多核之间共享L3缓存,多个CPU之间共享内存<br/>
 * cpu为了提高效率,在读取某个数据时,会假设其附近的数据也会被访问,因此读的是一个缓存行的数据<br/>
 * 缓存行填充,默认缓存行大小是64Byte<br/>
 * 前提: 同一数组中,其中A线程修改其中的一部分数据,B线程修改其中的另一部分数据<br/>
 * 修改的数据中要有volatile修饰的属性<br/>
 * 如果没有做缓存填充,则该数组修改的部分可能在同一缓存行,此时会出现缓存一致性问题,解决这问题用的是MESI协议,导致耗时大量增加<br/>
 * 如果做了缓存填充,则数组中的每一个对象都在不同的缓存行中,就不需要进行缓存同步,可以提高速度
 */
public class CacheLineFill {

    private static long TIMES = 1_0000_0000;

    public static void main(String[] args) throws InterruptedException {
        testFillTime();
        testNoFillTime();
    }

    private static void testFillTime() throws InterruptedException {
        ObjectFill[] array = new ObjectFill[2];
        array[0] = new ObjectFill();
        array[1] = new ObjectFill();
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < TIMES; i++) {
                array[0].m = i + 1;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < TIMES; i++) {
                array[1].m = i + 1;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long end = System.currentTimeMillis();
        System.out.println("有填充的耗时:" + (end - start));
    }

    private static void testNoFillTime() throws InterruptedException {
        ObjectNoFill[] array = new ObjectNoFill[2];
        array[0] = new ObjectNoFill();
        array[1] = new ObjectNoFill();
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < TIMES; i++) {
                array[0].m = i + 1;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < TIMES; i++) {
                array[1].m = i + 1;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long end = System.currentTimeMillis();
        System.out.println("没有填充的耗时:" + (end - start));
    }

    /**
     * 没有缓存行填充的对象
     */
    static class ObjectNoFill {
        private volatile long m;
    }

    /**
     * 有缓存行填充的对象
     */
    static class ObjectFill {
        private long m;
        private long p1;
        private long p2;
        private long p3;
        private long p4;
        private long p5;
        private long p6;
        private long p7;
    }
}
