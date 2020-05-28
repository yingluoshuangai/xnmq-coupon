package xyz.xnmq.coupon.constant;

/**
 * @Created xnmq
 * @Date 2020/5/29
 * @Description
 * 通用 常量定义
 */
public class Constant {
    /** Kafka 消息的Topic*/
    public static final String TOPIC = "user_coupon_op";

    /** Redis Key 前缀*/
    public static class RedisPrefix{
        /** 优惠券码 key 前缀*/
        public static final String COUPON_TEMPLATE = "coupon_template_code_";

        /** 用户当前所有可用的优惠卷 key 前缀*/
        public static final String USER_COUPON_USABLE = "user_coupon_usable_";

        /** 用户当前已使用的优惠券 key 前缀*/
        public static final String USER_COUPON_USED = "user_coupon_used_";

        /** 用户当前已过期的优惠券 key 前缀*/
        public static final String USER_COUPON_EXPIRED = "user_coupon_expired_";
    }
}
