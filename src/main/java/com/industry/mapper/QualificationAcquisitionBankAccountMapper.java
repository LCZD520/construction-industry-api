package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.QualificationAcquisitionBankAccountDO;
import com.industry.bean.request.QualificationAcquisitionBankAccountRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质收购账户表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Mapper
public interface QualificationAcquisitionBankAccountMapper extends BaseMapper<QualificationAcquisitionBankAccountDO> {

    /**
     * 获取资质收购银行账户列表
     *
     * @param id 资质收购id
     * @return List<QualificationAcquisitionBankAccountRequest>
     */
    List<QualificationAcquisitionBankAccountRequest> getListBankAccountsById(@Param("id") Integer id);

    /**
     * 获取资质收购银行账户详情
     *
     * @param id 银行账户id
     * @return QualificationAcquisitionBankAccountRequest
     */
    QualificationAcquisitionBankAccountRequest getBankAccountsById(@Param("id") Integer id);
}
