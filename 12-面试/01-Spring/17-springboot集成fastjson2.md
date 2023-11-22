引入依赖
```xml
<dependencies>
    <dependency>
        <groupId>com.alibaba.fastjson2</groupId>
        <!--  对应的还有fastjson2-extension-spring6和fastjson2-extension,分别适用于不同的spring版本和不使用spring  -->
        <artifactId>fastjson2-extension-spring5</artifactId>
        <version>2.0.42</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <!-- 排除默认的Jackson -->
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-json</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

在 Spring Web MVC 中集成 Fastjson2
```java
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import com.alibaba.fastjson2.support.spring.webservlet.view.FastJsonJsonView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor("自定义拦截器,需要实现HandlerInterceptor").addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowedHeaders(CorsConfiguration.ALL)
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Bean
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setWriterFeatures(
                JSONWriter.Feature.WriteNullListAsEmpty,
                JSONWriter.Feature.PrettyFormat,
                JSONWriter.Feature.WriteMapNullValue,
                JSONWriter.Feature.WriteNullBooleanAsFalse,
                // JSONWriter.Feature.WriteNullNumberAsZero,
                // JSONWriter.Feature.WriteNullStringAsEmpty,
                JSONWriter.Feature.MapSortField
        );
        config.setCharset(StandardCharsets.UTF_8);
        return config;
    }

    // 使用 FastJsonHttpMessageConverter 来替换 Spring MVC 默认的 HttpMessageConverter 以提高 @RestController @ResponseBody @RequestBody 注解的 JSON序列化和反序列化速度。
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converter.setFastJsonConfig(fastJsonConfig());
        converters.add(0, converter);
    }

    // 使用 FastJsonJsonView 来设置 Spring MVC 默认的视图模型解析器，以提高 @Controller @ResponseBody ModelAndView JSON序列化速度。
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
        fastJsonJsonView.setFastJsonConfig(fastJsonConfig());
        registry.enableContentNegotiation(fastJsonJsonView);
    }
}
```
在 Spring Web Socket 中集成 Fastjson2
```java
import org.springframework.stereotype.Component;

@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Resource
    WebSocketHandler handler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //自定义配置...
        //FastjsonSockJsMessageCodec messageCodec = new FastjsonSockJsMessageCodec();
        //FastJsonConfig config = new FastJsonConfig();
        //config.set...
        //messageCodec.setFastJsonConfig(config);
        registry.addHandler(handler, "/sockjs").withSockJS().setMessageCodec(new FastjsonSockJsMessageCodec());
    }
}
```
在 Spring Data Redis 中集成 Fastjson2
```java
// 使用 GenericFastJsonRedisSerializer 作为 RedisTemplate 的 RedisSerializer 来提升JSON序列化和反序列化速度。

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);//设置默认的Serialize，包含 keySerializer & valueSerializer

        //redisTemplate.setKeySerializer(fastJsonRedisSerializer);//单独设置keySerializer
        //redisTemplate.setValueSerializer(fastJsonRedisSerializer);//单独设置valueSerializer
        return redisTemplate;
    }
}
```

```java
// 通常使用 GenericFastJsonRedisSerializer 即可满足大部分场景，如果你想定义特定类型专用的 RedisTemplate 可以使用 FastJsonRedisSerializer 来代替 GenericFastJsonRedisSerializer ，配置是类似的。

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJsonRedisSerializer);
        return template;
    }
}
```

```java
// 如果你准备使用 JSONB 作为对象序列/反序列化的方式并对序列化速度有较高的要求的话，可以对jsonb参数进行配置，该参数是 fastjson 2.0.6 版本中新增的支持，配置也很简单。

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // GenericFastJsonRedisSerializer use jsonb
        // GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer(true);

        // FastJsonRedisSerializer use jsonb
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(User.class);
        // FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // fastJsonConfig.setJSONB(true);
        // fastJsonRedisSerializer.setFastJsonConfig(fastJsonConfig);
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);

        return redisTemplate;
    }
}

```

在 Spring Messaging 中集成 Fastjson2
```java
// 使用 MappingFastJsonMessageConverter 作为 Spring Cloud Stream 或 Spring Messaging 来提升Message的序列化和反序列化速度。

@Configuration
public class StreamConfiguration {

    @Bean
    @StreamMessageConverter
    public MappingFastJsonMessageConverter messageConverter() {
        return new MappingFastJsonMessageConverter();
    }
}

```

```java
// 如果你准备使用 JSONB 作为对象序列/反序列化的方式并对序列化速度有较高的要求的话，可以对 FastJsonConfig 的 jsonb 参数进行配置，该参数是 fastjson 2.0.6 版本中新增的支持，配置也很简单。

// 注意：JSONB仅支持将Message的payload序列化为byte[]

@Configuration
public class StreamConfiguration {

    @Bean
    @StreamMessageConverter
    public MappingFastJsonMessageConverter messageConverter() {
        MappingFastJsonMessageConverter messageConverter = new MappingFastJsonMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setJSONB(true); // use jsonb
        messageConverter.setFastJsonConfig(fastJsonConfig);
        return messageConverter;
    }
}

```