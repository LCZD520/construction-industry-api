package com.industry.util;

import com.industry.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lc
 * @description: 统一响应实体类
 * @date 2022/2/25 9:26
 */
@Data
@Component
public class ResultEntity implements Serializable {
    /**
     * 请求状态
     */
    private Boolean status;
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;

    public ResultEntity() {

    }

    public ResultEntity(ResultCodeEnum resultCodeEnum) {
        this.status = resultCodeEnum.getStatus();
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public ResultEntity(ResultCodeEnum resultCodeEnum, Object data) {
        this.status = resultCodeEnum.getStatus();
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
    }

    /**
     * 成功,不返回数据
     *
     * @param resultCodeEnum Enum
     * @return Map
     */
    public ResultEntity success(ResultCodeEnum resultCodeEnum) {
        return new ResultEntity(resultCodeEnum);
    }

    /**
     * 成功,返回数据
     *
     * @param resultCodeEnum Enum
     * @return Map
     */
    public ResultEntity success(ResultCodeEnum resultCodeEnum, Object data) {
        return new ResultEntity(resultCodeEnum, data);
    }

    /**
     * 响应失败
     */
    public ResultEntity failure(ResultCodeEnum resultCodeEnum) {
        return new ResultEntity(resultCodeEnum);
    }

}
