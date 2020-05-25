package xyz.xnmq.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Created xnmq
 * @Date 2020/4/20
 * @Description 优惠券分类枚举
 */
@Getter
@AllArgsConstructor
public enum CouponCategory {

    MANJIAN("满减卷", "001"),
    ZHEKOU("折扣卷", "002"),
    LIJIAN("立减劵", "003");

    // 优惠券分类描述
    private String description;
    // 优惠券分类编码
    private String code;

    /**
     * 根据 code 获得 枚举
     *
     * @param code
     * @return
     */
    public static CouponCategory of(String code) {

        // 为什么使用of() 而不使用 ofNullable(), 如果使用ofNullable(),假如code为空, 则抛出的依然是异常，而当使用of(), 假如code为空抛出空指针异常， 而code没有匹配到，才会抛出异常
        return Optional.of(code)
                .flatMap(codeEnum ->
                        Stream.of(values())
                                .filter(category -> category.code.equals(codeEnum))
                                .findAny()
                ).orElseThrow(() -> new IllegalArgumentException("CouponCategory: " + code + " no exists!"));

//        Objects.requireNonNull(code);
//        return Stream.of(values())
//                .filter(category -> code.equals(category.getCode()))
//                .findAny()
//                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }
}
