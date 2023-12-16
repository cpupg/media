package com.sheepfly.media.common.util;

import org.springframework.beans.BeanUtils;

public class BeanCopier {
    public static void copyFrom(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    public static void copyTo(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
