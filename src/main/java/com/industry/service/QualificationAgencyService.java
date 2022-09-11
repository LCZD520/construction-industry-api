package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationAgencyDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资质代办表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
public interface QualificationAgencyService extends IService<QualificationAgencyDO> {

    /**
     * 更新资质代办
     * @param qualificationAgency QualificationAgencyDO
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    boolean updateQualificationAgencyById(QualificationAgencyDO qualificationAgency);

    /**
     * 获取资质代办
     * @param id 资质代办id
     * @return QualificationAgencyDO
     */
    QualificationAgencyDO getQualificationAgencyById(Integer id);

    /**
     * 获取所有的资质代办列表
     *
     * @param page (Page<QualificationAgencyDO>
     * @return IPage<QualificationAgencyDO>
     */
    IPage<QualificationAgencyDO> listQualificationAgencys(Page<QualificationAgencyDO> page);

    /**
     * 插入资质代办
     * @param qualificationAgency QualificationAgencyDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(QualificationAgencyDO qualificationAgency);
}
