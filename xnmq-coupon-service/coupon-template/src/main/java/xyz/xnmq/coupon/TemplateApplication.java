package xyz.xnmq.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Created xnmq
 * @Date 2020/4/19
 * @Description 优惠券模板服务启动类
 */
@EnableEurekaClient
@EnableJpaAuditing // 开启Jpa审计功能
@EnableScheduling //开启定时任务
@SpringBootApplication
public class TemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }
}
