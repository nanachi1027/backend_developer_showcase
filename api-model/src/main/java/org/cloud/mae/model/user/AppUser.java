package org.cloud.mae.model.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mae
 * @Date: 2021/3/4 9:27 上午
 */
@Data
public class AppUser implements Serializable {
    private static final long serialVersionUID = 678909876678945678L;

    private Long id;
    private String username;
    private String password;
    private String nickName;
    private String headImgUrl;
    private String phone;
    private Integer sex;

    // state info
    private Boolean enabled;
    private String type;
    private Date createTime;
    private Date updateTime;
}
