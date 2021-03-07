package org.cloud.mae.api.user;

import java.io.Serializable;

/**
 * @Author: Mae
 * @Date: 2021/3/4 10:35 下午
 */
public class UserCredential implements Serializable {
    private static final long serialVersionUID = -936748409283745432L;

    private String username;

    private String credentialType;
    private Long userId;
}
