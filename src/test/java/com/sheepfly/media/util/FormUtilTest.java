package com.sheepfly.media.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sheepfly.media.form.querylist.ResourceVoForm;
import com.sheepfly.media.form.querylist.SiteForm;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FormUtilTest {
    @Test
    public void testFormToWrapper() throws InvocationTargetException, IllegalAccessException {
        SiteForm siteForm = new SiteForm();
        siteForm.setSiteName("hello");
        QueryWrapper<Object> wrapper = FormUtil.formToWrapper(siteForm);
        System.out.println(wrapper.getTargetSql());
    }

    @Test
    public void testTrimMethodPrefix() throws NoSuchMethodException {
        Method getFilename = ResourceVoForm.class.getMethod("getFilename");
        Object result = ReflectionTestUtils.invokeMethod(FormUtil.class, "trimMethodPrefix", getFilename);
        System.out.println(result);
    }
}