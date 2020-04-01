package xyz.xnmq.coupon.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @Created xnmq
 * @Date 2020/3/29
 * @Description 自定义 Pre类型 的抽象过滤器
 */
public abstract class AbstractPreZuulFilter extends AbstractZuulFilter{
    /**
     * 过滤器类型为pre
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
}
