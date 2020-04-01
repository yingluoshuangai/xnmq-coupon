package xyz.xnmq.coupon.filter.pre;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.xnmq.coupon.filter.AbstractPreZuulFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created xnmq
 * @Date 2020/3/29
 * @Description Token 校验过滤器
 */
@Slf4j
@Component // 过滤器需要注入spring容器
public class TokenFilter extends AbstractPreZuulFilter {
    /**
     * 过滤器主体
     * @return
     */
    @Override
    protected Object cRun() {
        // context 在父类的run方法中，已经实例化
        HttpServletRequest request = context.getRequest();
        log.info(String.format("%s request to %s",
                request.getMethod(), request.getRequestURL().toString()));
        String token = request.getParameter("token");

        // TODO token 校验逻辑
        if(null == token){
            log.error("error: token is empty");
            return fail(401, "token is empty");
        }
        return success();
    }

    /**
     * 过滤器在Pre中的排序，数字越小，排名越前,越先被执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }
}
