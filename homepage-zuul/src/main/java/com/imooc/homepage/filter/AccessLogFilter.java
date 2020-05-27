package com.imooc.homepage.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义过滤器打印请求 uri / 时间
 */
@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * post是1000  之前调用 1000 - 1
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //前置拦截的变量
        long startTime = (Long)ctx.get("startTime");
        //拿到请求的uri
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        //请求响应的时间
        long duration = System.currentTimeMillis() - startTime;
        log.info("uri:{},duration:{}ms",uri,duration / 100);
        return null;
    }
}
