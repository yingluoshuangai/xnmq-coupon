package xyz.xnmq.coupon.serialization;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import xyz.xnmq.coupon.entity.CouponTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @Created xnmq
 * @Date 2020/5/25
 * @Description
 * 优惠券模板实体类 自定义序列化器
 */
public class CouponTemplateSerialize extends JsonSerializer<CouponTemplate> {
    /**
     * 序列化方法
     * CouponTemplate 需要序列化的对象
     * JsonGenerator Json的生成器
     * SerializerProvider 序列化的工具
     */
    @Override
    public void serialize(CouponTemplate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        // 声明开始序列化对象
        gen.writeStartObject();
        // 序列化对象
        gen.writeStringField("id", value.getId().toString());
        gen.writeStringField("name", value.getName());
        gen.writeStringField("logo", value.getLogo());
        gen.writeStringField("desc", value.getDesc());
        gen.writeStringField("category", value.getCategory().getDescription()); // category是个枚举类，序列化时返回枚举类的description属性
        gen.writeStringField("productLine", value.getProductLine().getDescription());
        gen.writeStringField("count", value.getCount().toString());
        gen.writeStringField("createTime",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value.getCreateTime()));
        gen.writeStringField("userId", value.getUserId().toString());
        gen.writeStringField("key", value.getKey() + String.format("%04d", value.getId()));
        gen.writeStringField("target", value.getTarget().getDescription());
        gen.writeStringField("rule", JSON.toJSONString(value.getRule()));

        // 结束序列化对象
        gen.writeEndObject();
    }
}
