package xyz.xnmq.coupon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sun.xml.internal.ws.api.pipe.NextAction;

/**
 * @Created xnmq
 * @Date 2020/3/27
 * @Description 自定义过滤器的父类， 用来定义自定义过滤器的通用功能
 */
public abstract class AbstractZuulFilter extends ZuulFilter {

    /**
     * RequestContext 是Zuul提供的，在过滤器之间传递request，response，状态信息和数据
     * RequestContext 在请求期间都是存在的，且是ThreadLocal，是线程安全的
     * RequestContext是ConcurrentHashMap的扩展，我们可以方便的在里面放些数据
     */
    public RequestContext context;

    //标志位， 表示是否执行后面的过滤器
    private final static String NEXT = "next";// 这里为什么是next而不是true呢？

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //getOrDefault(a, b) 如果没有取到值，则返回默认值b
        // 为什么使用getOrDefault而不是get，因为一些默认过滤器可能没有NEXT这个参数，导致取值时，报错
        return (boolean)ctx.getOrDefault(NEXT, true);
    }

    @Override
    public Object run() throws ZuulException {
        //RequestContext 初始化
        context = RequestContext.getCurrentContext();
        return cRun();
    }

    /**
     * 一种设计模式，子类实现cRun方法，来实现真正的过滤功能
     * @return
     */
    protected abstract Object cRun();

    /**
     * 通用快速失败方法
     * @param code 错误码
     * @param msg 错误信息
     * @return
     */
    public Object fail(int code, String msg){
        // 设置自定义过滤器都不执行
        // 根据设计，所有自定义过滤器都继承该父类, 该父类通过NEXT字段，判断过滤器是否执行
        context.set(NEXT, false);
        // setSendZuulResponse() 这个请求最终不会被zuul转发到后端服务器,但是如果当前Filter后面还存在其他Filter,那么其他Filter仍然会被调用到,
        context.setSendZuulResponse(false);

        // 设置Response
        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.setResponseStatusCode(code);
        context.setResponseBody(String.format("{\"result\": \"%s!\"}", msg));
        return null;
    }

    /**
     * 通用快速过滤器通过方法，这里是指当前过滤器执行完成
     * @return
     */
    public Object success(){
        context.set(NEXT, true);
        return null;
    }

}
