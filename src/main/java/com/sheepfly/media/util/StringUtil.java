package com.sheepfly.media.util;

/**
 * 字符串工具。
 *
 * @author sheepfly
 */
public class StringUtil {
    private static final String UNDERLINE = "_";

    private StringUtil() {
    }

    /**
     * 驼峰转下划线。
     *
     * @param name 驼峰命名
     *
     * @return 下划线命名
     */
    public static String camelCaseToUnderline(String name) {
        StringBuilder stringBuffer = new StringBuilder();
        for (char c : name.toCharArray()) {
            if (c > 90) {
                stringBuffer.append((char) (c - 32));
            } else {
                stringBuffer.append(UNDERLINE);
                stringBuffer.append(c);
            }
        }
        return stringBuffer.substring(1);
    }
}
