package com.industry.bean.view;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industry.bean.entity.ApprovalSettingDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 审批配置返回VO
 * </p>
 *
 * @author lc
 * @since 2022-09-12
 */
@Data
public class ApprovalSettingVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ApprovalSettingDO> entryList;

    private List<ApprovalSettingDO> transferList;

    private List<ApprovalSettingDO> achievementList;


}
