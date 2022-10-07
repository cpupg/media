package com.sheepfly.media.vo.common;

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

    public static <T> ResponseData success(T data) {
        return new ResponseData(StatusCode.SUCCESS.getCode(), data, StatusCode.SUCCESS.getMessage());
    }

    public static <T> ResponseData success(T data, String message) {
        return new ResponseData(StatusCode.SUCCESS.getCode(), data, message);
    }

    public static <T> ResponseData success(String message) {
        return new ResponseData(StatusCode.SUCCESS.getCode(), null, message);
    }

    public static <T> ResponseData fail(T data) {
        return new ResponseData(StatusCode.FAILED.getCode(), data, StatusCode.SUCCESS.getMessage());
    }

    public static <T> ResponseData fail(T data, String message) {
        return new ResponseData(StatusCode.FAILED.getCode(), data, message);
    }

    public static <T> ResponseData fail(String message) {
        return new ResponseData(StatusCode.FAILED.getCode(), null, message);
    }

    public static <T> ResponseData fail(ErrorCode errorCode, Object data) {
        return new ResponseData(errorCode.getErrorCode(), data, errorCode.getMessage());
    }

    public static ResponseData fail(ErrorCode errorCode) {
        return new ResponseData(errorCode.getErrorCode(), null, errorCode.getMessage());
    }

    public static <T> ResponseData fail(ErrorCode errorCode, String message, Object data) {
        return new ResponseData(errorCode.getErrorCode(), data, message);
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
