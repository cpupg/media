package com.sheepfly.media.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * 对象工具类。
 *
 * @author bytechaocai
 */
public class ObjectUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtil.class);
    /**
     * 基本类型。
     */
    private static final Set<Class<?>> PRIMITIVE_TYPE_SETS = new HashSet<>();

    static {
        PRIMITIVE_TYPE_SETS.add(Integer.class);
        PRIMITIVE_TYPE_SETS.add(Integer.TYPE);
        PRIMITIVE_TYPE_SETS.add(Long.class);
        PRIMITIVE_TYPE_SETS.add(Long.TYPE);
        PRIMITIVE_TYPE_SETS.add(Short.class);
        PRIMITIVE_TYPE_SETS.add(Short.TYPE);
        PRIMITIVE_TYPE_SETS.add(Byte.class);
        PRIMITIVE_TYPE_SETS.add(Byte.TYPE);
        PRIMITIVE_TYPE_SETS.add(Float.class);
        PRIMITIVE_TYPE_SETS.add(Float.TYPE);
        PRIMITIVE_TYPE_SETS.add(Double.class);
        PRIMITIVE_TYPE_SETS.add(Double.TYPE);
        PRIMITIVE_TYPE_SETS.add(Boolean.class);
        PRIMITIVE_TYPE_SETS.add(Boolean.TYPE);
        PRIMITIVE_TYPE_SETS.add(Character.class);
        PRIMITIVE_TYPE_SETS.add(Character.TYPE);
    }

    private ObjectUtil() {
    }

    /**
     * 裁剪
     *
     * @param bean 要裁剪的对象。
     */
    public static void trim(Object bean) {
        if (bean == null) {
            return;
        }
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(bean.getClass());
        try {
            for (PropertyDescriptor pd : pds) {
                Class<?> returnType = pd.getReadMethod().getReturnType();
                Object value = pd.getReadMethod().invoke(bean);
                if ("class".equals(pd.getName()) || value == null || isPrimitiveType(returnType)) {
                    continue;
                }
                if (returnType == String.class) {
                    String s = (String) value;
                    pd.getWriteMethod().invoke(bean, s.trim());
                } else {
                    trim(value);
                }
            }
        } catch (Exception e) {
            // 不需要处理异常
            LOGGER.info("裁剪对象中的字符串属性失败:{}", e.toString());
        }
    }

    public static boolean isPrimitiveType(Class<?> clazz) {
        return PRIMITIVE_TYPE_SETS.contains(clazz);
    }
}
