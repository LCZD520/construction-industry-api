package com.industry.bean.common;

import com.industry.enums.ResultCodeEnum;
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
    private static final long serialVersionUID = 3839412368549130681L;
    /**
     * 状态
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

    /**
     * 接口入参
     */
    private Object inputParameter;

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

    public ResultEntity(Class<ResultCodeEnum> resultCodeEnum) {
    }

    public ResultEntity(ResultCodeEnum resultCodeEnum, Object data, Object inputParameter) {
        this.status = resultCodeEnum.getStatus();
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
        this.inputParameter = inputParameter;
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
     * 成功,返回数据
     *
     * @param success        ResultCodeEnum
     * @param resultCodeEnum Class<ResultCodeEnum>
     * @return Map
     */
    public ResultEntity success2(ResultCodeEnum success, Class<ResultCodeEnum> resultCodeEnum) {
        return new ResultEntity(success, resultCodeEnum);
    }


    public ResultEntity success(ResultCodeEnum resultCodeEnum, Object data, Object inputParameter) {
        return new ResultEntity(resultCodeEnum, data, inputParameter);
    }

    /**
     * 响应失败
     */
    public ResultEntity failure(ResultCodeEnum resultCodeEnum) {
        return new ResultEntity(resultCodeEnum);
    }

    /**
     * 响应失败
     */
    public ResultEntity failure(ResultCodeEnum resultCodeEnum, Object data) {
        return new ResultEntity(resultCodeEnum, data);
    }

}