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
 * <li>B:没有查询到数据。</li>
 * <li>C:重复插入数据（主键冲突，或业务主键冲突）。</li>
 * <li>D:删除不存在的数据。</li>
 * <li>E:查询条件错误。</li>
 * <li>F:更新不存在的数据。</li>
 * <li>G:用户输入有遗漏。</li>
 * <li>H:用户没有输入必需参数。</li>
 * <li>I:操作数据库失败。此处不限于select语句，所有数据库操作都归于此类。</li>
 * <li>J:文件不存在，可能是目录，也可能是文件。</li>
 * <li>K:尝试覆盖一个已存在的文件。</li>
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
 * </ul>
 */
public enum ErrorCode {
    OPERATION_SUCCESS("00000000", "操作成功"),
    USER_NOT_LOG_IN("G0100001", "用户未登录"),
    DELETE_NOT_EXIST_DATA("D0001001", "要删除的数据不存在"),
    SAVE_DUPLICATED_DATA("C0001002", "重复的数据"),
    REWRITE_FILE("K0002001", "覆盖文件"),
    FILE_NOT_FOUND("K0002002", "文件不存在"),
    UNEXPECT_ERROR("Z0999999", "未知错误，请联系管理员");
    private String errorCode;
    private String message;

    ErrorCode(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
