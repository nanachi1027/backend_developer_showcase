package org.cloud.mae.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Mae
 * @Date: 2021/3/4 10:54 下午
 * <p>
 * Paging object.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {
    private static final long serialVersionUID = -28374023048203L;
    private int total;
    private List<T> data;
}
