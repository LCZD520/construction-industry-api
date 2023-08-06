package com.industry.mapper;

import com.industry.bean.entity.QualificationAgencyBankAccountDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.request.QualificationAgencyBankAccountRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质代办账户表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Mapper
public interface QualificationAgencyBankAccountMapper extends BaseMapper<QualificationAgencyBankAccountDO> {

    /**
     * 获取资质代办银行账户列表
     *
     * @param id 资质代办id
     * @return List<QualificationAgencyBankAccountRequest>
     */
    List<QualificationAgencyBankAccountRequest> getListBankAccountsById(@Param("id") Integer id);

    /**
     * 获取资质代办银行账户详情
     *
     * @param id 银行账户id
     * @return QualificationAgencyBankAccountRequest
     */
    QualificationAgencyBankAccountRequest getBankAccountsById(@Param("id") Integer id);
}
