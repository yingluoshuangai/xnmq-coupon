package xyz.xnmq.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Created xnmq
 * @Date 2020/4/20
 * @Description 优惠券分发目标
 * 目前优惠券有两个分发目标 ： 单用户 和 多用户
 * 如果为单用户分发，则需要用户主动领取
 * 如果为多用户分发，则默认分发给所有匹配用户，不需要用户主动领取
 */
@Getter
@AllArgsConstructor
public enum DistributeTarget {

    SINGLE("单用户", 1),
    MULTI("多用户", 2);

    // 分发目标描述
    private String description;
    // 分发目标编码
    private Integer code;

    /**
     * 根据 code 获得 枚举
     * @param code
     * @return
     */
    public static DistributeTarget of(Integer code){
        return Optional.of(code)
                .flatMap(c ->
                        Stream.of(values())
                        .filter(distributeTarget -> distributeTarget.code.equals(c))
                        .findAny()
                ).orElseThrow(() -> new IllegalArgumentException("DistributeTarget: " + code + " no exists!"));
    }
}
