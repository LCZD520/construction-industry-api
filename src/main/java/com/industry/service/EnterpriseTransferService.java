package com.industry.service;

import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EnterpriseTransferDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.request.EnterpriseTransferRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 企业转账表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-09-09
 */
public interface EnterpriseTransferService extends IService<EnterpriseTransferDO> {

    /**
     * 获取待分配人才
     *
     * @param id 企业id
     * @return List<OrderSelectedTalentDO>
     */
    List<OrderSelectedTalentDO> getWaitAssignTalents(Integer id);

    /**
     * 转账申请
     *
     * @param request EnterpriseTransferRequest
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity insert(EnterpriseTransferRequest request);

    /**
     * 获取转账记录
     *
     * @param id 企业id
     * @return List<EnterpriseTransferDO>
     */
    List<EnterpriseTransferDO> getListTransferRecords(Integer id);

    /**
     * 获取转账详情
     * @param id 转账id
     * @return EnterpriseTransferDO
     */
    EnterpriseTransferDO getDetailById(Integer id);
}
