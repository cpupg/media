package com.sheepfly.media.common.exception;

/**
 * 通用异常，不想定义错误码的时候可以用这个。
 *
 * @author wrote-code
 */
public class CommonException extends Exception {
    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    protected CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
