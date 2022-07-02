package com.industry.entity.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/1
 */
@Data
public class RoleInsertOrUpdateBatchRequest implements Serializable {
    private static final long serialVersionUID = 2608822465402416545L;
    private List<Integer> list;
    private Integer roleId;
}
