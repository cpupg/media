package com.sheepfly.media.vo.common;

/**
 * 系统错误码。
 *
 * <p>错误码一共8位。第一位是26个大写字母，用来表示错误来源。第二位表示操作结果，1表示成功，0表示失
 * 败。3-5位是模块代码，从100开始递增。5-8位是错误序号，从1开始递增。模块代码不区分子模块和父模块，
 * 也就是说，若存在以下模块代码：101，102，121，122，则这四个模块不一定分别属于两个父模块，他们可
 * 能都是父模块，也可能是四个子模块，也可能其中一个是父模块，另外三个是这个父模块的子模块。</p>
 *
 * <p>错误码有以下特殊情况。</p>
 * <ol>
 * <li>8位错误码都是0：表示操作成功。</li>
 * <li>错误码3-5位小于200，表示和模块无关的通用错误。200开始表示业务错误。</li>
 * <li>Z0999999：表示未知错误，此错误码由全局异常处理器抛出。</li>
 * </ol>
 *
 * <p>目前的错误类型有以下几种：</p>
 * <ul>
 * <li>A:用户填写的表单错误。</li>
 * <li>B:表单校验错误。</li>
 * <li>C:业务异常。大多数异常都可以作为业务异常。</li>
 * <li>Z:未知错误，通常是没有捕获的异常。</li>
 * </ul>
 *
 * <p>模块代码如下：</p>
 * <ul>
 * <li>100:用户未登录</li>
 * <li>101:公用错误</li>
 * <li>103:系统异常</li>
 * </ul>
 *
 * @author wrote-code
 * @since 0.0.1-SNAPSHOT
 */

public enum ErrorCode {
    /**
     * 操作成功。
     */
    OPERATION_SUCCESS("00000000", "操作成功"),
    /**
     * 用户未登录。
     */
    USER_NOT_LOG_IN("C0100001", "用户未登录"),
    // 网站模块开始200
    /**
     * 网站名称和网站地址不能为空。
     */
    SITE_NAME_URL_CANT_BE_NULL("C0200001", "网站名称和网站地址不能为空"),
    /**
     * 网站地址不合法。
     */
    URL_IS_ILLEGAL("C0200002", "网站地址不合法"),
    SITE_ID_CANT_NULL("C0200003", "网站标识不能为空"),
    SITE_CANT_BE_DELETE("C0200004", "网站下有作者，不能删除网站"),
    // 网站模块结束200

    // 作者模块开始201
    /**
     * 注册网站为空。
     */
    AUTHOR_SITE_CANT_BE_NULL("C0201001", "注册网站为空"),
    /**
     * 用户名和用户id不能同时为空。
     */
    AUTHOR_ID_AND_NAME_CANT_NULL("C0201002", "用户名和用户id不能同时为空"),
    /**
     * 作者标识不能为空。
     */
    AUTHOR_ID_CANT_BE_NULL("C0201002", "作者标识不能为空"),
    AUTHOR_ASSOCIATE_RESOURCE("C0201003", "作者下由关联的资源"),
    // 作者模块结束201

    // 公共模块开始101
    /**
     * 要删除的数据不存在。
     */
    DELETE_NOT_EXIST_DATA("C0101001", "要删除的数据不存在"),
    /**
     * 重复的数据。
     */
    SAVE_DUPLICATED_DATA("C0101002", "重复的数据"),
    /**
     * 覆盖文件。
     */
    REWRITE_FILE("C0101003", "覆盖文件"),
    /**
     * 文件不存在。
     */
    FILE_NOT_FOUND("C0101004", "文件不存在"),
    /**
     * 转换失败。
     */
    DATA_TO_FORM_FAIL("C0101005", "转换失败"),
    /**
     * 缺少请求参数。
     */
    REQUEST_VALUE_IS_LOST("C0101006", "缺少请求参数"),
    // 公用错误结束101

    /**
     * 未知错误，请联系管理员。
     */
    UNEXPECT_ERROR("Z0999999", "未知错误，请联系管理员"),
    VALIDATE_ERROR("Z0999998", "输入参数有误");

    private final String code;
    private final String message;

    ErrorCode(String errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
