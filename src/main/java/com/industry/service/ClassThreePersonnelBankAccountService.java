package com.industry.service;

import com.industry.bean.entity.ClassThreePersonnelBankAccountDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.ClassThreePersonnelBankAccountDO;
import com.industry.bean.request.ClassThreePersonnelBankAccountRequest;

import java.util.List;

/**
 * <p>
 * 三类人员转账银行账户表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
public interface ClassThreePersonnelBankAccountService extends IService<ClassThreePersonnelBankAccountDO> {

    /**
     * 插入银行账户
     *
     * @param classThreePersonnelBankAccountDO ClassThreePersonnelBankAccountDO
     * @return 受影响行数
     */
    int insert(ClassThreePersonnelBankAccountDO classThreePersonnelBankAccountDO);

    /**
     * 根据职称评审转账主键id获取银行账户列表
     *
     * @param id 职称评审转账主键id
     * @return list
     */
    List<ClassThreePersonnelBankAccountRequest> getListClassThreePersonnelBankAccountById(Integer id);

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
     * @return ClassThreePersonnelBankAccountRequest
     */
    ClassThreePersonnelBankAccountRequest getClassThreePersonnelBankAccountById(Integer id);

    /**
     * 更新账户信息
     *
     * @param classThreePersonnelBankAccountDO ClassThreePersonnelBankAccountDO
     * @return -1(不存在该记录) 1(更新成功) 0(更新失败)
     */
    int updateClassThreePersonnelBankAccountById(ClassThreePersonnelBankAccountDO classThreePersonnelBankAccountDO);
}
