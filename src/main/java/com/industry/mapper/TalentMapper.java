package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.AlternativeTalentSearch;
import com.industry.bean.search.AlternativeTalentSearch2;
import com.industry.bean.search.TalentSearch;
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

    Long getCountByCondition(@Param("talent") TalentSearch talent);

    List<TalentDO> listTalents(@Param("page") ListPages<TalentDO> page, @Param("talent") TalentSearch talent);

    List<TalentDO> getAlternativeTalents(@Param("page") ListPages<TalentDO> page, @Param("search") AlternativeTalentSearch search);

    Long getAlternativeTalentsCount(@Param("search") AlternativeTalentSearch search);

    /**
     * 获取已被下单的人才姓名
     *
     * @param listTalentIds 将要下单的人才id集合
     * @return List<String>
     */
    List<String> getOrderedTalentIds(@Param("listTalentIds") List<Integer> listTalentIds);

    /**
     * 获取可以下单的人员id集合
     *
     * @return List<Integer>
     */
    List<Integer> getCanOrderTalentIds();

    /**
     * 更新删除状态
     *
     * @param id      主键id
     * @param deleted 是否被删除
     * @return 受影响rows
     */
    int updateDeleteStatusById(@Param("id") Integer id, @Param("deleted") Boolean deleted);

    /**
     * 条件获取备选人才列表
     *
     * @param page   ListPages<TalentDO>
     * @param search AlternativeTalentSearch2
     * @return List<TalentDO>
     */
    List<TalentDO> getTalentsByCondition2(@Param("page") ListPages<TalentDO> page,
                                          @Param("search") AlternativeTalentSearch2 search);

    /**
     * 条件获取备选人才总数
     *
     * @param search AlternativeTalentSearch2
     * @return List<TalentDO>
     */
    Long getTalentsCountByCondition2(@Param("search") AlternativeTalentSearch2 search);

    /**
     * 更新人才状态
     *
     * @param id 人才id
     * @return 受影响rows
     */
    int updateTalentStatusById(@Param("id") Integer id, @Param("status") Integer status);
}
