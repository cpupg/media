package com.sheepfly.media.common.exception;

import static com.sheepfly.media.common.exception.BusinessRunTimeException.createMessage;

/**
 * 业务异常。
 *
 * <p>所有异常都必须通过错误码定义，将错误码传入构造方法，然后返回异常。禁止不通过错误码抛出异常。
 * </p>
 *
 * @author wrote-code
 */
public class BusinessException extends Exception {
    /**
     * 错误码表。
     */
    private ErrorCode error = ErrorCode.UNEXPECT_ERROR;
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

    public BusinessException() {
        super(createMessage(ErrorCode.UNEXPECT_ERROR, null, null));
    }

    /**
     * 通过错误码创建异常。
     *
     * @param errorCode 错误码。
     */
    public BusinessException(ErrorCode errorCode) {
        super(createMessage(errorCode, null, null));
        this.error = errorCode;
    }

    /**
     * 通过错误码和异常来创建业务异常。
     *
     * @param errorCode 错误码。
     * @param exception 异常。
     */
    public BusinessException(ErrorCode errorCode, Exception exception) {
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
    public BusinessException(ErrorCode errorCode, Exception exception, Throwable cause) {
        super(createMessage(errorCode, exception, cause), cause);
        this.errorCode = errorCode.getCode();
        this.errorDescription = errorCode.getMessage();
        this.throwable = exception;
        this.cause = exception;
        this.errorMessage = createMessage(errorCode, exception, cause);
        this.error = errorCode;
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

