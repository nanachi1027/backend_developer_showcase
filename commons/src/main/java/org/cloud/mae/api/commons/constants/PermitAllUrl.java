package org.cloud.mae.api.commons.constants;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Mae
 * @Date: 2021/2/22 12:38 上午
 * <p>
 * URL access permission implement.
 */
public class PermitAllUrl {
    private static final String[] ENDPOINTS = {"/actuator/health", "/actuator/env", "/actuator/metrics/**", "/actuator/trace", "/actuator/dump",
            "/actuator/jolokia", "/actuator/info", "/actuator/logfile", "/actuator/refresh", "/actuator/flyway", "/actuator/liquibase",
            "/actuator/heapdump", "/actuator/loggers", "/actuator/auditevents", "/actuator/env/PID", "/actuator/jolokia/**",
            "/v2/api-docs/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**"};

    /**
     * Retrieve all the permits urls.
     *
     * @param urls apply access urls.
     * @return all the urls which are allowed to access by the platform.
     */
    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return ENDPOINTS;
        }

        Set<String> set = new HashSet<>();
        Collections.addAll(set, ENDPOINTS);
        Collections.addAll(set, urls);

        return set.toArray(new String[set.size()]);
    }
}
