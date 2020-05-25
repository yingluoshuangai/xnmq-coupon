package xyz.xnmq.coupon.converter;

import xyz.xnmq.coupon.constant.ProductLine;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Created xnmq
 * @Date 2020/5/25
 * @Description
 * 属性转换器
 * 将属性ProductLine 与 数据库字段转换
 */
@Converter
public class ProductLineConverter implements AttributeConverter<ProductLine, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProductLine productLine) {
        return productLine.getCode();
    }

    @Override
    public ProductLine convertToEntityAttribute(Integer integer) {
        return ProductLine.of(integer);
    }
}
