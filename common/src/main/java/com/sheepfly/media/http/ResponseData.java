package com.sheepfly.media.http;

import com.sheepfly.media.exception.ErrorCode;

/**
 * 数据对象。
 *
 * @author sheepfly
 */
public class ResponseData<T> {
    /**
     * 状态码。
     */
    private String statusCode;
    /**
     * 返回前台的数据。
     */
    private T data;
    /**
     * 提示信息。
     */
    private String message;

    public ResponseData() {
    }

    public ResponseData(String statusCode, T data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }

    public static <T> ResponseData success() {
        return success(ErrorCode.OPERATION_SUCCESS.getMessage());
    }

    public static <T> ResponseData success(T data) {
        return new ResponseData<>(ErrorCode.OPERATION_SUCCESS.getCode(), data,
                ErrorCode.OPERATION_SUCCESS.getMessage());
    }

    public static <T> ResponseData success(T data, String message) {
        return new ResponseData<>(ErrorCode.OPERATION_SUCCESS.getCode(), data, message);
    }

    public static <T> ResponseData success(String message) {
        return new ResponseData<T>(ErrorCode.OPERATION_SUCCESS.getCode(), null, message);
    }

    public static <T> ResponseData fail(T data) {
        return new ResponseData<>(ErrorCode.UNEXPECT_ERROR.getCode(), data, ErrorCode.UNEXPECT_ERROR.getMessage());
    }

    public static <T> ResponseData fail(T data, String message) {
        return new ResponseData<>(ErrorCode.UNEXPECT_ERROR.getCode(), data, message);
    }

    public static <T> ResponseData fail(String message) {
        return new ResponseData<>(ErrorCode.UNEXPECT_ERROR.getCode(), null, message);
    }

    public static <T> ResponseData fail(ErrorCode errorCode, Object data) {
        return new ResponseData<>(errorCode.getCode(), data, errorCode.getMessage());
    }

    public static ResponseData fail(ErrorCode errorCode) {
        return new ResponseData<>(errorCode.getCode(), null, errorCode.getMessage());
    }

    public static <T> ResponseData fail(ErrorCode errorCode, String message, Object data) {
        return new ResponseData<>(errorCode.getCode(), data, message);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
