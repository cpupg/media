package com.sheepfly.media.dataaccess.entity.baseinterface;

/**
 * bean复制方法。
 *
 * <p>实现此接口的类可以进行bean拷贝，建议继承CopyImpl</p>
 */
public interface Copy {
    void copyFrom(Object source);

    void copyTo(Object target);
}
