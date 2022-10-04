package com.sheepfly.media.vo.common;

/**
 * 数据对象。
 *
 * @author sheepfly
 */
public class DataObject<T> {
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

    public DataObject() {
    }

    public DataObject(String statusCode, T data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }

    public static <T> DataObject success(T data) {
        return new DataObject(StatusCode.SUCCESS.getCode(), data, StatusCode.SUCCESS.getMessage());
    }

    public static <T> DataObject success(T data, String message) {
        return new DataObject(StatusCode.SUCCESS.getCode(), data, message);
    }

    public static <T> DataObject success(String message) {
        return new DataObject(StatusCode.SUCCESS.getCode(), null, message);
    }

    public static <T> DataObject fail(T data) {
        return new DataObject(StatusCode.FAILED.getCode(), data, StatusCode.SUCCESS.getMessage());
    }

    public static <T> DataObject fail(T data, String message) {
        return new DataObject(StatusCode.FAILED.getCode(), data, message);
    }

    public static <T> DataObject fail(String message) {
        return new DataObject(StatusCode.FAILED.getCode(), null, message);
    }

    public static <T> DataObject fail(ErrorCode errorCode, Object data) {
        return new DataObject(errorCode.getErrorCode(), data, errorCode.getMessage());
    }

    public static <T> DataObject fail(ErrorCode errorCode, String message, Object data) {
        return new DataObject(errorCode.getErrorCode(), data, message);
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
