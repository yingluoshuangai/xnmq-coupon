package xyz.xnmq.coupon.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Created xnmq
 * @Date 2020/4/1
 * @Description 定制 HTTP 消息转换器
 * configureMessageConverters 是springboot用来序列化对象的
 * 内置了许多转换器，每次转化消息时，都从中选择一个最优的转换器 进行转换
 * 我们清空了转换器，并只使用一个转换器 MappingJacksong2HttpMessageConverter
 *
 * 这样做的好处，应该是，第一不用选择转换器，优化了性能。第二保证了返回数据的数据格式一致性。
 * 以上都是我的猜测，别人是这样写的，也没查到为什么这样写
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 清空其他消息转换器
        converters.clear();

        // 添加转换器 MappingJackson2HttpMessageConverter
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
