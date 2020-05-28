package xyz.xnmq.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedBy;
import xyz.xnmq.coupon.constant.CouponCategory;
import xyz.xnmq.coupon.constant.DistributeTarget;
import xyz.xnmq.coupon.constant.ProductLine;
import xyz.xnmq.coupon.converter.CouponCategoryConverter;
import xyz.xnmq.coupon.converter.DistributeTargetConverter;
import xyz.xnmq.coupon.converter.ProductLineConverter;
import xyz.xnmq.coupon.converter.TemplateRuleConverter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Created xnmq
 * @Date 2020/5/27
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponTemplateVO {

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

    /** 总数*/
    private Integer count;

    /** 创建时间*/
    private Date createTime;

    /** 创建用户*/
    private Long userId;

    /** 目标用户*/
    private Integer target;

    /** 优惠卷规则*/
    private TemplateRule rule;

    /**
     * 对象有效性校验
     * @return
     */
    public boolean validate(){
        // TODO 使用这种方法校验，而不是一堆if来校验。 以前校验的时候我总是用各种if嵌套，但是我发现这种写法的校验清晰多了。
        // 字符串类型校验
        boolean stringValid = StringUtils.isNotEmpty(name)
                && StringUtils.isNotEmpty(logo)
                && StringUtils.isNotEmpty(desc);
        // 枚举类型校验
        boolean enumValid = null != CouponCategory.of(category)
                && null != ProductLine.of(productLine)
                && null != DistributeTarget.of(target);
        // 数字类型校验
        boolean numValid = count > 0 && userId >0;

        return stringValid && enumValid && numValid && rule.validate();
    }
}
