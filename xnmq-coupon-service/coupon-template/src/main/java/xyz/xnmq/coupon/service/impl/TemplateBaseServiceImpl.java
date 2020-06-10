package xyz.xnmq.coupon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xnmq.coupon.dao.CouponTemplateDao;
import xyz.xnmq.coupon.entity.CouponTemplate;
import xyz.xnmq.coupon.exception.CouponCommonException;
import xyz.xnmq.coupon.serialization.CouponTemplateSerialize;
import xyz.xnmq.coupon.service.TemplateBaseService;
import xyz.xnmq.coupon.vo.CouponTemplateSDK;
import xyz.xnmq.coupon.vo.CouponTemplateVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Created xnmq
 * @Date 2020/6/5
 * @Description
 */
@Slf4j
@Service
public class TemplateBaseServiceImpl implements TemplateBaseService {
    @Autowired
    private CouponTemplateDao couponTemplateDao;

    /**
     * 根据优惠券模板id 获取优惠券模板信息
     *
     * @param id
     * @return
     * @throws CouponCommonException
     */
    @Override
    public CouponTemplate findTemplateInfo(Long id) throws CouponCommonException {
        return couponTemplateDao.findById(id)
                .orElseThrow(() -> new CouponCommonException("Template Is Not Exist: " + id));
        // 过时的写法
//        Optional<CouponTemplate> template = couponTemplateDao.findById(id);
//        if(null == template){
//            throw new CouponCommonException("Template Is Not Exist: " + id);
//        }
//        return template;
    }

    /**
     * 查询所有可用优惠券模板
     * 因为有延时，可能我们这边查的时候未过期，但是到达消费方时就过期了，所以需要消费方做个有效期校验
     *
     * @return
     */
    @Override
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        List<CouponTemplate> templates = couponTemplateDao.findAllByAvailableAndExpired(true, false);
        List<CouponTemplateSDK> templateSDKS =
                templates.stream().map(template -> templateToSdk(template)).collect(Collectors.toList());
        return templateSDKS;
    }

    /**
     * 根据ids 获得 id到CouponTemplateSDK的映射
     *
     * @param ids
     * @return
     */
    @Override
    public Map<Long, CouponTemplateSDK> findIdsToTemplateSDK(Collection<Long> ids) {
        List<CouponTemplate> templates = couponTemplateDao.findAllById(ids);
        return templates.stream().collect(Collectors.toMap(
                template -> template.getId(),
                template -> templateToSdk(template)
        ));
//         return templates.stream().map(this::templateToSdk)
//                .collect(Collectors.toMap(
//                        CouponTemplateSDK :: getId,
//                        Function.identity()
//                ));
    }

    /**
     * vo 转为 实体CouponTemplate
     * @param vo
     * @return
     */
    @Override
    public CouponTemplate voToTemplate(CouponTemplateVO vo){
        return new CouponTemplate(
                vo.getName(),
                vo.getLogo(),
                vo.getDesc(),
                vo.getCategory(),
                vo.getProductLine(),
                vo.getCount(),
                vo.getUserId(),
                vo.getTarget(),
                vo.getRule()
        );
    }

    /**
     * 实体类CouponTemplate 转为 SDK
     * @param template
     * @return
     */
    @Override
    public CouponTemplateSDK templateToSdk(CouponTemplate template){
        return new CouponTemplateSDK(
                template.getId(),
                template.getName(),
                template.getLogo(),
                template.getDesc(),
                template.getCategory().getCode(),
                template.getProductLine().getCode(),
                template.getKey(),
                template.getTarget().getCode(),
                template.getRule()
        );
    }
}
