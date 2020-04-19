package xyz.xnmq.coupon.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.xnmq.coupon.enums.ExceptionEnum;
import xyz.xnmq.coupon.exception.CouponCommonException;
import xyz.xnmq.coupon.vo.CommonResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created xnmq
 * @Date 2020/4/9
 * @Description 全局异常处理增强
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 注解 @ExceptionHandler(value = CouponCommonException.class) 表示对CouponCommonException异常 进行拦截处理
     */
    @ExceptionHandler(value = CouponCommonException.class)
    public CommonResponse<String> handlerCouponCommonException(HttpServletRequest req, CouponCommonException ex){

        CommonResponse<String> response = new CommonResponse<>(
                ExceptionEnum.COMMONEXCEPTION.getCode(),
                ExceptionEnum.COMMONEXCEPTION.getMessage());
        response.setData(ex.getMessage());
        return response;
    }
}
