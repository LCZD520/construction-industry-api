package com.industry.mapper;

import com.industry.bean.entity.QualificationTransferTransferOrderBankAccountDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.request.QualificationTransferTransferOrderBankAccountRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质转让转账订单账户表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Mapper
public interface QualificationTransferTransferOrderBankAccountMapper extends BaseMapper<QualificationTransferTransferOrderBankAccountDO> {

    /**
     * 获取银行账户列表
     *
     * @param id 资质转让订单id
     * @return List<QualificationTransferTransferOrderBankAccountRequest>
     */
    List<QualificationTransferTransferOrderBankAccountRequest> getListBankAccountsById(@Param("id") Integer id);

    /**
     * 获取银行账户详情
     * @param id 银行账户id
     * @return QualificationTransferTransferOrderBankAccountRequest
     */
    QualificationTransferTransferOrderBankAccountRequest selectBankAccountById(@Param("id") Integer id);
}
