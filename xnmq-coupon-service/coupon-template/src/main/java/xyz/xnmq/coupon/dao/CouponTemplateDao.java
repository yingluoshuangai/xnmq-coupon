package xyz.xnmq.coupon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xnmq.coupon.entity.CouponTemplate;

/**
 * @Created xnmq
 * @Date 2020/5/25
 * @Description
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Long> {

}
