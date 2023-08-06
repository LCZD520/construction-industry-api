package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentResourceDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.TalentResourceSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才资源表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-12
 */
@Mapper
public interface TalentResourceMapper extends BaseMapper<TalentResourceDO> {

    IPage<TalentResourceDO> listTalentResources(Page<TalentResourceDO> page);

    TalentResourceDO getTalentResourceById(@Param("id") Integer id);

    int save(TalentResourceDO talentResource);

    IPage<TalentResourceDO> listSharedTalentResources(Page<TalentResourceDO> page);

    IPage<TalentResourceDO> listTalentResourcesByUserId(Page<TalentResourceDO> page, @Param("userId") Integer userId);

    /**
     * 条件分页获取人才资源列表
     *
     * @param page   ListPages<TalentResourceDO> page
     * @param search TalentResourceSearch
     * @return List<TalentResourceDO>
     */
    List<TalentResourceDO> listTalentResourcesByConditionPages(
            @Param("page") ListPages<TalentResourceDO> page,
            @Param("search") TalentResourceSearch search);

    /**
     * @param search TalentResourceSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") TalentResourceSearch search);
}
