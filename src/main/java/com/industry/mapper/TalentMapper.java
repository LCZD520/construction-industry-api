package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.TalentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Mapper
public interface TalentMapper extends BaseMapper<TalentDO> {

    int save(TalentDO talent);

    IPage<TalentDO> queryList(Page<TalentDO> page);

    TalentDO queryById(Integer id);

    int updateTalentStatusBatch(@Param("listTalentIds") List<Integer> listTalentIds);
}
