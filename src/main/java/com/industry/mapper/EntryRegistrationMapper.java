package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.SelectOptions;
import com.industry.bean.entity.EntryRegistrationDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.EntryRegistrationSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才入账登记表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-04-18
 */
@Mapper
public interface EntryRegistrationMapper extends BaseMapper<EntryRegistrationDO> {

    /**
     * 条件获取入账登记记录
     *
     * @param page   ListPages<EntryRegistrationDO>
     * @param search EntryRegistrationSearch
     * @return List<EntryRegistrationDO>
     */
    List<EntryRegistrationDO> listByConditionPages(@Param("page") ListPages<EntryRegistrationDO> page,
                                                   @Param("search") EntryRegistrationSearch search);

    /**
     * 条件获取入账登记记录总数
     *
     * @param search EntryRegistrationSearch
     * @return rows
     */
    Long getCountByCondition(@Param("search") EntryRegistrationSearch search);

    /**
     * 获取所有企业
     *
     * @return List<SelectOptions>
     */
    List<SelectOptions> getListEnterprises();

    /**
     * 获取所有资质转让
     *
     * @return List<SelectOptions>
     */
    List<SelectOptions> getListQualificationTransfers();

    /**
     * 获取所有资质代办
     *
     * @return List<SelectOptions>
     */
    List<SelectOptions> getListQualificationAgencys();

    /**
     * 获取所有职称评审
     *
     * @return rows
     */
    List<SelectOptions> getListTitleEvaluations();

    /**
     * 获取所有三类人员
     *
     * @return List<SelectOptions>
     */
    List<SelectOptions> getListClassThreePerson();

    /**
     * 获取所有学历提升
     *
     * @return List<SelectOptions>
     */
    List<SelectOptions> getListEducationPromotions();


}
