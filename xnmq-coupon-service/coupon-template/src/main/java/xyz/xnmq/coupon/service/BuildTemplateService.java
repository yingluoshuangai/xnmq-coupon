package xyz.xnmq.coupon.service;

import xyz.xnmq.coupon.entity.CouponTemplate;
import xyz.xnmq.coupon.exception.CouponCommonException;
import xyz.xnmq.coupon.vo.CouponTemplateVO;

/**
 * @Created xnmq
 * @Date 2020/5/27
 * @Description
 * 优惠券模板 构建优惠券服务
 */
public interface BuildTemplateService {
    CouponTemplate buildTemplate(CouponTemplateVO vo) throws CouponCommonException;
}
