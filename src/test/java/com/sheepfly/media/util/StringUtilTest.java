package com.sheepfly.media.util;

import org.junit.Test;

public class StringUtilTest {
    @Test
    public void testCamelCaseToUnderline() {
        String s = "StringUtilTest";
        System.out.println(StringUtil.camelCaseToUnderline(s));
    }
}