package com.industry.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPagesVo<T> implements Serializable {
    private static final long serialVersionUID = -2789581533414438860L;
    private List<T> list;
    private Long total;
    private Long currentPage;
    private Long pageSize;
}
