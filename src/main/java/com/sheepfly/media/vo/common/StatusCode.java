package com.sheepfly.media.vo.common;

/**
 * 状态码。
 *
 * @author sheepfly
 */
public enum StatusCode {
    /**
     * 成功
     */
    SUCCESS("200", "操作成功"),
    /**
     * 失败
     */
    FAILED("500", "操作失败");
    /**
     * 状态码。
     */
    private String code;
    /**
     * 提示信息。
     */
    private String message;

    StatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
