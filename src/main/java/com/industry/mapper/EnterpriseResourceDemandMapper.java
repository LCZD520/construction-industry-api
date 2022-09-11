package com.industry.mapper;

import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.EnterpriseResourceDemandDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 企业资源需求表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-13
 */
@Mapper
public interface EnterpriseResourceDemandMapper extends BaseMapper<EnterpriseResourceDemandDO> {

    /**
     * 批量插入企业需求
     *
     * @param listEnterpriseDemands List<EnterpriseResourceDemandDO>
     * @return 受影响rows
     */
    int insertBatch(List<EnterpriseResourceDemandDO> listEnterpriseDemands);

    /**
     * 查询企业资源需求id集合
     *
     * @param id enterpriseResourceId
     * @return List<Integer>
     */
    List<Integer> selectIdsByEnterpriseResourceId(Integer id);

    /**
     * 获取企业需求
     *
     * @param id 企业需求id
     * @return EnterpriseDemandDO
     */
    EnterpriseDemandDO getEnterpriseDemandById(@Param("id") Integer id);
}
