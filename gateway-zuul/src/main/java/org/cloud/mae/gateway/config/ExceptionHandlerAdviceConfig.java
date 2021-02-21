package org.cloud.mae.gateway.config;

import com.alibaba.fastjson.JSONObject;
import com.netflix.client.ClientException;
import com.netflix.discovery.util.StringUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Mae
 * @Date: 2021/2/22 2:03 上午
 * <p>
 * API invoke exception info parser class.
 */

@Slf4j
@RestController
public class ExceptionHandlerAdviceConfig {
    /**
     * FeignClient invoke exception and http status codes info handler.
     *
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler({FeignException.class})
    public Map<String, Object> feignExceptionHandler(FeignException exception, HttpServletResponse response) {
        int httpStatus = exception.status();
        if (httpStatus >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            log.error("faignClient invoke error ", exception);
        }

        Map<String, Object> data = new HashMap<>();
        String msg = exception.getMessage();

        if (!StringUtils.isEmpty(msg)) {
            int index = msg.indexOf("\n");
            if (index > 0) {
                String str = msg.substring(index);
                if (!StringUtils.isEmpty(str)) {
                    JSONObject json = JSONObject.parseObject(str.trim());
                    data.putAll(json);
                }
            }
        }
        if (data.isEmpty()) {
            data.put("message", msg);
        }
        data.put("code", httpStatus + "");
        response.setStatus(httpStatus);
        return data;
    }

    /**
     * illegal argument exception and http status info handler.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> badRequestException(IllegalArgumentException exception) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", HttpStatus.BAD_REQUEST.value());
        data.put("message", exception.getMessage());

        return data;
    }

    /**
     * ClientException and Throwable info handler.
     *
     * @param throwable
     * @return
     */
    @ExceptionHandler({ClientException.class, Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> serverException(Throwable throwable) {
        log.error("server internal error ", throwable);
        Map<String, Object> data = new HashMap<>();
        data.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        data.put("message", "server internal error!");
        return data;
    }
}
