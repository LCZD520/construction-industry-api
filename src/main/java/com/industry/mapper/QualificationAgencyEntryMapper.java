package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAgencyEntryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.QualificationAgencyEntrySearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质代办入账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-02-26
 */
@Mapper
public interface QualificationAgencyEntryMapper extends BaseMapper<QualificationAgencyEntryDO> {

    /**
     * 资质代办入账添加
     *
     * @param qualificationAgencyEntryDO QualificationAgencyEntryDO
     * @return 受影响rows
     */
    int insertQualificationAgencyEntry(QualificationAgencyEntryDO qualificationAgencyEntryDO);

    /**
     * 获取所有入账记录
     *
     * @param id 资质代办id
     * @return List<QualificationAgencyEntryDO>
     */
    List<QualificationAgencyEntryDO> getList(@Param("id") Integer id);

    /**
     * 条件获取资质代办入账列表总数
     *
     * @param search QualificationAgencyEntrySearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") QualificationAgencyEntrySearch search);

    /**
     * 条件分页获取资质代办入账列表
     *
     * @param page   ListPages<QualificationAgencyEntryDO>
     * @param search QualificationAgencyEntrySearch
     * @return List<QualificationAgencyEntryDO>
     */
    List<QualificationAgencyEntryDO> listByConditionPages(
            @Param("page") ListPages<QualificationAgencyEntryDO> page,
            @Param("search") QualificationAgencyEntrySearch search);

    /**
     * 获取资质代办入账
     *
     * @param id 主键id
     * @return QualificationAgencyEntryDO
     */
    QualificationAgencyEntryDO getDetailById(@Param("id") Integer id);
}
