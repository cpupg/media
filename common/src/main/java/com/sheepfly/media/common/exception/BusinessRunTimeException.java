package com.sheepfly.media.common.exception;

/**
 * 运行时会出现的业务异常。
 *
 * @author wrote-code
 */
public class BusinessRunTimeException extends RuntimeException {
    /**
     * 错误码表。
     */
    private ErrorCode error;
    /**
     * 错误码。
     */
    private String errorCode = ErrorCode.UNEXPECT_ERROR.getCode();
    /**
     * 错误描述。
     */
    private String errorDescription = ErrorCode.UNEXPECT_ERROR.getMessage();
    /**
     * 错误消息。
     */
    private String errorMessage = createMessage(ErrorCode.UNEXPECT_ERROR, null, null);
    /**
     * 异常。
     */
    private Throwable throwable;
    /**
     * 异常原因。
     */
    private Throwable cause;

    public BusinessRunTimeException() {
        super(createMessage(ErrorCode.UNEXPECT_ERROR, null, null));
    }

    /**
     * 通过错误码创建异常。
     *
     * @param errorCode 错误码。
     */
    public BusinessRunTimeException(ErrorCode errorCode) {
        super(createMessage(errorCode, null, null));
    }

    /**
     * 通过错误码和异常来创建业务异常。
     *
     * @param errorCode 错误码。
     * @param exception 异常。
     */
    public BusinessRunTimeException(ErrorCode errorCode, Exception exception) {
        super(createMessage(errorCode, exception, null), exception);
        this.errorCode = errorCode.getCode();
        this.errorDescription = errorCode.getMessage();
        this.throwable = exception;
        this.errorMessage = createMessage(errorCode, exception, null);
        this.error = errorCode;
    }

    /**
     * 通过错误码和自定义异常来创建异常。
     *
     * @param errorCode 错误码。
     * @param exception 异常。
     * @param cause 原因。
     */
    public BusinessRunTimeException(ErrorCode errorCode, Exception exception, Throwable cause) {
        super(createMessage(errorCode, exception, cause), cause);
        this.errorCode = errorCode.getCode();
        this.errorDescription = errorCode.getMessage();
        this.throwable = exception;
        this.cause = exception;
        this.errorMessage = createMessage(errorCode, exception, cause);
        this.error = errorCode;
    }

    /**
     * 创建错误消息。
     *
     * <p>错误格式如下：[错误码] 错误描述，异常，原因。</p>
     *
     * @param errorCode 错误码。
     * @param e 异常。
     * @param cause 原因。
     *
     * @return 错误消息。
     */
    public static String createMessage(ErrorCode errorCode, Exception e, Throwable cause) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[").append(errorCode.getCode()).append("] ").append(errorCode.getMessage());
        if (e != null) {
            stringBuffer.append("，异常描述：").append(e.getMessage());
        }
        if (cause != null) {
            stringBuffer.append("。错误描述：").append(cause.getMessage());
        }
        return stringBuffer.toString();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public ErrorCode getError() {
        return error;
    }
}
