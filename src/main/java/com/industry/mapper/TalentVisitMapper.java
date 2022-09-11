package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.TalentVisitDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 人才回访表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
@Mapper
public interface TalentVisitMapper extends BaseMapper<TalentVisitDO> {

    IPage<TalentVisitDO> listTalentVisits(Page<TalentVisitDO> page, @Param("talentId") Integer talentId);
}
