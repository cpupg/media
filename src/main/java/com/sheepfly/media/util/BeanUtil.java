package com.sheepfly.media.util;

import com.sheepfly.media.vo.common.ErrorCode;

import java.lang.reflect.Method;

/**
 * bean工具类。
 *
 * <p>处理和bean及bean之间的转换。</p>
 *
 * @author wrote-code
 * @since 2022.10.15, v0.0.1-SNAPSHOT
 */
public class BeanUtil {
    private static final String GET_CLASS = "getClass";
    private static final String GETTER_PREFIX = "get";
    private static final String SETTER_PREFIX = "set";

    private BeanUtil() {
    }

    /**
     * 将前台录入的表单数据转换为实体类，暂时不支持嵌套属性转换。
     *
     * <p>表单对象和实体对象字段完全相同。实体类中有setter且数据类中有getter的字段会被转换。</p>
     *
     * <p>此方法是为解决sonarlint检查中的java:S4684，Persistent entities should not
     * be used as arguments of "@RequestMapping" methods。</p>
     *
     * @param d 数据对象。
     * @param e 实体类。
     * @param <D> 数据类型。
     * @param <E> 实体类型。
     *
     * @return 转换后的对象。
     */
    public static <D, E> E dataToEntity(D d, E e) {
        Class<?> eClass = e.getClass();
        Method[] eMethods = eClass.getDeclaredMethods();
        Class<?> dClass = d.getClass();
        for (Method method : eMethods) {
            // setter方法名
            String setter = method.getName();
            // 方法以set开头且不是getClass的方法
            if (setter.indexOf(SETTER_PREFIX) > -1 && !GET_CLASS.equals(setter)) {
                // getter方法名
                String getter = GETTER_PREFIX + setter.substring(3);
                try {
                    // 获取数据对象中字段值
                    Method getterMethod = dClass.getMethod(getter);
                    Object obj = getterMethod.invoke(d);
                    // 调用实体类的setter设置对象值
                    method.invoke(e, obj);
                } catch (Exception exception) {
                    throw new RuntimeException(ErrorCode.DATA_TO_FORM_FAIL.name(), exception);
                }
            }
        }
        return e;
    }
}
