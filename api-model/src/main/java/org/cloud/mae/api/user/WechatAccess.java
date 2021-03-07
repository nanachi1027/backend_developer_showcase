package org.cloud.mae.api.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: Mae
 * @Date: 2021/3/4 10:37 下午
 */
@Getter
@Setter
@ToString
public class WechatAccess implements Serializable {
    private static final long serialVersionUID = 27348950594830483L;

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
}
