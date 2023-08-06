package com.industry.service;

import com.industry.bean.entity.QualificationAgencyPerformConfirmDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资质代办执行确认表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
public interface QualificationAgencyPerformConfirmService extends IService<QualificationAgencyPerformConfirmDO> {

    /**
     * 资质代办执行确认
     *
     * @param confirmDO QualificationAgencyPerformConfirmDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int performConfirm(QualificationAgencyPerformConfirmDO confirmDO);
}
