package com.sheepfly.media.common.exception;

/**
 * 系统错误码。
 *
 * <p>错误码一共8位。第一位是26个大写字母，用来表示错误来源。第二位表示操作结果，1表示成功，0表示失
 * 败。3-5位是模块代码，从100开始递增。5-8位是错误序号，从1开始递增。模块代码不区分子模块和父模块，
 * 也就是说，若存在以下模块代码：101，102，121，122，则这四个模块不一定分别属于两个父模块，他们可
 * 能都是父模块，也可能是四个子模块，也可能其中一个是父模块，另外三个是这个父模块的子模块。在定义
 * 变量时，为了避免出现一行代码半行变量，应该尽量使用简明扼要的缩写。</p>
 *
 * <p>错误码有以下特殊情况。</p>
 * <ol>
 * <li>8位错误码都是0：表示操作成功。</li>
 * <li>错误码3-5位小于200，表示和模块无关的通用错误。200开始表示业务错误。</li>
 * <li>E0999xxx：表示未知错误，此错误码由全局异常处理器抛出，xxx从100开始。</li>
 * </ol>
 *
 * <p>模块代码如下：</p>
 * <ul>
 * <li>[100]:公共</li>
 * <li>[200]: SITE-网站</li>
 * <li>[201]: AUTHOR-作者</li>
 * <li>[202]: RES-资源</li>
 * <li>[203]: DIRECTORY-目录</li>
 * <li>[204]: TAG-标签</li>
 * <li>[205]: ALBUM-专辑</li>
 * <li>[300]: FILE-文件</li>
 * </ul>
 *
 * @author wrote-code
 * @since 0.0.1-SNAPSHOT
 */

public enum ErrorCode {
    // region 模块[module][prefix]开始[999]
    // A-------1---------000----000
    // 错误类型-操作结果---模块代码-错误序号
    // 大写字母-1成功0失败-
    // 正文。新模块补在===前面
    // endregion


    OPERATION_SUCCESS("00000000", "操作成功"),


    // region 模块[网站][SITE]开始[200]
    SITE_NAME_URL_CANT_BE_NULL("E0200001", "网站名称和网站地址不能为空"),
    URL_IS_ILLEGAL("E0200002", "网站地址不合法"),
    SITE_ID_CANT_NULL("E0200003", "网站标识不能为空"),
    SITE_CANT_BE_DELETE("E0200004", "网站下有作者，不能删除网站"),
    // endregion

    // region 模块[作者][AUTHOR]开始[201]
    AUTHOR_SITE_CANT_BE_NULL("E0201001", "注册网站不能为空"),
    AUTHOR_ID_AND_NAME_CANT_NULL("E0201002", "用户名和用户id不能同时为空"),
    AUTHOR_ID_CANT_BE_NULL("E0201002", "作者标识不能为空"),
    AUTHOR_ASSOCIATE_RESOURCE("E0201003", "作者下由关联的资源"),
    // endregion

    // region 模块[资源][RES]开始[202]
    RESOURCE_MKDIR_FAIL("E0202001", "创建目录失败，请重试"),
    RES_ADD_FAIL_BY_DUPLICATED("E0202002", "资源重复，添加失败"),
    RES_TAG_NAME_CANT_NULL("E0202003", "标签名称不能为空"),
    RES_TAG_NOT_FOUND("E0202004", "标签不存在"),
    RES_DONT_HAVE_THIS_TAG("E0202005", "指定资源没有设置此标签"),
    RES_RA_RES_NOT_EXISTS("E0202006", "无法为不存在的资源设置专辑"),
    RES_RA_ALBUM_EXISTS("E0202007", "无法为资源设置不存在的专辑"),
    RES_RA_NOT_REPEATED_AR("E0202008", "不可重复设置专辑"),
    RES_NOT_FOUND("E0202009", "资源不存在"),
    // endregion

    // region 模块[目录][DIRECTORY]开始[203]
    DIRECTORY_MUST_BE_ABSOLUTE("E0203001", "目录格式有错误，必须是绝对路径"),
    DIRECTORY_ILLEGAL_DRIVER("E0203002", "目录格式错误，不正确的Windows盘符"),
    DIRECTORY_EMPTY_ROOT_DIRECTORY("E0203003", "根目录不存在，请检查数据库"),
    // endregion

    // region 模块[标签][TAG]开始[204]
    TAG_NAME_CANT_BE_EMPTY("E0204001", "标签名不能为空"),
    TAG_NAME_TOO_LONG("E0204001", "标签名太长"),
    TAG_RES_ID_CANT_BE_NULL("E0204002", "缺少资源标识"),
    TAG_RES_ID_AND_TAG_ID_CANT_BE_NULL("E0204003", "资源标识或标签标识为空"),
    // endregion

    // region 模块[专辑][ALBUM]开始[205]
    ALBUM_REPEATED_ALBUM("E0205001", "不能添加重复专辑"),
    ALBUM_EMPTY_NAME("E0205002", "专辑名称不能为空"),
    ALBUM_NAME_TOO_LONG("E0205003", "专辑名称过长"),
    ALBUM_SELECT_MODAL_LOST_RESOURCE("E0205004", "查询表单弹框中表格时必须包含资源参数"),
    // endregion

    // region 模块[评分][RATE]开始[206]
    // 正文。新模块补在===前面
    // endregion

    // region 模块[评分][RATE]开始[207]
    // A-------1---------000----000
    // 错误类型-操作结果---模块代码-错误序号
    // 大写字母-1成功0失败-
    RATE_RANGE_NOT_ALLOW("E0207001", "评分必须是0-10的整数"),
    // endregion

    // region 模块[收藏][collect]开始[208]
    COLLECT_NOT_FOUND("E0208001", "收藏夹不存在"),
    COLLECT_CONTAIN_RESOURCE("E0208002", "收藏夹已经包含资源"),
    COLLECT_EXISTS("E0208003", "收藏夹已存在"),
    // endregion

    // region 模块[文件][FILE]开始[300]
    FILE_EMPTY_BUSINESS_CODE_TYPE("E0206001", "缺少业务代码和业务类型"),
    FILE_EMPTY_BUSINESS_CODE("E0206002", "缺少业务代码"),
    FILE_EMPTY_FILENAME("E0206003", "文件名不能为空"),
    FILE_NOT_FOUND_ERROR("E0206004", "文件记录不存在"),
    // endregion

    // region 模块[公共][]开始[100]
    DELETE_NOT_EXIST_DATA("E0100001", "要删除的数据不存在"),
    SAVE_DUPLICATED_DATA("E0100002", "重复的数据"),
    REWRITE_FILE("E0100003", "覆盖文件"),
    FILE_NOT_FOUND("E0100004", "文件不存在"),
    DATA_TO_FORM_FAIL("E0100005", "转换失败"),
    REQUEST_VALUE_IS_LOST("E0100006", "缺少请求参数"),
    PAGINATION_ERROR("E0100007", "缺少分页参数"),
    LOGIC_DELETE_CREATE_FAIL("E0100008", "逻辑删除失败：创建实例失败"),
    LOGIC_DELETE_NOT_SUPPORT("E0100009", "目标实例不支持逻辑删除"),
    UNEXPECT_ERROR("E0999100", "未知错误，请联系管理员"),
    VALIDATE_ERROR("E0999101", "输入参数有误"),
    BATCH_UPDATE_PARAM_LOSE("E0999102", "批量更新/删除缺少条件"),
    BATCH_UPDATE_COUNT_CONFLICT("E0999103", "批量更新/删除数量和实际数量不一致"),
    BATCH_UPDATE_CONDITION_LOST("E0999104", "批量更新缺少更新条件"),
    BATCH_UPDATE_CONTENT_LOST("E0999105", "批量更新缺少更新内容"),
    // endregion

    ;
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
