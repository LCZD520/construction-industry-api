package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.TalentResourceDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
