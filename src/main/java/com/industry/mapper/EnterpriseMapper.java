package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseDO;
import com.industry.bean.entity.EnterpriseDemandDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * <p>
 * 企业表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Mapper
public interface EnterpriseMapper extends BaseMapper<EnterpriseDO> {

    @Override
    int insert(EnterpriseDO enterpriseDO);

    IPage<EnterpriseDO> listEnterprises(Page<EnterpriseDO> page);

    EnterpriseDO getEnterpriseById(Integer id);

    /**
     * 获取企业需求
     *
     * @param id 企业id
     * @return List<EnterpriseDemandDO>
     */
    List<EnterpriseDemandDO> getEnterpriseDemandsById(@Param("id") Integer id);

    int updateEnterpriseStatus(@Param("enterpriseId") Integer enterpriseId);
}
