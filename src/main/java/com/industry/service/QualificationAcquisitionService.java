package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 资质收购表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
public interface QualificationAcquisitionService extends IService<QualificationAcquisitionDO> {

    /**
     * 添加资质收购记录
     *
     * @param qualificationAcquisition QualificationAcquisitionDO
     * @return 受影响rows
     */
    int insert(QualificationAcquisitionDO qualificationAcquisition);

    /**
     * 获取资质收购记录
     *
     * @param page page
     * @return IPage<QualificationAcquisitionDO>
     */
    IPage<QualificationAcquisitionDO> listQualificationAcquisition(Page<QualificationAcquisitionDO> page);

    /**
     * 更新资质收购
     *
     * @param qualificationAcquisition QualificationAcquisitionDO
     * @return 受影响rows
     */
    int updateQualificationAcquisitionById(QualificationAcquisitionDO qualificationAcquisition);

    /**
     * 获取资质收购详情
     *
     * @param id 资质收购id
     * @return 受影响rows
     */
    QualificationAcquisitionDO getDetailById(Integer id);
}
