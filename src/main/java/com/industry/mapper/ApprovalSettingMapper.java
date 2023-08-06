package com.industry.mapper;

import com.industry.bean.entity.ApprovalSettingDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 审批设置表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-07
 */
@Mapper
public interface ApprovalSettingMapper extends BaseMapper<ApprovalSettingDO> {

    Long selectByType(@Param("type") Integer type);

    int insertBatch(List<ApprovalSettingDO> list);

    int deleteBatch(@Param("type") Integer type);

    List<ApprovalSettingDO> listApprovalSettings();

    /**
     * 获取入账/转账/业绩审批设置
     *
     * @param type 类型（1-入账,2-转账,3-业绩）
     * @return List<ApprovalSettingDO>
     */
    List<ApprovalSettingDO> listApprovalSettingsByType(@Param("type") int type);
}
