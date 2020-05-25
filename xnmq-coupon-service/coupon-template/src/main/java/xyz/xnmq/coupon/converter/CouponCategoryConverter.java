package xyz.xnmq.coupon.converter;

import xyz.xnmq.coupon.constant.CouponCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

/**
 * @Created xnmq
 * @Date 2020/5/25
 * @Description
 * 属性转换器
 * 将属性CouponCategory与数据库字段转换
 * 接口 Attributeconverter<X, Y> : X 表示实体类的属性类型 表示数据库字段的类型
 *
 */
@Converter
public class CouponCategoryConverter implements AttributeConverter<CouponCategory, String> {

    /** 将实体属性X 转换为Y， 存储到数据库中, 在新增或修改时执行*/
    @Override
    public String convertToDatabaseColumn(CouponCategory couponCategory) {
        return couponCategory.getCode();
    }

    /** 将数据库字段Y转换为实体属性X， 在查询时执行*/
    @Override
    public CouponCategory convertToEntityAttribute(String s) {
        return CouponCategory.of(s);
    }
}
