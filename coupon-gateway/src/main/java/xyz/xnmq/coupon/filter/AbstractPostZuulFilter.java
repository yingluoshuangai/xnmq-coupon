package xyz.xnmq.coupon.filter;

import com.sun.scenario.effect.FilterContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @Created xnmq
 * @Date 2020/3/29
 * @Description 自定义 POST类型 的抽象过滤器
 */
public abstract class AbstractPostZuulFilter extends AbstractZuulFilter{
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }
}
