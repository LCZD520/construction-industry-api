package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationAcquisitionApplication;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 资质收购申请表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-02
 */
public interface QualificationAcquisitionApplicationService extends IService<QualificationAcquisitionApplication> {

    IPage<QualificationAcquisitionApplication> queryList(Page<QualificationAcquisitionApplication> objectPage);

    int insert(QualificationAcquisitionApplication qualificationAcquisitionApplication);

    int deleteById(Integer id);

    QualificationAcquisitionApplication queryById(Integer id);
}
