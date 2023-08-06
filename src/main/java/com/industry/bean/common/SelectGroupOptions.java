package com.industry.bean.common;

import lombok.Data;

import java.util.List;

/**
 * @author lc
 * @date 2022/7/6
 */
@Data
public class SelectGroupOptions {
    private String label;
    private List<SelectOptions> options;
}
