package xyz.xnmq.coupon.filter.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import xyz.xnmq.coupon.filter.AbstractPostZuulFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created xnmq
 * @Date 2020/3/30
 * @Description 在日志中统计请求处理时长
 *  PreRequestFilter ： 负责记录请求进入时间
 *  AccessLogFilter : 负责记录请求出去时间，并将耗时记录到日志中
 */
@Slf4j
@Component
public class AccessLogFilter extends AbstractPostZuulFilter {
    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        Long startTime = (Long)context.get("startTime");
        Long duration = System.currentTimeMillis() - startTime;
        String uri = request.getRequestURI();
        log.info("uri : {}, duration : {}", uri, duration);
        return success();
    }

    /**
     * FilterConstants.SEND_RESPONSE_FILTER_ORDER 是Zuul的Post类型过滤器最大执行顺序，默认为1000
     * 我们的请求时长统计过滤器应该设为1000，但是zuul的默认过滤器也为1000，在过滤器顺序相同时，随机执行，
     * 那么我们的过滤器就有可能不执行，为了安全，设置为FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }
}
