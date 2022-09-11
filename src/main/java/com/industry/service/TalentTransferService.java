package com.industry.service;

import com.industry.bean.entity.TalentTransferDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 人才转账表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
public interface TalentTransferService extends IService<TalentTransferDO> {

    /**
     * 插入人才转账申请记录
     *
     * @param talentTransferDO talentTransferDO
     * @return 受影响行数
     */
    int insert(TalentTransferDO talentTransferDO);

    /**
     * 获取所有申请转账记录
     *
     * @param id 人才主键id
     * @return list
     */
    List<TalentTransferDO> listTalentTransfers(Integer id);

    /**
     * 删除申请记录
     * @param id 主键id
     * @return 受影响行数
     */
    int deleteById(Integer id);
    /**
     * 获取转账详情及转账流水
     * @param id 主键id
     * @return TalentTransferDO
     */
    TalentTransferDO getDetailById(Integer id);
}
