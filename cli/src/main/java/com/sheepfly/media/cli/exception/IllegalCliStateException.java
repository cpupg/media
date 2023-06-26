package com.sheepfly.media.cli.exception;

/**
 * 命令行状态异常。。
 *
 * <p>解析异常、执行异常等。这个异常只用来交互，捕捉后只需要输出message，不需要输出堆栈。</p>
 *
 * @author wrote-code
 * @since 0.3.0-alpha
 */
public class IllegalCliStateException extends Exception {
    public IllegalCliStateException() {
    }

    public IllegalCliStateException(String message) {
        super(message);
    }

    public IllegalCliStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCliStateException(Throwable cause) {
        super(cause);
    }

    public IllegalCliStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
