package com.yingjun.ssm.aop;

import com.yingjun.ssm.dto.BaseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author yingjun
 * <p>
 * 采用AOP的方式处理参数问题。
 */
@Component
@Aspect
public class BindingResultAop {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.yingjun.ssm.web.*.*(..))")
    public void aopMethod() {

    }

    @Around("aopMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("测试AOP: around");
        BindingResult bindingResult = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BindingResult) {
                bindingResult = (BindingResult) arg;
            }
        }
        if (bindingResult != null) {
            if (bindingResult.hasErrors()) {
                String errorInfo = "[" + bindingResult.getFieldError().getField() + "]" + bindingResult.getFieldError().getDefaultMessage();
                return new BaseResult<>(false, errorInfo);
            }
        }
        return joinPoint.proceed();
    }

    @Before("aopMethod()")
    public void before() throws Throwable {
            LOG.info("测试AOP: before");
    }

    @After("aopMethod()")
    public void after() throws Throwable {
        LOG.info("测试AOP: after");
    }
}
