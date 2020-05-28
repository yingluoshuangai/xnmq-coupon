package xyz.xnmq.coupon.service;

import xyz.xnmq.coupon.entity.CouponTemplate;

/**
 * @Created xnmq
 * @Date 2020/5/27
 * @Description
 * 优惠券模板 异步服务
 */
public interface AsyncService {
    /**
     * 根据模板 异步创建优惠券码
     */
    void asyncConstructCouponByTemplate(CouponTemplate template);
}
