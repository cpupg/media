package com.sheepfly.media.dataaccess.entity.baseinterface.impl;

import com.sheepfly.media.common.util.BeanCopier;
import com.sheepfly.media.dataaccess.entity.baseinterface.Copy;

/**
 * 仅作为复制粘贴模板使用。
 *
 * <p>copy接口只作为一种功能接口，不能当作父类使用，仅作为快速复制粘贴的模板。</p>
 */
@SuppressWarnings("all")
public final class AbstractCopyImpl implements Copy {
    @Override
    public void copyFrom(Object source) {
        BeanCopier.copyFrom(source, this);
    }

    @Override
    public void copyTo(Object target) {
        BeanCopier.copyTo(this, target);
    }
}
