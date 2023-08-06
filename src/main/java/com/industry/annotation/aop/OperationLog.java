package com.industry.annotation.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author win10
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
    /**
     * 操作描述
     *
     * @return String
     */
    String operationDesc() default "";

    /**
     * 模块
     *
     * @return String
     */
    String module() default "";

    /**
     * URI
     *
     * @return String
     */
    String uri() default "";
}
