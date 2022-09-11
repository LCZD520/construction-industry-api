package com.industry.mapper;

import com.industry.bean.entity.EnterpriseTransferDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.OrderSelectedTalentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 企业转账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-09
 */
@Mapper
public interface EnterpriseTransferMapper extends BaseMapper<EnterpriseTransferDO> {

    /**
     * 获取待分配人才
     *
     * @param id 企业id
     * @return List<OrderSelectedTalentDO>
     */
    List<OrderSelectedTalentDO> getWaitAssignTalents(@Param("id") Integer id);

    List<Integer> getWaitAssignTalentIds(@Param("id") Integer id);

    Integer updateInfoByIds(@Param("id") Integer id, @Param("ids") List<Integer> ids);

    List<String> selectFullNameByIds(@Param("lis") List<Integer> lis);

    /**
     * 获取转账记录
     *
     * @param id 企业id
     * @return List<EnterpriseTransferDO>
     */
    List<EnterpriseTransferDO> getListTransferRecords(Integer id);

    EnterpriseTransferDO getDetailById(@Param("id") Integer id);
}
