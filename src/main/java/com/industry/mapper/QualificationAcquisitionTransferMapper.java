package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAcquisitionTransferDO;
import com.industry.bean.search.QualificationAcquisitionTransferSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质收购转账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Mapper
public interface QualificationAcquisitionTransferMapper extends BaseMapper<QualificationAcquisitionTransferDO> {

    /**
     * 获取转账收购转账记录
     *
     * @param id 资质收购id
     * @return List<QualificationAcquisitionTransferDO>
     */
    List<QualificationAcquisitionTransferDO> getListTransferRecords(@Param("id") Integer id);

    /**
     * 获取资质收购转账详情
     *
     * @param id 资质收购转账记录id
     * @return QualificationAcquisitionTransferDO
     */
    QualificationAcquisitionTransferDO getDetailById(@Param("id") Integer id);

    /**
     * 获取资质收购转账记录总数
     *
     * @return Long
     */
    Long getCount();

    /**
     * 分页获取资质收购转账记录
     *
     * @param page ListPages<QualificationAcquisitionTransferDO>
     * @return List<QualificationAcquisitionTransferDO>
     */
    List<QualificationAcquisitionTransferDO> getListTransferRecordsPage(@Param("page") ListPages<QualificationAcquisitionTransferDO> page);

    /**
     * 更新转账记录
     *
     * @param qualificationAcquisitionTransferId 资质收购转账id
     * @param auditStatus                        审核状态
     * @return 受影响rows
     */
    int updateApplicationStatusById(@Param("qualificationAcquisitionTransferId") Integer qualificationAcquisitionTransferId
            , @Param("auditStatus") String auditStatus);


    /**
     * 条件分页获取资质收购转账记录
     *
     * @param page   ListPages<QualificationAcquisitionTransferDO>
     * @param search QualificationAcquisitionTransferSearch
     * @return List<QualificationAcquisitionTransferDO>
     */
    List<QualificationAcquisitionTransferDO> listTransferRecords(
            @Param("page") ListPages<QualificationAcquisitionTransferDO> page,
            @Param("search") QualificationAcquisitionTransferSearch search);

    /**
     * 获取资质收购转账记录总数
     *
     * @param search QualificationAcquisitionTransferSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") QualificationAcquisitionTransferSearch search);
}
