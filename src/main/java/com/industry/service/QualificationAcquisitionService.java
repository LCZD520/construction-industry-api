package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.QualificationAcquisition;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 资质收购表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-02
 */
public interface QualificationAcquisitionService extends IService<QualificationAcquisition> {

    IPage<QualificationAcquisition> queryList(Page<QualificationAcquisition> objectPage);

    int insert(QualificationAcquisition qualificationAcquisition);

    int deleteById(Integer id);

    QualificationAcquisition queryById(Integer id);
}
