package com.sheepfly.media.common.util;

import cn.hutool.core.lang.Snowflake;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * id工具类。
 *
 * @author chen
 */
@Component
public class IdUtil implements ApplicationContextAware {
    private static Snowflake snowflake;

    public static long getId() {
        return snowflake.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(snowflake.nextId());
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        snowflake = context.getBean(Snowflake.class);
    }
}
