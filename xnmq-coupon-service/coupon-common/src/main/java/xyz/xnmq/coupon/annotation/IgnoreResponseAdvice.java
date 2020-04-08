package xyz.xnmq.coupon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Created xnmq
 * @Date 2020/4/7
 * @Description 忽略统一响应注解
 */
@Target({ElementType.TYPE, ElementType.METHOD}) // 标注该注解可以加在属性和方法上
@Retention(RetentionPolicy.RUNTIME) // 标注该注解 运行时起作用
public @interface IgnoreResponseAdvice {
}
