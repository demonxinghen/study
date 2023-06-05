### @EnableAspectJAutoProxy
启用@AspectJ支持

### @Aspect
声明切面类

### @PointCut
切入点

### Advice
* @Before 前置通知
* @After  后置通知
* @AfterThrowing
* @AfterReturning
* @Around

任何通知都可以声明一个org.aspectj.lang.JoinPoint类型的参数作为它的第一个参数(@Around的参数是ProceedingJoinPoint)

执行顺序
1. @Around中proceed()执行之前逻辑
2. @Before
3. @Around中proceed()执行,可以在此处执行循环,比如执行失败,重试三次的机制
4. @AfterReturning/@AfterThrowing
5. @After
6. @Around中proceed()执行之后逻辑

tips: 
* 如果两个相同的通知，可以使用Ordered或@Order来排序
* @After的优先级其实在@AfterReturning/@AfterThrowing之前,但是由于@After语义类似于finally,所以会在@AfterReturning/@AfterThrowing之后执行

### 引入 introductions
使用@DeclareParents进行引入

```java
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;

public class UsageTracking {
    @DeclareParents(value = "com.xyz.service.*+", defaultImpl = DefaultUsageTracked.class)
    public static UsageTracked mixin;

    @Before("execution(* com.xyz.service.*.*(..)) && this(usageTracked)")
    public void recordUsage(UsageTracked usageTracked){
        usageTracked.incrementUseCount();
    }
}
```

Spring AOP使用JDK动态代理和CGLIB.如果被代理的对象实现了至少一个接口,则使用JDK动态代理,否则使用CGLIB代理.

也可以强制使用CGLIB代理,配置

```java
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// proxyTargetClass配置为true
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfiguration{}
```
或者Spring AOP

```xml
<aop:config proxy-target-class="true">
    <!-- other beans defined here... -->
</aop:config>
```
或者AspectJ
```xml
<!--不要被元素的名称误导。使用它会导致创建 Spring AOP 代理。这里使用@AspectJ 风格的方面声明，但不涉及AspectJ 运行时。-->
<aop:aspectj-autoproxy proxy-target-class="true"/>
```

### 简单AOP示例
```java
public interface Pojo{
    
}
public class SimplePojo implements Pojo{
    public void foo(){
        this.bar();
    }
    
    public void bar(){
        
    }
}
```
直接调用,非代理方式
```java
public class Main{
    public static void main(String[] args) {
        Pojo pojo = new SimplePojo();
        pojo.foo();
    }
}
```
代理调用

```java
import org.springframework.aop.framework.ProxyFactory;

public class Main {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new SimplePojo());
        proxyFactory.addInterface(Pojo.class);
        proxyFactory.addAdvice(new RetryAdvise());
        
        Pojo pojo = (Pojo) proxyFactory.getProxy();
        pojo.foo();
    }
}
```
org.springframework.aop.aspectj.annotation.AspectJProxyFactory创建代理

```java
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AopContext;

public class Main {
    public static void main(String[] args) {
        Object targetObject = new Object();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(targetObject);
        proxyFactory.addAspect(SecurityManager.class);
        proxyFactory.addAspect(usageTracker);
        Pojo pojo = proxyFactory.getProxy();

        
    }
}
```

Spring AOP缺陷：生成的代理对象在内部调用方法时,AOP失效,因为内部是this调用,其次只能对public、protected生效

解决方式1：通过 AopContext.currentProxy() 获取代理对象去调用, 不建议, 容易疏忽
解决方式2：使用 aspectj , 官网 [aspectj](https://www.eclipse.org/aspectj/)

