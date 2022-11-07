package com.sheepfly.media.exception;

import com.sheepfly.media.vo.common.ErrorCode;
import com.sheepfly.media.vo.common.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器。
 *
 * @author wrote-code
 */
@ControllerAdvice
public class BusinessExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    /**
     * 处理捕捉到的业务异常。
     *
     * @param e 异常。
     *
     * @return 通用返回结果。
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseData<BusinessException> handleBusinessException(BusinessException e) {
        log.error("发生业务异常", e);
        return ResponseData.fail(e.getError());
    }

    /**
     * 解析捕捉到的非业务异常（未知异常）。
     *
     * @param e 异常。
     *
     * @return 通用返回结果。
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData<Exception> handleException(Exception e) {
        log.error("发生未知异常", e);
        return ResponseData.fail(ErrorCode.UNEXPECT_ERROR);
    }

    @ExceptionHandler({MissingRequestValueException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseData<Exception> handleMissingRequestValueException(Exception e) {
        log.error("缺少请求参数", e);
        return ResponseData.fail(ErrorCode.REQUEST_VALUE_IS_LOST);
    }
}
