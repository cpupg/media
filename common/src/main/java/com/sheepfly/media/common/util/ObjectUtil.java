package com.sheepfly.media.common.util;

import com.sheepfly.media.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Random;
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
    private static final Random RANDOM = new Random();

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
                // 只裁剪字符串，别的类型不需要管
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

    /**
     * 判断是否是基本类型和包装类型。
     *
     * @param clazz 类型。
     *
     * @return 是基本类型返回true。
     */
    public static boolean isPrimitiveType(Class<?> clazz) {
        return PRIMITIVE_TYPE_SETS.contains(clazz);
    }

    /**
     * 为指定的 Bean 填充随机值。
     */
    public static <T> T createBean(Class<T> clazz) {
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
        try {
            T t = clazz.getConstructor().newInstance();
            for (PropertyDescriptor pd : pds) {
                Class<?> type = pd.getReadMethod().getReturnType();
                if ("class".equals(pd.getName())) {
                    continue;
                }
                Object o = getDefaultValue(type);
                pd.getWriteMethod().invoke(t, o);
            }
            return t;
        } catch (Exception e) {
            throw new CommonException("创建对象失败", e);
        }
    }

    /**
     * 生成默认值。
     *
     * @param type 类型。
     *
     * @return 默认值。
     */
    public static <T> T getDefaultValue(Class<T> type) {
        // 生成默认值0 ~ 255
        Object o = null;
        int value = RANDOM.nextInt(256);
        if (type == int.class || type == Integer.class)
            o = value;
        if (type == Long.TYPE || type == Long.class)
            o = (long) value;
        if (type == Short.TYPE || type == Short.class)
            o = (short) value;
        if (type == Double.TYPE || type == Double.class)
            o = value + 0.0d;
        if (type == Float.TYPE || type == Float.class)
            o = value + 0.0f;
        if (type == Boolean.TYPE || type == Boolean.class)
            o = value % 2 == 0;
        if (type == Character.TYPE || type == Character.class)
            // 可见ascii 33-126
            o = (char) (33 + (value % 94));
        if (type == String.class) {
            // 可见ascii 33-126
            char c = (char) (33 + RANDOM.nextInt(94));
            o = String.valueOf(c);
        }
        // noinspection unchecked
        return (T) o;
    }
}
