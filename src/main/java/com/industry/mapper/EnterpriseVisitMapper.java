package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseVisitDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业回访表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-10
 */
@Mapper
public interface EnterpriseVisitMapper extends BaseMapper<EnterpriseVisitDO> {

    IPage<EnterpriseVisitDO> listEnterpriseVisits(Page<EnterpriseVisitDO> page, @Param("enterpriseId") Integer enterpriseId);
}
