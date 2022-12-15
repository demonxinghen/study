### Java内存模型(JMM Java Memory Model)
概念(维基百科):
```text
The Java memory model describes how threads in the Java programming language interact through memory. Together with the description of single-threaded execution of code, the memory model provides the semantics of the Java programming language.
Java内存模型描述了Java编程语言中的线程如何通过内存进行交互。内存模型与代码的单线程执行描述一起提供了Java编程语言的语义。
```

硬件带来的3个问题:
```text
1.为了充分利用CPU,CPU会对指令进行乱序执行或者语言的编译器会指令重排,导致了有序性问题.
2.为了平衡CPU的寄存器和内存的速度差异,CPU增加了高速缓存,导致了可见性问题.
3.为了平衡CPU和IO设备的速度差异,操作系统增加了进程、线程,以分时利用CPU,导致了原子性问题.
```

解决这些问题,JMM提供了synchronized、volatile、lock、final.

指令重排序有编译器重排序和CPU重排序, 可重排序的指令, CPU可以在不同线程上同时执行.

volatile:
```text
Every write to the field will be written/flushed directly to the main memory (i.e. bypassing the cache.)
Every read of that field is read directly from the main memory.
```

#### Happens-before规则
1. 程序次序规则(Program Order Rule):在同一个线程中,按照控制流顺序,书写在前面的操作Happens-before于后面的操作.
```
int a = 1; // A
int b = 2; // B
int c = 3; // C
根据规则1, 可知A Happens-before B, B Happens-before C, A Happens-before C.
int d = a + b; // D
```
但是并不是说就不可以重排序了, 这里ABC其实是可以重排序的, 也就是说重排序违背了程序次序规则.

事实上,JMM对重排序有两种不同的策略:

* 会改变结果, 例如A->D, JMM就要求编译器和CPU都禁止这种重排序
* 不会改变结果, 例如ABC, JMM对编译器和CPU不做要求,可以重排序
2. 管程锁定规则(Monitor Lock Rule):一个unlock操作Happens-before于后面对同一个锁的lock操作.
```text
针对synchronized, 在进入synchronized时,在这之前对该线程可见的变量都会刷新到内存,然后jvm会自动加锁,synchronized代码块执行结束时,变量也会写到主存,然后会自动释放锁.对应的字节码指令是monitorenter和monitorexit.
```
3. volatile变量规则(Volatile Variable Rule):对一个volatile变量的写操作Happens-before于后面对这个变量的读操作.

所以非volatile变量写在volatile变量之前,可以让非volatile变量和volatile变量一起刷新到主存.
```java
public class VolatileDemo {
    int a = 0;
    volatile boolean flag = false;

    public void writer() {
        a = 42; // 1
        flag = true; // 2
    }

    public void reader() {
        if (flag == true) { // 3
            int i = a; // 4
            // ...
        }
    }
}
```
假设线程A执行writer, 线程B执行reader.

根据规则1,1 Happens-before 2, 3 Happens-before 4;

根据规则3, 2 Happens-before 3

根据传递性规则, 1 Happens-before 3, 1 Happens-before 4;

这里保证的不是线程A在线程B之前执行, 而是保证flag被修改之后, 其他后续读的线程能读到修改后的值.
4. 线程启动规则(Thread Start Rule): startRule.a = 42 Happens-before start方法, 所以子线程thread里的startRule.a=42. 也就是说子线程启动后, 读取到的数据是start方法调用之前的执行结果.
```java
public class StartRuleDemo {

    static class StartRule {
        int a;
    }

    public static void main(String[] args) throws InterruptedException {
        StartRule startRule = new StartRule();
        Thread thread = new Thread(() -> {
            System.out.println(startRule.a); // 此处输出42
        });
        startRule.a = 42;
        thread.start();
        thread.join();
    }
}
```
5. 线程终止规则(Thread Termination Rule):join()规则, A线程中调用B线程的join方法,当线程B完成后,线程B对共享变量的操作对线程A可见.
```java
public class VolatileDemo {

    static class StartRule {
        int a;
    }

    public static void main(String[] args) throws InterruptedException {
        StartRule startRule = new StartRule();
        Thread thread = new Thread(() -> {
            System.out.println(startRule.a);
            startRule.a = 999;
        });
        startRule.a = 42;
        thread.start();
        thread.join();
        System.out.println(startRule.a); // join执行后,thread对startRule.a的修改对主线程可见,所以startRule.a=999
    }
}
```
6. 线程中断规则(Thread Interruption Rule):interrupt方法的调用Happens-before于中断线程检测到中断状态.
```java
public class VolatileDemo {

    static class StartRule {
        int a = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        StartRule startRule = new StartRule();
        Thread thread = new Thread(() -> {
            if (Thread.interrupted()){
                System.out.println(startRule.a); // 此处输入42
            }
        });
        thread.start();
        startRule.a = 42;
        thread.interrupt();
    }
}
```
7. 对象终结规则(Finalizer Rule):一个对象的初始化完成Happens-before于finalize方法的开始.
8. 传递性(Transitivity)

#### as-if-serial规则
as-if-serial不管怎么重排序,单线程的执行结果不能被改变,也就是存在依赖关系的操作不会被重排序