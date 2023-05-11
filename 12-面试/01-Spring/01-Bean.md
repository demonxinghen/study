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