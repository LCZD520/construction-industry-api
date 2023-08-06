package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.OperationLogDO;
import com.industry.bean.entity.TalentCertificatesDO;
import com.industry.bean.entity.TalentDO;
import com.industry.bean.request.UpdateTalentCertificatesWhereaboutsRequest;
import com.industry.bean.search.TalentCertificatesSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才证件表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
@Mapper
public interface TalentCertificatesMapper extends BaseMapper<TalentCertificatesDO> {

    int insertBatch(@Param("list") List<TalentCertificatesDO> list);

    List<TalentCertificatesDO> listCertificates(@Param("id") Integer id);

    int updateCertificateBatchById(@Param("whereaboutsRequest") UpdateTalentCertificatesWhereaboutsRequest whereaboutsRequest);

    /**
     * 获取人才证件列表
     *
     * @param page ListPages<TalentDO>
     * @return List<TalentDO>
     */
    List<TalentDO> listAllCertificates(@Param("page") ListPages<TalentDO> page);

    /**
     * 获取人才总数
     *
     * @return Long
     */
    Long getCountListAllCertificates();

    /**
     * 获取证件列表
     *
     * @param id   人才id
     * @param page ListPages<TalentCertificatesDO>
     * @return List<TalentCertificatesDO>
     */
    List<TalentCertificatesDO> listPageCertificates(@Param("id") Integer id, @Param("page") ListPages<TalentCertificatesDO> page);

    /**
     * 获取人才证件列表
     *
     * @param id 人才id
     * @return Long
     */
    Long getCountListCertificatesById(@Param("id") Integer id);

    /**
     * 条件分页获取人才证件列表
     *
     * @param page   ListPages<OperationLogDO>
     * @param search TalentCertificatesDO
     * @return List<OperationLogDO>
     */
    List<TalentDO> listByConditionPages(
            @Param("page") ListPages<TalentDO> page,
            @Param("search") TalentCertificatesSearch search);

    /**
     * 条件分页获取人才证件列表总数
     *
     * @param search OperationLogSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") TalentCertificatesSearch search);
}
