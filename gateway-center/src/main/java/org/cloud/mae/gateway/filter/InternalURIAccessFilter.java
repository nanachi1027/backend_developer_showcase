package org.cloud.mae.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Mae
 * @Date: 2021/2/22 1:13 上午
 * <p>
 * Platform's inner url access filter.
 * <p>
 * Internal uri's access doesn't need gateway allowance, only allowed among inner micro-services.
 */
public class InternalURIAccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        return PatternMatchUtils.simpleMatch("*-anon/internal*", request.getRequestURI());
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        requestContext.setResponseBody(HttpStatus.FORBIDDEN.getReasonPhrase());
        requestContext.setSendZuulResponse(false);

        return null;
    }
}
