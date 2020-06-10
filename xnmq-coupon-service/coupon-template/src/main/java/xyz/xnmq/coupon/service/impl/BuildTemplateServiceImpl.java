package xyz.xnmq.coupon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xnmq.coupon.dao.CouponTemplateDao;
import xyz.xnmq.coupon.entity.CouponTemplate;
import xyz.xnmq.coupon.exception.CouponCommonException;
import xyz.xnmq.coupon.service.AsyncService;
import xyz.xnmq.coupon.service.BuildTemplateService;
import xyz.xnmq.coupon.service.TemplateBaseService;
import xyz.xnmq.coupon.vo.CouponTemplateVO;

/**
 * @Created xnmq
 * @Date 2020/5/27
 * @Description
 */
@Slf4j
@Service
public class BuildTemplateServiceImpl implements BuildTemplateService {

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private CouponTemplateDao couponTemplateDao;

    @Autowired
    private TemplateBaseService templateBaseService;

    /**
     * 构建优惠券模板
     * @param vo
     * @return
     * @throws CouponCommonException
     */
    @Override
    public CouponTemplate buildTemplate(CouponTemplateVO vo) throws CouponCommonException {
        //校验
        if(!vo.validate()){
            throw new CouponCommonException("BuildTemplate Param Is Not Valid!");
        }
        // 优惠券模板不允许名字相同
        if(null != couponTemplateDao.findByName(vo.getName())){
            throw new CouponCommonException("Exist Same Name Template!");
        }

        // 构造优惠券模板
        CouponTemplate couponTemplate = templateBaseService.voToTemplate(vo);
        couponTemplate = couponTemplateDao.save(couponTemplate);

        // 根据优惠券模板异步生成优惠券码
        asyncService.asyncConstructCouponByTemplate(couponTemplate);
        return couponTemplate;
    }

}
