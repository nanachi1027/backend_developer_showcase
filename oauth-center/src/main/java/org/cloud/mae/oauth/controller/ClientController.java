package org.cloud.mae.oauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.cloud.mae.model.log.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Mae
 * @Date: 2021/2/23 1:07 上午
 *
 * client management implementation.
 *
 */
@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientController {

    // @Autowired
    // private RedisClientDetailService clientDetailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('client:save')")
    @LogAnnotation(module = "client save")
    @PostMapping
    public void save(@RequestBody BaseClientDetails clientDetails) {
        ClientDetails client = null;
        // getAndCheckClient(clientDetails.getClientId(), false);
        if (client != null) {
            throw new IllegalArgumentException(clientDetails.getClientId() + " already exists.");
        }

        // password encrypt
        clientDetails.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
    }
}
