package com.sheepfly.media.web.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 添加了@Trim注解的参数，在调用方法时，会自动调用ObjectUtil.trim方法进行参数的trim操作。
 *
 * @author bytechaocai
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Trim {
}
