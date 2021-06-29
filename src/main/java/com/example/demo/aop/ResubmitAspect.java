package com.example.demo.aop;

import cn.hutool.crypto.SecureUtil;
import com.example.demo.annotation.Resubmit;
import com.example.demo.common.ApiException;
import com.example.demo.common.Constant;
import com.example.demo.config.redisson.RedissonLockClient;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: WangYuanrong
 * @Date: 2021/6/24 9:40
 */
@Slf4j
@Aspect
@Component
public class ResubmitAspect {

    @Autowired
    private RedissonLockClient redissonLockClient;


    /**
     * 防重复提交aop
     * @param joinPoint
     * @param resubmit
     * @return
     * @throws Throwable
     */
    @Around("@annotation(resubmit)")
    public Object resubmit(ProceedingJoinPoint joinPoint, Resubmit resubmit) throws Throwable {
        if (Objects.nonNull(resubmit)) {
            // 获取参数
            Object[] args = joinPoint.getArgs();
            // 进行一些参数的处理，比如获取订单号，操作人id等
            String prefix = Constant.RESUBMIT_LOCK_KEY;
            final Class<?> aClass = joinPoint.getTarget().getClass();
            final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String suffix = aClass.getName() + signature.getName() + Arrays.toString(joinPoint.getArgs());
            String key = prefix + SecureUtil.md5(suffix);
            // 公平加锁，lockTime后锁自动释放
            boolean isLocked = false;
            try {
                isLocked = redissonLockClient.fairLock(key, resubmit.lockTime(), TimeUnit.MILLISECONDS);
                // 如果成功获取到锁就继续执行
                if (isLocked) {
                    return joinPoint.proceed();
                } else {
                    // 未获取到锁
                    throw new ApiException("请勿重复提交");
                }
            } finally {
                if (isLocked) {
                    redissonLockClient.unlock(key);
                }
            }
        }

        return joinPoint.proceed();

    }
}
