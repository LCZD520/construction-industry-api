package com.industry.mapper;

import com.industry.bean.entity.EnterpriseBankAccountDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.request.EnterpriseBankAccountRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 企业申请转账银行账户表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-11
 */
@Mapper
public interface EnterpriseBankAccountMapper extends BaseMapper<EnterpriseBankAccountDO> {

    List<EnterpriseBankAccountRequest> getListEnterpriseBankAccountById(Integer id);

    EnterpriseBankAccountRequest getEnterpriseBankAccountById(Integer id);
    
}
