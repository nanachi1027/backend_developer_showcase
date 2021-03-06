package org.cloud.mae.model.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mae
 * @Date: 2021/3/4 9:31 上午
 * <p>
 * user's system role
 */
@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = -234839485849348549L;

    private Long id;
    private String code;
    private String name;
    private Date createTime;
    private Date updateTime;
}
