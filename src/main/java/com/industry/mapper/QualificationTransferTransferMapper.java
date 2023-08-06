package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationTransferTransferDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.QualificationTransferTransferSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质转让转账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Mapper
public interface QualificationTransferTransferMapper extends BaseMapper<QualificationTransferTransferDO> {

    /**
     * 条件获取资质转让转账记录
     *
     * @param page   ListPages<QualificationTransferTransferDO>
     * @param search QualificationTransferTransferSearch
     * @return List<QualificationTransferTransferDO>
     */
    List<QualificationTransferTransferDO> listTransferRecords(@Param("page") ListPages<QualificationTransferTransferDO> page,
                                                              @Param("search") QualificationTransferTransferSearch search);

    /**
     * 条件获取总数
     *
     * @param search QualificationTransferTransferSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") QualificationTransferTransferSearch search);

    /**
     * 获取资质转让转账详情
     *
     * @param id 资质转让转账记录id
     * @return QualificationTransferTransferDO
     */
    QualificationTransferTransferDO getDetailById(@Param("id") Integer id);

    /**
     * 获取资质转让转账记录
     *
     * @param id 资质转让订单id
     * @return List<QualificationTransferTransferDO>
     */
    List<QualificationTransferTransferDO> getListTransferRecords(@Param("id") Integer id);

    /**
     * 更新审核状态
     *
     * @param qualificationTransferTransferId 资质转让转账id
     * @param auditStatus                     审核状态
     * @return 受影响rows
     */
    int updateApplicationStatusById(@Param("qualificationTransferTransferId") Integer qualificationTransferTransferId,
                                    @Param("auditStatus") String auditStatus);
}
