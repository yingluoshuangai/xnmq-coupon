package xyz.xnmq.coupon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xnmq.coupon.entity.CouponTemplate;

import java.util.List;

/**
 * @Created xnmq
 * @Date 2020/5/25
 * @Description
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Long> {
    CouponTemplate findByName(String name);

    /**
     * 查询所有可用优惠券
     * 有效且未过期的优惠券模板
     * avaiable 为true， expired为flase
     * @param avaiable
     * @param expired
     * @return
     */
    List<CouponTemplate> findAllByAvailableAndExpired(Boolean avaiable, Boolean expired);

    List<CouponTemplate> findAllById(Iterable<Long> ids);
}
