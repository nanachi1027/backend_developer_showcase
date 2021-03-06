package org.cloud.mae.model.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.cloud.mae.model.gateway.feign.BackendClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Mae
 * @Date: 2021/2/22 1:14 上午
 * <p>
 * Black IP Access Filter class.
 * <p>
 * In our project, we update blacklist ip address by cron scheduler to gateway side
 * instead of retrieving from other micro-services.
 * <p>
 */
@Slf4j
public class BlackIPAccessFilter extends ZuulFilter {

    /**
     * black ip list
     */
    private Set<String> blackIPS = new HashSet<>();

    @Autowired
    private BackendClient backendClient;


    /**
     * Fetch black list by scheduler.
     */
    @Scheduled(cron = "${cron.black-ip}")
    public void syncBlackIPList() {
        try {
            Set<String> list = backendClient.findAllBlackIPs(Collections.emptyMap());
            blackIPS = list;
        } catch (Exception e) {
            log.error("Failed to sync black list to gateway, caused by {}", e);
        }
    }


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
        if (blackIPS.isEmpty()) {
            return false;
        }

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String srcIP = getIPAddress(request);

        return blackIPS.contains(srcIP);
    }

    /**
     * Parse request ip address from request body.
     *
     * @param request
     * @return
     */
    private static String getIPAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        requestContext.setResponseBody("black ip");
        requestContext.setSendZuulResponse(false);

        return null;
    }
}
