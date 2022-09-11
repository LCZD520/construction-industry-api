package com.industry.service;

import com.industry.bean.entity.TalentBankAccountDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.request.TalentBankAccountRequest;

import java.util.List;

/**
 * <p>
 * 人才申请转账银行账户表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-08
 */
public interface TalentBankAccountService extends IService<TalentBankAccountDO> {

    /**
     * 插入银行账户
     *
     * @param talentBankAccountDO talentBankAccountDO实体
     * @return 受影响行数
     */
    int insert(TalentBankAccountDO talentBankAccountDO);

    /**
     * 根据人才主键id获取银行账户列表
     *
     * @param id 人才主键id
     * @return list
     */
    List<TalentBankAccountRequest> getListTalentBankAccountById(Integer id);

    /**
     * 根据银行账户id删除银行
     *
     * @param id 银行账户id
     * @return -1(不存在该记录) 1(删除成功) 0(删除失败)
     */
    int deleteById(Integer id);

    /**
     * 根据银行账户id获取账户详情
     *
     * @param id 银行账户id
     * @return TalentBankAccountRequest
     */
    TalentBankAccountRequest getTalentBankAccountById(Integer id);

    /**
     * 更新账户信息
     *
     * @param talentBankAccountDO talentBankAccountDO实体
     * @return -1(不存在该记录) 1(更新成功) 0(更新失败)
     */
    int updateTalentBankAccountById(TalentBankAccountDO talentBankAccountDO);
}
