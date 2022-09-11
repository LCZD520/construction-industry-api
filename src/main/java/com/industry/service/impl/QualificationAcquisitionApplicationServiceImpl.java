package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationAcquisitionApplication;
import com.industry.mapper.QualificationAcquisitionApplicationMapper;
import com.industry.service.QualificationAcquisitionApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资质收购申请表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-02
 */
@Service
public class QualificationAcquisitionApplicationServiceImpl extends ServiceImpl<QualificationAcquisitionApplicationMapper, QualificationAcquisitionApplication> implements QualificationAcquisitionApplicationService {
    private QualificationAcquisitionApplicationMapper mapper;

    @Autowired
    public void setMapper(QualificationAcquisitionApplicationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public IPage<QualificationAcquisitionApplication> queryList(Page<QualificationAcquisitionApplication> page) {
        return mapper.queryList(page);
    }

    @Override
    public int insert(QualificationAcquisitionApplication qualificationAcquisitionApplication) {
        return mapper.insert(qualificationAcquisitionApplication);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public QualificationAcquisitionApplication queryById(Integer id) {
        return mapper.queryById(id);
    }
}
