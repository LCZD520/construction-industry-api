package com.industry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.EnterpriseBankAccountDO;
import com.industry.bean.request.EnterpriseBankAccountRequest;

import java.util.List;

/**
 * <p>
 * 企业申请转账银行账户表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-11
 */
public interface EnterpriseBankAccountService extends IService<EnterpriseBankAccountDO> {
    /**
     * 插入银行账户
     *
     * @param enterpriseBankAccountDO enterpriseBankAccountDO实体
     * @return 受影响行数
     */
    int insert(EnterpriseBankAccountDO enterpriseBankAccountDO);

    /**
     * 根据人才主键id获取银行账户列表
     *
     * @param id 人才主键id
     * @return list
     */
    List<EnterpriseBankAccountRequest> getListEnterpriseBankAccountById(Integer id);

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
     * @return EnterpriseBankAccountRequest
     */
    EnterpriseBankAccountRequest getEnterpriseBankAccountById(Integer id);

    /**
     * 更新账户信息
     *
     * @param enterpriseBankAccountDO enterpriseBankAccountDO实体
     * @return -1(不存在该记录) 1(更新成功) 0(更新失败)
     */
    int updateEnterpriseBankAccountById(EnterpriseBankAccountDO enterpriseBankAccountDO);
}
