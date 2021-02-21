package org.cloud.mae.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.cloud.mae.gateway.feign.LogClient;
import org.cloud.mae.gateway.feign.Oauth2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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


}
