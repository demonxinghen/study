Spring Bean的生命周期
1.实例化：分配内存空间
2.注入属性@Resource @Autowired
3.初始化
    * 执行初始化的前置方法BeanPostProcessor#afterPropertiesSet
    * 生命周期方法@PostConstruct
    * 执行初始化的后置方法BeanPostProcessor
4.使用Bean
5.销毁Bean