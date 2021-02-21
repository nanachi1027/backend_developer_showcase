package org.cloud.mae.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @Author: Mae
 * @Date: 2021/2/22 12:37 上午
 * <p>
 * Web paging implementation class.
 */
@Slf4j
public class PageUtil {
    // paging parameter, start location, begin from 0
    public static final String START = "start";

    // paging parameter, how many lines each page
    public static final String LENGTH = "length";

    /**
     * paging parameter validation and transfer function.
     * <p>
     * In mybatis we implement paging feature by
     * `limit #{start, JdbcType=INTEGER}, ${length, JdbcType=INTEGER}`
     * <p>
     * In this method, we validate whether the parameters are illegal.
     * If param values are illegal then transfer parameters value into integer.
     *
     * @param params   paging parameters.
     * @param required paging param is optional or not.
     */
    public static void pageParamConvert(Map<String, Object> params, boolean required) {
        if (required) {
            if (params == null || !params.containsKey(START) || !params.containsKey(LENGTH)) {
                throw new IllegalArgumentException("Check your paging params, " + START + ", " + LENGTH);
            }

            if (!CollectionUtils.isEmpty(params)) {
                if (params.containsKey(START)) {
                    Integer start = MapUtils.getInteger(params, START);
                    if (start < 0) {
                        log.error("start: {} reset to 0", start);
                        start = 0;
                    }
                    params.put(START, start);
                }

                if (params.containsKey(LENGTH)) {
                    Integer length = MapUtils.getInteger(params, LENGTH);
                    if (length < 0) {
                        log.error("length: {}, reset into 0", length);
                        length = 0;
                    }
                    params.put(LENGTH, length);
                }
            }
        }
    }
}
