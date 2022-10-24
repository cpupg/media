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
 * <li>错误码3-5位小于100，表示和模块无关的通用错误。</li>
 * <li>Z0999999：表示未知错误，此错误码由全局异常处理器抛出。</li>
 * </ol>
 *
 * <p>目前的错误类型有以下几种：</p>
 * <ul>
 * <li>A:用户填写的表单错误。</li>
 * <li>B:表单校验错误。</li>
 * <li>C:业务异常。大多数异常都可以作为业务异常。</li>
 * <li>D:io错误，如文件不存在，目录不存在等。可以是手动判断，也可以是运行异常。</li>
 * <li>E:工具类调用异常。</li>
 * <li>Z:未知错误，通常是没有捕获的异常。</li>
 * </ul>
 *
 * <p>模块代码如下：</p>
 * <ul>
 * <li>100:用户登录</li>
 * <li>101:站点管理</li>
 * <li>102:资源管理</li>
 * <li>103:作者管理</li>
 * <li>104:标签管理</li>
 * <li>105:专辑管理</li>
 * <li>106:工具类错误</li>
 * <li>999:公共模块</li>
 * </ul>
 */
@SuppressWarnings({"AlibabaClassMustHaveAuthor", "AlibabaEnumConstantsMustHaveComment"})
public enum ErrorCode {
    /**
     * 操作成功。
     */
    OPERATION_SUCCESS("00000000", "操作成功"),
    /**
     * 用户未登录。
     */
    USER_NOT_LOG_IN("C0100001", "用户未登录"),
    // 网站模块开始
    /**
     * 网站名称和网站地址不能为空。
     */
    SITE_NAME_URL_CANT_BE_NULL("C0101001", "网站名称和网站地址不能为空"),
    /**
     * 网站地址不合法。
     */
    URL_IS_ILLEGAL("C0101002", "网站地址不合法"),
    SITE_ID_CANT_NULL("C0101003", "网站标识不能为空"),
    // 网站模块结束

    // 作者模块开始
    // 作者模块结束
    /**
     * 要删除的数据不存在。
     */
    DELETE_NOT_EXIST_DATA("C0999001", "要删除的数据不存在"),
    /**
     * 重复的数据。
     */
    SAVE_DUPLICATED_DATA("C0999002", "重复的数据"),
    /**
     * 覆盖文件。
     */
    REWRITE_FILE("D0002001", "覆盖文件"),
    /**
     * 文件不存在。
     */
    FILE_NOT_FOUND("D0002002", "文件不存在"),
    /**
     * 转换失败。
     */
    DATA_TO_FORM_FAIL("E0106001", "转换失败"),
    /**
     * 未知错误，请联系管理员。
     */
    UNEXPECT_ERROR("Z0999999", "未知错误，请联系管理员");
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
