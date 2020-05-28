package xyz.xnmq.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.xnmq.coupon.constant.CouponCategory;
import xyz.xnmq.coupon.constant.DistributeTarget;
import xyz.xnmq.coupon.constant.ProductLine;

import java.util.Date;

/**
 * @Created xnmq
 * @Date 2020/5/28
 * @Description
 * 微服务之间使用的优惠券模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponTemplateSDK {
    /** 主键*/
    private Long id;

    /** 优惠券名称*/
    private String name;

    /** 优惠券logo*/
    private String logo;

    /** 优惠券描述*/
    private String desc;

    /** 优惠券分类*/
    private String category;

    /** 产品线*/
    private Integer productLine;

    /** 优惠券模板编码*/
    private String key;

    /** 目标用户*/
    private Integer target;

    /** 优惠券规则*/
    private  TemplateRule rule;

}
