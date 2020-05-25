package xyz.xnmq.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Created xnmq
 * @Date 2020/4/20
 * @Description 产品线枚举
 * 优惠券属于哪个产品线， 类比淘宝有天猫和淘宝两套产品线
 */
@Getter
@AllArgsConstructor
public enum ProductLine {
    DAMAO("达猫", 1),
    DABAO("大宝", 2);

    // 产品线描述
    private String description;
    // 产品线编码
    private Integer code;

    /**
     * 根据 code 返回 枚举
     * @param code
     * @return
     */
    public static ProductLine of(Integer code){
        return Optional.of(code)
                .flatMap(c ->
                    Stream.of(values())
                            .filter(productLine -> productLine.code.equals(c))
                            .findAny()
                ).orElseThrow(() -> new IllegalArgumentException("ProductLine: " + code + "not exists!"));
    }
}
