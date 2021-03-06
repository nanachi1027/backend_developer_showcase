package org.cloud.mae.model.mail;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mae
 * @Date: 2021/3/4 10:50 下午
 * <p>
 * system mail
 */
public class Mail implements Serializable {

    private static final long serialVersionUID = 291278247838273L;

    private Long id;
    private Long userId;

    // sender's username
    private String username;

    // receiver's email address
    private String toEmail;

    // email's subject
    private String subject;

    // email's content
    private String content;

    // mail sending status
    private Integer status;

    // mail send timestamp
    private Date sendTime;
    private Date createTime;
    private Date updateTime;
}
