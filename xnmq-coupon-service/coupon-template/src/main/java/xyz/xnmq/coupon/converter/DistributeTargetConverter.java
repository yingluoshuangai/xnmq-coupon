package xyz.xnmq.coupon.converter;

import xyz.xnmq.coupon.constant.DistributeTarget;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Created xnmq
 * @Date 2020/5/25
 * @Description
 * 属性转换器
 * 将属性DistributeTarger 与数据库字段转换
 */
@Converter
public class DistributeTargetConverter implements AttributeConverter<DistributeTarget, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DistributeTarget distributeTarget) {
        return distributeTarget.getCode();
    }

    @Override
    public DistributeTarget convertToEntityAttribute(Integer integer) {
        return DistributeTarget.of(integer);
    }
}
