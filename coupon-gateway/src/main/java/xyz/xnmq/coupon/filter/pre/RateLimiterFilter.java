package xyz.xnmq.coupon.filter.pre;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.xnmq.coupon.filter.AbstractPreZuulFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created xnmq
 * @Date 2020/3/29
 * @Description 限流 过滤器
 */
@Slf4j
@Component
public class RateLimiterFilter extends AbstractPreZuulFilter {

    /*
    使用 google工具包 的 RateLimiter实现限流
    RateLimiter.create(2.0) 表示每秒产生两个令牌
    而 RateLimiter是全局通用，通过rateLimiter.tryAcquire()判断RateLimiter有没有令牌，从而实现每秒两个访问的限流功能
     */
    RateLimiter rateLimiter = RateLimiter.create(2.0);

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        if(rateLimiter.tryAcquire()){
            //获得令牌
            log.info("get rate token success");
            return success();
        }else{
            //没有获得令牌
            log.error("rate limit: {}", request.getRequestURI());
            return fail(402, "error: rate limit");
        }
    }

    @Override
    public int filterOrder() {
        return 2;
    }
}
