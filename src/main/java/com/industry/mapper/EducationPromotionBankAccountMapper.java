package com.industry.mapper;

import com.industry.bean.entity.EducationPromotionBankAccountDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.request.EducationPromotionBankAccountRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 学历提升转账银行账户表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Mapper
public interface EducationPromotionBankAccountMapper extends BaseMapper<EducationPromotionBankAccountDO> {

    /**
     * 获取职称评审转账账户详情
     *
     * @param id 职称评审转账id
     * @return TitleEvaluationBankAccountRequest
     */
    EducationPromotionBankAccountRequest getEducationPromotionBankAccountById(@Param("id") Integer id);

    /**
     * 获取职称评审转账账户列表
     *
     * @param id 职称评审转账id
     * @return List<TitleEvaluationBankAccountRequest>
     */
    List<EducationPromotionBankAccountRequest> getListEducationPromotionBankAccountById(@Param("id") Integer id);
}
