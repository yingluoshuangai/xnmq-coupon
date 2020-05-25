package xyz.xnmq.coupon.converter;

import com.alibaba.fastjson.JSON;
import xyz.xnmq.coupon.vo.TemplateRule;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Created xnmq
 * @Date 2020/5/25
 * @Description
 * 属性转换器
 * 属性TemplateRule 与数据库字段转换
 */
@Converter
public class TemplateRuleConverter implements AttributeConverter<TemplateRule, String> {
    @Override
    public String convertToDatabaseColumn(TemplateRule templateRule) {
        return JSON.toJSONString(templateRule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String s) {
        return JSON.parseObject(s, TemplateRule.class);
    }
}
