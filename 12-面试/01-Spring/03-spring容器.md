### 注册关闭钩子(非web)
ConfigurableApplicationContext.registerShutdownHook()

### 扩展spring ioc容器
BeanPostProcessor/BeanFactoryPostProcessor,通过order控制执行顺序(实现Ordered接口),与bean定义一样,只处理当前容器的bean

### BeanFactory和ApplicationContext区别
优先使用ApplicationContext

| 功能                           | BeanFactory | ApplicationContext |
|------------------------------|-------------|--------------------|
| bean的实例化和装配                  | 否           | 是                  |
| 综合生命周期管理                     | 否           | 是                  |
| 自动注册BeanPostProcessor        | 否           | 是                  |
| 自动注册BeanFactoryPostProcessor | 否           | 是                  |
| 国际化MessageSource             | 否           | 是                  |
| 事件发布ApplicationEvent         | 否           | 是                  |
BeanFactory需要手动调用addBeanPostProcessor,postProcessBeanFactory