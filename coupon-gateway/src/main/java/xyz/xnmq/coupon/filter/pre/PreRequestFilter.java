package xyz.xnmq.coupon.filter.pre;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.xnmq.coupon.filter.AbstractPreZuulFilter;

/**
 * @Created xnmq
 * @Date 2020/3/30
 * @Description 在日志中统计请求处理时长
 * PreRequestFilter ： 负责记录请求进入时间
 * AccessLogFilter : 负责记录请求出去时间，并将耗时记录到日志中
 */
@Slf4j
@Component
public class PreRequestFilter extends AbstractPreZuulFilter {
    @Override
    protected Object cRun() {
        context.set("startTime", System.currentTimeMillis());
        return success();
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
