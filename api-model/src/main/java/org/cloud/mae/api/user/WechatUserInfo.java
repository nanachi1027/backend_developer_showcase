package org.cloud.mae.api.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mae
 * @Date: 2021/3/4 10:40 下午
 */
@Getter
@Setter
@ToString
public class WechatUserInfo implements Serializable {

    private static final long serialVersionUID = 393271298838374L;

    private Long id;
    private String openid;
    private String unionid;
    private Long userId;
    private String app;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private Date createTime;
    private Date updateTime;
}
