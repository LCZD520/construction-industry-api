package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.EnterpriseDO;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.search.EnterpriseSearch;
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

    /**
     * 条件分页获取企业列表
     *
     * @param page   ListPages<EnterpriseDO>
     * @param search EnterpriseSearch
     * @return List<EnterpriseDO>
     */
    List<EnterpriseDO> listEnterprisesByConditionPages(@Param("page") ListPages<EnterpriseDO> page
            , @Param("search") EnterpriseSearch search);

    /**
     * 条件获取企业列表总数
     *
     * @param search EnterpriseSearch
     * @return Long
     */
    Long getCount(@Param("search") EnterpriseSearch search);

    /**
     * 更新删除状态
     *
     * @param id      主键id
     * @param deleted 是否被删除
     * @return 受影响rows
     */
    int updateDeleteStatusById(@Param("id") Integer id, @Param("deleted") Boolean deleted);
}
