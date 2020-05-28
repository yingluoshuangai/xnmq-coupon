package xyz.xnmq.coupon.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Created xnmq
 * @Date 2020/5/27
 * @Description
 * 自定义异步线程池
 */
@Slf4j
@EnableAsync
@Configuration
public class AsyncPoolConfig implements AsyncConfigurer {
    /**
     * 自定义异步线程池
     * @return
     */
    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);// 核心线程数
        executor.setMaxPoolSize(20); // 最大线程数
        executor.setQueueCapacity(20); // 缓存队列容量
        executor.setKeepAliveSeconds(60); // 超出核心线程数时，空闲线程存活多久
        executor.setThreadNamePrefix("CouponAsync_"); // 线程名前缀

        executor.setWaitForTasksToCompleteOnShutdown(true); // 当线程池关闭时，是否等待线程执行完成，默认为false
        executor.setAwaitTerminationSeconds(60); // 线程池关闭时，等待线程执行完成的时间

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略

        executor.initialize(); // 初始化线程池
        return executor;
    }

    /**
     * 当线程池出现异常时，该方法会捕获异常，并处理。 只针对没有返回值的线程，有返回值的线程会将异常抛出
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

    class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler{
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            throwable.printStackTrace();
            log.info("AsyncError : {}, Method : {}, Param : {}",
                    throwable.getMessage(), method.getName(), JSON.toJSONString(objects));
            // TODO 发送邮件或短信
        }
    }
}
