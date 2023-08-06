package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAgencyDO;
import com.industry.bean.search.QualificationAgencySearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质代办表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
@Mapper
public interface QualificationAgencyMapper extends BaseMapper<QualificationAgencyDO> {

    /**
     * 获取所有的资质代办列表
     *
     * @param page (Page<QualificationAgencyDO>
     * @return IPage<QualificationAgencyDO>
     */
    IPage<QualificationAgencyDO> listQualificationAgencys(Page<QualificationAgencyDO> page);

    /**
     * 获取资质代办
     *
     * @param id 资质代办id
     * @return QualificationAgencyDO
     */
    QualificationAgencyDO getQualificationAgencyById(@Param("id") Integer id);

    /**
     * 更新资质代办状态
     *
     * @param status 状态
     * @param id     资质代办id
     * @return 受影响rows
     */
    int updateStatusById(@Param("status") int status, @Param("id") Integer id);

    /**
     * 条件获取资质代办列表总数
     *
     * @param search QualificationAgencySearch
     */
    Long getCountByCondition(@Param("search") QualificationAgencySearch search);

    /**
     * 条件分页获取资质代办列表
     *
     * @param page   ListPages<QualificationAgencyDO>
     * @param search QualificationAgencySearch
     * @return List<QualificationAgencyDO>
     */
    List<QualificationAgencyDO> listQualificationAgencysByConditionPages(
            @Param("page") ListPages<QualificationAgencyDO> page,
            @Param("search") QualificationAgencySearch search);

    /**
     * 更新删除状态
     *
     * @param id      主键id
     * @param deleted 是否被删除
     * @return 受影响rows
     */
    int updateDeleteStatusById(@Param("id") Integer id, @Param("deleted") Boolean deleted);
}
