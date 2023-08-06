package com.industry.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author lc
 * @since 2022-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_picture")
@ApiModel(value = "PictureDO对象", description = "图片表")
public class PictureDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "resourceId不能为空")
    @ApiModelProperty(value = "资源id")
    @TableField("resource_id")
    private Integer resourceId;

    @NotBlank(message = "namespace不能为空")
    @ApiModelProperty(value = "命名空间")
    @TableField("namespace")
    private String namespace;

    @NotBlank(message = "type不能为空")
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "文件名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "文件路径")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "文件base64位格式")
    @TableField(exist = false)
    private String base64Str;

}
