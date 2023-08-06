package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TitleEvaluationTransferDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 职称评审转账表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
public interface TitleEvaluationTransferService extends IService<TitleEvaluationTransferDO> {

    /**
     * 添加职称评审转账记录
     *
     * @param titleEvaluationTransferDO TitleEvaluationTransferDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(TitleEvaluationTransferDO titleEvaluationTransferDO);

    /**
     * 获取转账记录
     *
     * @param id 职称评审订单id
     * @return List<TitleEvaluationTransferDO>
     */
    List<TitleEvaluationTransferDO> getListTransfers(Integer id);

    /**
     * 获取所有职称评审转账记录
     *
     * @param page ListPages<TitleEvaluationTransferDO>
     * @return ListPages<TitleEvaluationTransferDO>
     */
    ListPages<TitleEvaluationTransferDO> getListTransfersByPage(ListPages<TitleEvaluationTransferDO> page);

    /**
     * 获取职称评审转账详情
     *
     * @param id 职称评审转账id
     * @return QualificationAgencyTransferDO
     */
    TitleEvaluationTransferDO getDetailById(Integer id);
}
