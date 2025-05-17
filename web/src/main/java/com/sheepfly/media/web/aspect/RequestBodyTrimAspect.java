package com.sheepfly.media.web.aspect;

import com.sheepfly.media.common.util.ObjectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;

/**
 * 裁剪请求参数中的字符串类型属性。
 *
 * <p>解析参数在切面之前运行，因此请求方法上的@Validated会先于@Trim运行。此外，这个切面只能
 * 裁剪@RequestBody注解的参数。</p>
 *
 * @author bytechaocai
 */
@Aspect
@Component
public class RequestBodyTrimAspect {
    @Pointcut("@annotation(com.sheepfly.media.web.annotations.Trim)")
    public void trimAnnotation() {
    }

    @Pointcut("execution(* com.sheepfly.media.web.controller..*(..))")
    public void requestMapping() {
    }

    @Pointcut("trimAnnotation() && requestMapping()")
    public void controllerAndRequestMapping() {
    }

    @Before("controllerAndRequestMapping()")
    public void trim(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Annotation[] declaredAnnotations = arg.getClass().getDeclaredAnnotations();
            for (Annotation an : declaredAnnotations) {
                if (an.annotationType() == RequestBody.class) {
                    ObjectUtil.trim(arg);
                }
            }
        }
    }
}
