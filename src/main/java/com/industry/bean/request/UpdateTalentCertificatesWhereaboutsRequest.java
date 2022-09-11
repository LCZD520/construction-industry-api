package com.industry.bean.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 批量更新人才证件去向接收对象
 *
 * @author lc
 * @date 2022/7/8
 */
@Data
public class UpdateTalentCertificatesWhereaboutsRequest {
    /**
     * 证件去向
     */
    @NotNull(message = "请选择证件新去向")
    private Integer certificatesWhereabouts;
    /**
     * 人才证件id集合
     */
    @Size(min = 1, message = "更新的证件数量至少为1")
    private List<Integer> listTalentCertificatesId;
    /**
     * 备注
     */
    private String remark;
}
