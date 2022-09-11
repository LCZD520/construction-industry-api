package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationTransferDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 资质转让表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
public interface QualificationTransferService extends IService<QualificationTransferDO> {

    /**
     * 添加资质转账记录
     *
     * @param qualificationTransfer QualificationAcquisitionDO
     * @return 受影响rows
     */
    int insert(QualificationTransferDO qualificationTransfer);

    /**
     * 获取资质转账记录
     *
     * @param page page
     * @return IPage<QualificationAcquisitionDO>
     */
    IPage<QualificationTransferDO> listQualificationTransfer(Page<QualificationTransferDO> page);

    /**
     * 更新资质转账
     *
     * @param qualificationTransfer QualificationAcquisitionDO
     * @return 受影响rows
     */
    int updateQualificationTransferById(QualificationTransferDO qualificationTransfer);

    /**
     * 获取资质转账详情
     *
     * @param id 资质转账id
     * @return 受影响rows
     */
    QualificationTransferDO getDetailById(Integer id);
}
