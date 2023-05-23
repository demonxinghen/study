### Spring Bean的生命周期
1.实例化：分配内存空间，调用无参构造
2.注入属性@Resource @Autowired
3.初始化
    * 执行初始化的前置方法
        * BeanPostProcessor#postProcessBeforeInitialization
    * 执行初始化
        * @PostConstruct(建议,JSR-250标准,不耦合spring)
        * InitializingBean
        * initMethod
    * 执行初始化的后置方法
        * BeanPostProcessor#postProcessAfterInitialization
4.使用Bean
5.销毁Bean
    * @PreDestroy(建议,JSR-250标准,不耦合spring)
    * DisposableBean
    * destroyMethod(destroyMethod和destroy-method有一个特殊值,inferred,使用该值,只要实现了java.lang.AutoCloseable 或 java.io.Closeable接口,对应的close和shutdown方法会被执行,适用于公共方法)

还有个LifeCycle接口,参与对象的启动和关闭

### Bean的别名

### Bean的注册
入口：<T> T AbstractBeanFactory.getBean(String name, Class<T> requiredType)
调用链：
<T> T AbstractBeanFactory.doGetBean(String name, @Nullable Class<T> requiredType, @Nullable Object[] args, boolean typeCheckOnly)，此时typeCheckOnly为false
调用getSingleton(beanName):
    * 返回为null，则创建bean

Object DefaultSingletonBeanRegistry.getSingleton(String beanName, boolean allowEarlyReference), 此时allowEarlyReference为true

Set<String> singletonsCurrentlyInCreation 创建中的bean集合

1.从singletonObjects获取bean
    1.1 获取到或者没获取到，直接返回(没获取到就直接返回null？)
    1.2 没获取到且bean正在创建中


SimpleAliasRegistry.aliasMap

Spring中，由IOC容器管理的对象。
ApplicationContext(IOC容器)负责实例化、配置和组装bean。
容器通过获取bean元数据来执行实例化、配置和组装。元数据的方式有xml、注解、编码。

### Scope
* singleton: 针对一个容器而言，不同容器(比如父子容器)可以各自有一个同名同类型bean
* prototype: 
* request: 仅在web-application有用, http request
* session: 仅在web-application有用, http session
* application: 仅在web-application有用, ServletContext
* websocket: 仅在web-application有用, WebSocket
* thread: 线程范围可用,默认未注册,参考SimpleThreadScope
* 自定义scope,实现Scope接口



