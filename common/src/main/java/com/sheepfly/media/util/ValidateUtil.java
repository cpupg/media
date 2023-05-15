package com.sheepfly.media.util;

/**
 * n
 * 字段验证工具。
 *
 * <p>通过校验返回true，不通过返回false。</p>
 *
 * @author wrote-code
 */
public class ValidateUtil {
    public static boolean isEmptyString(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isEmptyString(Object object) {
        return isEmptyString(object.toString());
    }
}
