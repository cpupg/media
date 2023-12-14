package com.sheepfly.media.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 日志拦截器。
 *
 * <p>请求进来后输出请求参数，同时生成requestId。请求完成后输出相应参数。</p>
 * @author wrote-code
 * @since 0.0.4-alpha
 */
public class LoggerInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        MDC.put("requestId", UUID.randomUUID().toString());
        log.info("start request [{}] {}", MDC.get("requestId"), request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        log.info("finish request [{}] {}", MDC.get("requestId"), request.getRequestURI());
    }
}
