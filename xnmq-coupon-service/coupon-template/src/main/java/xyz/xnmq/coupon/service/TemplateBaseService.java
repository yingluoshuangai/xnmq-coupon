package xyz.xnmq.coupon.service;

import xyz.xnmq.coupon.entity.CouponTemplate;
import xyz.xnmq.coupon.exception.CouponCommonException;
import xyz.xnmq.coupon.vo.CouponTemplateSDK;
import xyz.xnmq.coupon.vo.CouponTemplateVO;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Created xnmq
 * @Date 2020/5/28
 * @Description
 * 优惠券模板 基础服务
 */
public interface TemplateBaseService {

    /**
     * 根据优惠券模板id 获取优惠券模板信息
     * @param id
     * @return
     * @throws CouponCommonException
     */
    CouponTemplate findTemplateInfo(Long id) throws CouponCommonException;

    /**
     * 查询所有可用优惠券模板
     * @return
     */
    List<CouponTemplateSDK> findAllUsableTemplate();

    /**
     * 根据ids 获得 id到CouponTemplateSDK的映射
     * @param ids
     * @return
     */
    Map<Long, CouponTemplateSDK> findIdsToTemplateSDK(Collection<Long> ids);

    /**
     * vo 转为 实体CouponTemplate
     * @param vo
     * @return
     */
    CouponTemplate voToTemplate(CouponTemplateVO vo);

    /**
     * 实体类CouponTemplate 转为 SDK
     * @param template
     * @return
     */
    CouponTemplateSDK templateToSdk(CouponTemplate template);
}
