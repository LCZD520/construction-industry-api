package com.industry.mapper;

import com.industry.bean.entity.EnterpriseDemandDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 企业需求表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Mapper
public interface EnterpriseDemandMapper extends BaseMapper<EnterpriseDemandDO> {

    /**
     * 批量插入企业需求
     *
     * @param listEnterpriseDemands List<EnterpriseDemandDO>
     * @return 受影响rows
     */
    int insertBatch(List<EnterpriseDemandDO> listEnterpriseDemands);

    /**
     * 查询企业资源需求id集合
     *
     * @param id enterpriseResourceId
     * @return List<Integer>
     */
    List<Integer> selectIdsByEnterpriseId(Integer id);
}
