package com.industry.exception.handler;

import com.industry.enums.ResultCodeEnum;
import com.industry.bean.common.ParameterError;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lc
 * @date 2022/2/21
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResultEntity result;

    @Autowired
    public void setResultEntity(ResultEntity result) {
        this.result = result;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultEntity handler(MethodArgumentNotValidException e) {
        BindingResult results = e.getBindingResult();
        List<FieldError> fieldErrors = results.getFieldErrors();
        List<ParameterError> listErrors = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            listErrors.add(
                    new ParameterError(
                            error.getField(),
                            error.getDefaultMessage(),
                            error.getRejectedValue()));
        }
        return result.failure(ResultCodeEnum.PARAM_VALID_FAIL, listErrors);
    }

    /**
     * HTTP请求方法异常处理
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return ResultEntity
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResultEntity handler(HttpRequestMethodNotSupportedException e) {
        return result.failure(ResultCodeEnum.USER_METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResultEntity handler(ConstraintViolationException e) {
        final Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        final Set<String> strings = new HashSet<>();
        for (ConstraintViolation<?> c : constraintViolations) {
            strings.add(c.getMessageTemplate());
        }
        return result.failure(ResultCodeEnum.PARAM_ASSESSOR_FEE_VALID_FAIL, strings);
    }

}
