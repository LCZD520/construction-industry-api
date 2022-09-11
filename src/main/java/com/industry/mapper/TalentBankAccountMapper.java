package com.industry.mapper;

import com.industry.bean.entity.TalentBankAccountDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.request.TalentBankAccountRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 人才申请转账银行账户表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-08
 */
@Mapper
public interface TalentBankAccountMapper extends BaseMapper<TalentBankAccountDO> {

    List<TalentBankAccountRequest> getListTalentBankAccountById(Integer id);

    TalentBankAccountRequest getTalentBankAccountById(Integer id);
}
