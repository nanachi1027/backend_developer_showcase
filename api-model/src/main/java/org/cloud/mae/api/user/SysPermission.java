package org.cloud.mae.api.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mae
 * @Date: 2021/3/4 10:33 下午
 *
 * permission
 *
 */
@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 37489506948376L;

    private Long id;
    private String permission;
    private String name;
    private Date createTime;
    private Date updateTime;
}
