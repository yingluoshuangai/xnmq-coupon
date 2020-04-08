package xyz.xnmq.coupon.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import xyz.xnmq.coupon.annotation.IgnoreResponseAdvice;
import xyz.xnmq.coupon.vo.CommonResponse;

/**
 * @Created xnmq
 * @Date 2020/4/7
 * @Description 对响应处理的增强， 用来实现统一响应处理
 * 注解 @RestControllerAdvice 表明对 @RestController 注解的增强
 * 接口 ResponseBodyAdvice 表明 对响应的响应体进行增强
 * 这两个一起用，表示对响应返回的内容进行额外处理
 */
@RestControllerAdvice // 对 @RestController 注解的增强
public class CommonResponseDataAdvice implements ResponseBodyAdvice {

    /**
     * supports() 方法 表示对那些响应进行处理， 返回true 表示处理，执行下面的beforeBodyWrite方法，返回false 则不处理
     * 参数 methodParameter 表示返回的内容
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            //如果当前返回对象 使用了自定义注解 @IgnoreResponseAdvice 则不处理
            return false;
        }
        // 如果当前方法 使用了自定义注解 @IgnoreResponseAdvice 则不处理
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        // 否则对响应进行处理， 执行下面的beforeBodyWrite方法
        return true;
    }

    /**
     * 对响应进行处理
     *
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(0, "");

        if( null == o){
            // 如果返回对象为null， 则直接返回response
            return response;
        }else if(o instanceof CommonResponse){
            // 如果返回对象本身实现CommonResponse， 则不需要处理
            response = (CommonResponse<Object>)o;
        }else{
            // 对响应进行包装
            response.setData(o);
        }
        return response;
    }
}
