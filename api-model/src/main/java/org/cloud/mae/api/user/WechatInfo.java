package org.cloud.mae.api.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: Mae
 * @Date: 2021/3/4 10:39 下午
 * <p>
 * wechat appid and secret object.
 */
@Getter
@Setter
@ToString
public class WechatInfo implements Serializable {
    private static final long serialVersionUID = 938472048374037L;

    private String appid;
    private String secret;
}
