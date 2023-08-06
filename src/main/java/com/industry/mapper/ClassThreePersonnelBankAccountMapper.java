package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.ClassThreePersonnelBankAccountDO;
import com.industry.bean.request.ClassThreePersonnelBankAccountRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 三类人员转账银行账户表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Mapper
public interface ClassThreePersonnelBankAccountMapper extends BaseMapper<ClassThreePersonnelBankAccountDO> {

    /**
     * 获取职称评审转账账户详情
     *
     * @param id 职称评审转账id
     * @return TitleEvaluationBankAccountRequest
     */
    ClassThreePersonnelBankAccountRequest getClassThreePersonnelBankAccountById(@Param("id") Integer id);

    /**
     * 获取职称评审转账账户列表
     *
     * @param id 职称评审转账id
     * @return List<TitleEvaluationBankAccountRequest>
     */
    List<ClassThreePersonnelBankAccountRequest> getListClassThreePersonnelBankAccountById(@Param("id") Integer id);
}
