package xyz.xnmq.coupon.enums;

/**
 * @Created xnmq
 * @Date 2020/4/9
 * @Description 异常枚举
 */
public enum ExceptionEnum {
    COMMONEXCEPTION(-1, "business error"); // 通用异常

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }}
