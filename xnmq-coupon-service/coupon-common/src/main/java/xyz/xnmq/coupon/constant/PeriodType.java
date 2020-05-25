package xyz.xnmq.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Created xnmq
 * @Date 2020/4/23
 * @Description 有效期类型 枚举
 */
@Getter
@AllArgsConstructor
public enum PeriodType {

    REGULAR("固定日期", 1),
    SHIFT("变动日期(以领取之日开始计算)", 2);

    // 有效期描述
    private String discription;
    // 有效期编码
    private Integer code;

    public static PeriodType of(Integer code) {
        return Optional.of(code)
                .flatMap(c ->
                        Stream.of(values())
                                .filter(periodType -> periodType.code.equals(c))
                                .findAny()
                )
                .orElseThrow(() -> new IllegalArgumentException("PeriodType: " + code + " no exists!"));
    }
}
