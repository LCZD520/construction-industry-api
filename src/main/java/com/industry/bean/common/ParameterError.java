package com.industry.bean.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lc
 * @date 2022/7/3
 */
@Data
@AllArgsConstructor
public class ParameterError {
    private String errorField;
    private String errorMessage;
    private Object rejectedValue;
}
