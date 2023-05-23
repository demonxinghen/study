### 注册关闭钩子(非web)
ConfigurableApplicationContext.registerShutdownHook()

### 扩展spring ioc容器
BeanPostProcessor/BeanFactoryPostProcessor,通过order控制执行顺序(实现Ordered接口),与bean定义一样,只处理当前容器的bean