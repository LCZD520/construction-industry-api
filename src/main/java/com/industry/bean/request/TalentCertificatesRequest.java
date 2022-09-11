package com.industry.bean.request;

import com.alibaba.druid.sql.visitor.functions.Insert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/7
 */
@Data
public class TalentCertificatesRequest {
    @NotNull(message = "证件id不能为空", groups = Update.class)
    @ApiModelProperty(value = "证件id")
    private Integer talentCertificatesId;

    @NotNull(message = "人才主键id不能为空", groups = Insert.class)
    @Null(message = "人才主键id必须为空", groups = Update.class)
    @ApiModelProperty(value = "人才主键id")
    private Integer talentId;

    @NotNull(message = "请选择证件所在分支")
    @ApiModelProperty(value = "证件所在分支")
    private Integer mechanismId;

    @ApiModelProperty(value = "证件类型")
    private List<Integer> listCertificatesType;

    @ApiModelProperty(value = "证件去向")
    private Integer certificatesWhereabouts;

    @ApiModelProperty(value = "统一备注")
    private String remark;
}
