### Spring Bean的生命周期
1.实例化：分配内存空间，调用无参构造
2.注入属性@Resource @Autowired
3.初始化
    * 执行初始化的前置方法
        * BeanPostProcessor#postProcessBeforeInitialization
    * 执行初始化
        * @PostConstruct
        * InitializingBean
        * initMethod
    * 执行初始化的后置方法
        * BeanPostProcessor#postProcessAfterInitialization
4.使用Bean
5.销毁Bean
    * @PreDestroy
    * DisposableBean
    * destroyMethod

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

