package com.industry.service;

import com.industry.bean.entity.EducationPromotionBankAccountDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.EducationPromotionBankAccountDO;
import com.industry.bean.request.EducationPromotionBankAccountRequest;

import java.util.List;

/**
 * <p>
 * 学历提升转账银行账户表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
public interface EducationPromotionBankAccountService extends IService<EducationPromotionBankAccountDO> {
    
    /**
     * 插入银行账户
     *
     * @param titleEvaluationBankAccountDO EducationPromotionBankAccountDO
     * @return 受影响行数
     */
    int insert(EducationPromotionBankAccountDO titleEvaluationBankAccountDO);

    /**
     * 根据职称评审转账主键id获取银行账户列表
     *
     * @param id 职称评审转账主键id
     * @return list
     */
    List<EducationPromotionBankAccountRequest> getListEducationPromotionBankAccountById(Integer id);

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
     * @return EducationPromotionBankAccountRequest
     */
    EducationPromotionBankAccountRequest getEducationPromotionBankAccountById(Integer id);

    /**
     * 更新账户信息
     *
     * @param titleEvaluationBankAccountDO EducationPromotionBankAccountDO
     * @return -1(不存在该记录) 1(更新成功) 0(更新失败)
     */
    int updateEducationPromotionBankAccountById(EducationPromotionBankAccountDO titleEvaluationBankAccountDO);
}
