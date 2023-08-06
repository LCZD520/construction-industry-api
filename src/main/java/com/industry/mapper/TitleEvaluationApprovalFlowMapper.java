package com.industry.mapper;

import com.industry.bean.entity.TitleEvaluationApprovalFlowDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 职称评审转账审批流水 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Mapper
public interface TitleEvaluationApprovalFlowMapper extends BaseMapper<TitleEvaluationApprovalFlowDO> {

    /**
     * 获取最新转账审核记录
     *
     * @param id 职称评审转账id
     * @return TitleEvaluationApprovalFlowDO
     */
    TitleEvaluationApprovalFlowDO getLatestRecordByTitleEvaluationTransferId(@Param("id") Integer id);
}
