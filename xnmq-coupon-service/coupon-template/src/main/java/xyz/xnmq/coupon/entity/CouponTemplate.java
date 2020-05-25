package xyz.xnmq.coupon.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.xnmq.coupon.constant.CouponCategory;
import xyz.xnmq.coupon.constant.DistributeTarget;
import xyz.xnmq.coupon.constant.ProductLine;
import xyz.xnmq.coupon.converter.CouponCategoryConverter;
import xyz.xnmq.coupon.converter.DistributeTargetConverter;
import xyz.xnmq.coupon.converter.ProductLineConverter;
import xyz.xnmq.coupon.converter.TemplateRuleConverter;
import xyz.xnmq.coupon.vo.TemplateRule;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Created xnmq
 * @Date 2020/5/21
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupon_template")
@EntityListeners(AuditingEntityListener.class) // 实体类监听器， 在新增或修改时自动塞值
public class CouponTemplate implements Serializable {

    /** 自增主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) // 字段名为id， 不允许为空
    private Integer id;

    /** 是否是可用状态*/
    private Boolean available;

    /** 是否过期*/
    private Boolean expired;

    /** 优惠券名称*/
    private String name;

    /** 优惠券logo*/
    private String logo;

    /** 优惠券描述*/
    private String desc;

    /** 优惠券分类*/
    @Convert(converter = CouponCategoryConverter.class)
    private CouponCategory category;

    /** 产品线*/
    @Convert(converter = ProductLineConverter.class)
    private ProductLine productLine;

    /** 总数*/
    @Column(name = "coupon_count")
    private Integer count;

    /** 创建时间*/
    @CreatedBy // 在新增或修改时自动塞入当前时间
    private Date createTime;

    /** 创建用户*/
    private Long userId;

    /** 优惠券模板编码*/
    @Column(name = "template_key", nullable = false)
    private String key;

    /** 目标用户*/
    @Convert(converter = DistributeTargetConverter.class)
    private DistributeTarget target;

    /** 优惠卷规则*/
    @Convert(converter = TemplateRuleConverter.class)
    private TemplateRule rule;

    /**
     * 生成优惠券模板
     * */
    public CouponTemplate(Boolean available, Boolean expired, String name, String logo,
                          String desc, String category, Integer productLine, Integer count,
                          Long userId, Integer target, TemplateRule rule) {
        this.available = false;
        this.expired = false;
        this.name = name;
        this.logo = logo;
        this.desc = desc;
        this.category = CouponCategory.of(category);
        this.productLine = ProductLine.of(productLine);
        this.count = count;
        this.userId = userId;
        // 优惠卷编码 = 4（产品线和类型） + 8（日期：yyyyMMdd) + 4(id)
        this.key = productLine.toString() + category +
                new SimpleDateFormat("yyyyMMdd").format(new Date());
        this.target = DistributeTarget.of(target);
        this.rule = rule;
    }
}
