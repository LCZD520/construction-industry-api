package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationTransferApprovalFlowDO;
import com.industry.bean.entity.QualificationTransferTransferDO;
import com.industry.bean.entity.QualificationTransferTransferDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.search.QualificationTransferTransferSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质转让转账表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
public interface QualificationTransferTransferService extends IService<QualificationTransferTransferDO> {

    /**
     * 获取资质转让转账记录
     *
     * @param id 资质转让id
     * @return List<QualificationTransferTransferDO>
     */
    List<QualificationTransferTransferDO> getListTransferRecords(Integer id);

    /**
     * 插入资质转账记录
     *
     * @param transfer QualificationTransferTransferDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(QualificationTransferTransferDO transfer);

    /**
     * 获取资质转让转账详情
     *
     * @param id 转账转让转账id
     * @return QualificationTransferTransferDO
     */
    QualificationTransferTransferDO getDetailById(Integer id);

    /**
     * 资质转让转账审核
     *
     * @param qualificationTransferApprovalFlow QualificationTransferApprovalFlowDO
     * @param currentAuditRoleId                   当前审批角色id
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity audit(QualificationTransferApprovalFlowDO qualificationTransferApprovalFlow, Integer currentAuditRoleId);

    /**
     * 条件搜索资质转让转账记录
     *
     * @param page   ListPages<QualificationTransferTransferDO>
     * @param search QualificationTransferTransferSearch
     * @return ListPages<QualificationTransferTransferDO>
     */
    ListPages<QualificationTransferTransferDO> listTransferRecords(ListPages<QualificationTransferTransferDO> page, QualificationTransferTransferSearch search);
}
