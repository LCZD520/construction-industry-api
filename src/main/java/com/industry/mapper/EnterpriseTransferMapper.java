package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.EnterpriseTransferDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.OrderSelectedTalentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 企业转账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-09
 */
@Mapper
public interface EnterpriseTransferMapper extends BaseMapper<EnterpriseTransferDO> {

    /**
     * 获取待分配人才
     *
     * @param id 企业id
     * @return List<OrderSelectedTalentDO>
     */
    List<OrderSelectedTalentDO> getWaitAssignTalents(@Param("id") Integer id);

    List<Integer> getWaitAssignTalentIds(@Param("id") Integer id);

    Integer updateInfoByIds(@Param("id") Integer id, @Param("ids") List<Integer> ids);

    List<String> selectFullNameByIds(@Param("lis") List<Integer> lis);

    /**
     * 获取转账记录
     *
     * @param id 企业id
     * @return List<EnterpriseTransferDO>
     */
    List<EnterpriseTransferDO> getListTransferRecords(Integer id);

    EnterpriseTransferDO getDetailById(@Param("id") Integer id);

    /**
     * 获取转账记录总数
     *
     * @return Long
     */
    Long getCount();

    /**
     * 获取企业转账记录
     *
     * @param page ListPages<EnterpriseTransferDO>
     * @return List<EnterpriseTransferDO>
     */
    List<EnterpriseTransferDO> listTransferRecords(@Param("page") ListPages<EnterpriseTransferDO> page);

    /**
     * 更新审核状态
     * @param enterpriseTransferId 企业转账id
     * @param auditStatus 审核状态
     * @return 受影响rows
     */
    int updateApplicationStatusById(@Param("enterpriseTransferId") Integer enterpriseTransferId, @Param("auditStatus") String auditStatus);

    /**
     * 获取审批详情
     * @param id id
     * @return EnterpriseTransferDO
     */
    EnterpriseTransferDO getAuditDetailById(@Param("id") Integer id);

    List<EnterpriseTransferDO> listAllEnterpriseTransfers(@Param("page") ListPages<EnterpriseTransferDO> page);
}
