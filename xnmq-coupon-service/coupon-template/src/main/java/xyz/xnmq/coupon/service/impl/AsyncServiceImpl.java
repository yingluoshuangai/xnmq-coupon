package xyz.xnmq.coupon.service.impl;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import xyz.xnmq.coupon.constant.Constant;
import xyz.xnmq.coupon.dao.CouponTemplateDao;
import xyz.xnmq.coupon.entity.CouponTemplate;
import xyz.xnmq.coupon.service.AsyncService;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Created xnmq
 * @Date 2020/5/29
 * @Description
 * 异步服务 实现
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private CouponTemplateDao templateDao;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 根据模板 异步创建优惠券码
     *
     * @param template
     */
    @Override
    @Async("getAsyncExecutor")
    public void asyncConstructCouponByTemplate(CouponTemplate template) {
        // 计时器 计算生成优惠券码和将优惠券码塞入Redis的总时间
        Stopwatch watch = Stopwatch.createStarted();

        Set<String> couponCodes = buildCouponCode(template);

        // 生成Redis的key
        String redisKey = String.format("%s%s", Constant.RedisPrefix.COUPON_TEMPLATE, template.getId().toString());
        // 将优惠卷码 放入Redis
        log.info("Push CouponCode To Redis: {}", redisTemplate.opsForList().rightPushAll(redisKey, couponCodes));

        // 将优惠券模板置为可用
        template.setAvailable(true);
        templateDao.save(template);

        watch.stop();
        log.info("Construct CouponCode By Template Cost: {}ms", watch.elapsed(TimeUnit.MILLISECONDS));
        log.info("CouponTemplate({}) Is Available!", template.getId());
        // TODO 可以发送短信或邮件通知优惠券模板已经可用

    }

    /**
     * 构造优惠券码
     * 根据优惠券数量，构造对应数量的优惠券码
     * 优惠券码（每个优惠券都有唯一的优惠券码）：18位
     * 前四位： 产品线（3位） + 类型（1位）
     * 中间六位： 日期（yyMMdd 200201）
     * 后八位： 0 - 9 随机数构成 第一位不为0
     * @param template
     * @return
     */
    private Set<String> buildCouponCode(CouponTemplate template){
        // 计时器 计算生成优惠券码的时间
        Stopwatch watch = Stopwatch.createStarted();

        Set<String> result = new HashSet<>(template.getCount());

        // 前四位
        String prefix4 = template.getProductLine().getCode().toString()
                + template.getCategory().getCode();

        String date = new SimpleDateFormat("yyMMdd").format(template.getCreateTime());

        for(int i = 0; i != template.getCount(); i++){
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }

        // 因为生成的随机数有可能重复，导致生成的优惠券码少了，这里进行一个判断
        while(result.size() < template.getCount()){
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }

        assert  result.size() == template.getCount();

        watch.stop();
        log.info("Build Coupon Code Cost: {}ms", watch.elapsed(TimeUnit.MILLISECONDS));
        return result;

    }

    /**
     * 根据时间 构造优惠券码后14位
     * @param date
     * @return
     */
    private String buildCouponCodeSuffix14(String date){
        char[] bases = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        //中间六位
        List<Character> chars = date.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        Collections.shuffle(chars);
        String mid6 = chars.stream().map(Object::toString).collect(Collectors.joining());

        // 后八位
        String  suffix8 = RandomStringUtils.random(1, bases)
                + RandomStringUtils.randomNumeric(7);
        return mid6 + suffix8;
    }
}
