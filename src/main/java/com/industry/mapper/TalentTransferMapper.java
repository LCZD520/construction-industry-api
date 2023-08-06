package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentTransferDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才转账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Mapper
public interface TalentTransferMapper extends BaseMapper<TalentTransferDO> {

    /**
     * 获取所有人才转账
     *
     * @param id 人才id
     * @return List<TalentTransferDO>
     */
    List<TalentTransferDO> listTalentTransfers(@Param("id") Integer id);

    /**
     * 获取人才转账详情
     *
     * @param id 人才转账id
     * @return TalentTransferDO
     */
    TalentTransferDO getDetailById(@Param("id") Integer id);

    /**
     * 获取人才转账总数
     *
     * @return Long
     */
    Long getCount();

    /**
     * 获取人才转账记录
     *
     * @param page ListPages<TalentTransferDO>
     * @return List<TalentTransferDO>
     */
    List<TalentTransferDO> listAllTalentTransfers(@Param("page") ListPages<TalentTransferDO> page);

    /**
     * 人才转账审批详情
     *
     * @param id 人才转账id
     * @return TalentTransferDO
     */
    TalentTransferDO getAuditDetailById(@Param("id") Integer id);

    /**
     * 更新转账申请状态
     *
     * @param talentTransferId 人才转账id
     * @param auditStatus      申请状态
     * @return 受影响rows
     */
    int updateApplicationStatusById(@Param("talentTransferId") Integer talentTransferId
            , @Param("auditStatus") String auditStatus);

    /**
     * 查询转账出纳审核通过数量
     *
     * @param id     人才id
     * @param status 审核状态
     * @return rows
     */
    int selectByIdAndStatus(@Param("id") Integer id, @Param("status") String status);

}
