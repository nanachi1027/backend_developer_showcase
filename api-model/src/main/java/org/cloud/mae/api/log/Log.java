package org.cloud.mae.api.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mae
 * @Date: 2021/2/22 2:44 上午
 * <p>
 * log instance
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {
    private static final long serialVersionUID = -56968799987676L;

    private Long id;

    private String username;

    private String module;

    private String params;

    private String remark;

    private Boolean flag;

    private Date createTime;
}
