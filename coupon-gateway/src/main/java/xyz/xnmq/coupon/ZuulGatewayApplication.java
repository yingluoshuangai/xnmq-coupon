package xyz.xnmq.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @Created xnmq
 * @Date 2020/3/27
 * @Description Zuul Server 的启动类
 * 注解说明： @SpringCloudApplication 注解里包含了@SpringBootApplication注解，并且还添加了@EnableDiscoveryClient 和@EnableCircuitBreaker
 * 其中@EnableDiscoveryClient 表明这是一个Eureka Client
 * 其中@EnableCircuitBreaker 表明开启熔断功能，该应用是一个路由服务，需要开启熔断，以免雪崩情况发生
 */
@EnableZuulServer //标识当前应用为Zuul Server
@SpringCloudApplication
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }
}
