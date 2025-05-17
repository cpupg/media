package com.sheepfly.media.common.http;

import com.sheepfly.media.common.exception.ErrorCode;

import java.io.Serializable;

/**
 * 数据对象。
 *
 * @author sheepfly
 */
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public static <T> ResponseData<T> success() {
        return success(ErrorCode.OPERATION_SUCCESS.getMessage());
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<>(ErrorCode.OPERATION_SUCCESS.getCode(), data,
                ErrorCode.OPERATION_SUCCESS.getMessage());
    }

    public static <T> ResponseData<T> success(T data, String message) {
        return new ResponseData<>(ErrorCode.OPERATION_SUCCESS.getCode(), data, message);
    }

    public static <T> ResponseData<T> success(String message) {
        return new ResponseData<T>(ErrorCode.OPERATION_SUCCESS.getCode(), null, message);
    }

    public static <T> ResponseData<T> fail(T data) {
        return new ResponseData<>(ErrorCode.UNEXPECT_ERROR.getCode(), data, ErrorCode.UNEXPECT_ERROR.getMessage());
    }

    public static <T> ResponseData<T> fail(T data, String message) {
        return new ResponseData<>(ErrorCode.UNEXPECT_ERROR.getCode(), data, message);
    }

    public static <T> ResponseData<T> fail(String message) {
        return new ResponseData<>(ErrorCode.UNEXPECT_ERROR.getCode(), null, message);
    }

    public static <T> ResponseData<T> fail(ErrorCode errorCode) {
        return new ResponseData<>(errorCode.getCode(), null, errorCode.getMessage());
    }

    public static <T> ResponseData<T> fail(ErrorCode errorCode, String message) {
        return new ResponseData<>(errorCode.getCode(), null, message);
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
