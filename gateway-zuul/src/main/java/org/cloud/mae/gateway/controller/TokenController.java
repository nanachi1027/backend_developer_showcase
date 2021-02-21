package org.cloud.mae.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.cloud.mae.gateway.feign.LogClient;
import org.cloud.mae.gateway.feign.Oauth2Client;
import org.cloud.mae.log.Log;
import org.cloud.mae.user.constants.CredentialType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.OAuth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: Mae
 * @Date: 2021/2/22 1:32 上午
 */

@Slf4j
@RestController
public class TokenController {

    @Autowired
    private Oauth2Client oauth2Client;

    @Autowired
    private LogClient logClient;

    /**
     * system login by user name.
     * oauth2 password mode to retrieve access_token & refresh_token
     */
    @PostMapping("/sys/login")
    public Map<String, Object> login(String username, String password) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(OAuth2Utils.GRANT_TYPE, "password");
        parameters.put(OAuth2Utils.CLIENT_ID, "system");
        parameters.put("client_secret", "system");
        parameters.put(OAuth2Utils.SCOPE, "app");
        parameters.put("username", username + "|" + CredentialType.USERNAME.name());
        parameters.put("password", password);

        Map<String, Object> tokenInfo = oauth2Client.postAccessToken(parameters);
        saveLoginLog(username, "UsernamePassowrdLogin");

        return tokenInfo;
    }

    /**
     * sms login
     *
     * @param phone
     * @param key
     * @param code
     * @return
     */
    @PostMapping("/sys/login-sms")
    public Map<String, Object> smsLogin(String phone, String key, String code) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(OAuth2Utils.GRANT_TYPE, "password");
        parameters.put(OAuth2Utils.CLIENT_ID, "system");
        parameters.put("client_secret", "system");
        parameters.put(OAuth2Utils.SCOPE, "app");
        parameters.put("username", phone + "|" + CredentialType.PHONE.name() + "|" + key + "|" + code + "|"
                + DigestUtils.md5Hex(key + code));
        parameters.put("password", phone);

        Map<String, Object> tokenInfo = oauth2Client.postAccessToken(parameters);
        saveLoginLog(phone, "phonesmslogin");

        return tokenInfo;
    }

    /**
     * refresh token
     *
     * @param refreshToken
     * @return
     */
    @PostMapping("/sys/refresh_token")
    public Map<String, Object> refreshToken(String refreshToken) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(OAuth2Utils.GRANT_TYPE, "refresh_token");
        parameters.put(OAuth2Utils.CLIENT_ID, "system");
        parameters.put("client_secret", "system");
        parameters.put(OAuth2Utils.SCOPE, "app");
        parameters.put("refresh_token", refreshToken);

        return oauth2Client.postAccessToken(parameters);
    }

    /**
     * logout
     *
     * @param accessToken
     */
    @GetMapping("/sys/logout")
    public void logout(String accessToken, @RequestHeader(required = false, value = "Authorization") String token) {
        if (StringUtils.isBlank(accessToken)) {
            accessToken = token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
        }
        oauth2Client.removeAccessToken(accessToken);
    }

    /**
     * User login log info.
     *
     * @param username
     * @param remark
     */
    private void saveLoginLog(String username, String remark) {
        log.info("{} login", username);
        CompletableFuture.runAsync(() -> {
            try {
                Log log = Log.builder().username(username).module("login").remark(remark).createTime(new Date())
                        .build();
                logClient.save(log);
            } catch (Exception e) {
                log.error("Failed to save log login log ", e);
            }
        });
    }
}
