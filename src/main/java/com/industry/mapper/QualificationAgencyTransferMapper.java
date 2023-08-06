package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAgencyTransferDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质代办转账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Mapper
public interface QualificationAgencyTransferMapper extends BaseMapper<QualificationAgencyTransferDO> {

    /**
     * 获取转账代办转账记录
     *
     * @param id 资质代办id
     * @return List<QualificationAgencyTransferDO>
     */
    List<QualificationAgencyTransferDO> getListTransferRecords(@Param("id") Integer id);

    /**
     * 获取资质代办转账详情
     *
     * @param id 资质代办转账记录id
     * @return QualificationAgencyTransferDO
     */
    QualificationAgencyTransferDO getDetailById(@Param("id") Integer id);

    /**
     * 获取资质代办转账记录总数
     *
     * @return Long
     */
    Long getCount();

    /**
     * 分页获取资质代办转账记录
     *
     * @param page ListPages<QualificationAgencyTransferDO>
     * @return List<QualificationAgencyTransferDO>
     */
    List<QualificationAgencyTransferDO> getListTransferRecordsPage(@Param("page") ListPages<QualificationAgencyTransferDO> page);

    /**
     * 更新转账记录
     *
     * @param qualificationAgencyTransferId 资质代办转账id
     * @param auditStatus                   审核状态
     * @return 受影响rows
     */
    int updateApplicationStatusById(@Param("qualificationAgencyTransferId") Integer qualificationAgencyTransferId
            , @Param("auditStatus") String auditStatus);
}
