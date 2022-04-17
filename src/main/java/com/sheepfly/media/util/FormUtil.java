package com.sheepfly.media.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sheepfly.media.vo.common.ProPaginationForm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 表单工具。
 *
 * <p>用于处理前台输入的表担数据。</p>
 *
 * @author sheepfly
 */
public class FormUtil {
    private static final String GETTER_PREFIX = "get";
    private static final String GET_PAGE_SIZE = "getPageSize";
    private static final String GET_CURRENT_PAGE = "getCurrent";
    private static final String GET_CLASS = "getClass";

    private FormUtil() {
    }

    /**
     * 将查询表单转换为queryWrapper。
     *
     * <p>构造包装器时，默认情况下，表单中的所有字段都使用eq拼接。表单中的字段需要和数据库字段对应，
     * 如果两者不对应，则需要手动构造包装器。表单中的字段使用getter方法识别，如果没有getter方法，
     * 则会抛出异常或者改字段不在包装器中。</p>
     *
     * @param f 表单数据
     * @param <Q> 查询结果返回类型。
     * @param <F> 表单类型。
     *
     * @return 完整的QueryWrapper。
     */
    public static <Q, F extends ProPaginationForm> QueryWrapper<Q> formToWrapper(F f)
            throws InvocationTargetException, IllegalAccessException {
        QueryWrapper<Q> queryWrapper = new QueryWrapper<>();
        Method[] methods = f.getClass().getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith(GETTER_PREFIX)) {
                if (GET_CURRENT_PAGE.equals(name) || GET_PAGE_SIZE.equals(name) || GET_CLASS.equals(name)) {
                    continue;
                }
                Object result = method.invoke(f);
                if (result != null) {
                    queryWrapper.eq(StringUtil.camelCaseToUnderline(trimMethodPrefix(method)), result);
                }
            }
        }
        return queryWrapper;
    }

    /**
     * 去除getter和setter前缀。
     *
     * @param method 方法
     *
     * @return 去除前缀后的方法
     */
    private static String trimMethodPrefix(Method method) {
        return method.getName().substring(3);
    }
}
