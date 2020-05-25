package xyz.xnmq.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.xnmq.coupon.constant.CouponCategory;
import xyz.xnmq.coupon.constant.PeriodType;

import java.time.Instant;

/**
 * @Created xnmq
 * @Date 2020/4/23
 * @Description 优惠券规则对象定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {

    /** 优惠券过期规则*/
    private Expiration expiration;
    /** 折扣*/
    private Discount discount;
    /** 每个人最多领几张优惠券*/
    private Integer limitation;
    /** 使用范围 地域 + 商品类型*/
    private Usage usage;
    /** 权重（可以和那些优惠券叠加使用， 同一类的优惠券不能叠加）*/
    private String weight;

    /**
     * 有效期规则
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Expiration{
        /** 有效期规则， 对应 PeriodType 的 code 字段 **/
        private Integer period;

        /** 有效间隔， 只对变动性有效期有效**/
        private Integer gap;

        /** 优惠券模板的失效日期，时间戳格式， 两类规则都有效**/
        private Long deadline;

        /**
         * 有效期规则 有效性校验
         * 有效期规则是否合法（在枚举类中存在），
         * 有效间隔是否合法（大于0），
         * 优惠券模板失效日期是否合法（大于当前时间）
         * @return
         */
        boolean validate(){
            // 校验
            return null != PeriodType.of(period) && gap > 0 && deadline > Instant.now().getEpochSecond();
        }
    }

    /**
     * 折扣
     * 需要与优惠券类型配合，才能确定折扣
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Discount{
        /**
         * 额度
         * 20： 满减卷（达到某个金额，减20元）, 折扣卷（打2折), 立减劵（减20元）
         */
        private Integer quota;

        /**
         * 基准
         * 达到那个基准，折扣卷才有效
         * 30: 金额达到30元，折扣才生效
         */
        private Integer base;

        /**
         * 折扣 有效性校验
         * code ： 优惠券类型code
         * 当优惠券类型为 满减卷，基准不能小于0
         * @return
         */
        boolean validate(String code){
            if(CouponCategory.of(code).equals(CouponCategory.MANJIAN) && base < 0){
               return false;
            }
            return true;
        }
    }

    /**
     * 使用范围
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage{
        /** 省份*/
        private String province;

        /** 城市*/
        private String city;

        /** 商品类型 文娱, 生鲜, 家居, 全品类*/
        private String goodsType;

        /**
         * 使用范围 有效性校验
         * 省份不能为空
         * 城市不能为空
         * 商品类型不能为空
         * @return
         */
        boolean validate(){
            return StringUtils.isNotEmpty(province)
                    && StringUtils.isNotEmpty(city)
                    && StringUtils.isNotEmpty(goodsType);
        }

    }
}
