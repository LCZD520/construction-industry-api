package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.QualificationAcquisition;
import com.industry.mapper.EnterpriseAccountMapper;
import com.industry.mapper.QualificationAcquisitionMapper;
import com.industry.service.QualificationAcquisitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资质收购表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-02
 */
@Service
public class QualificationAcquisitionServiceImpl extends ServiceImpl<QualificationAcquisitionMapper, QualificationAcquisition> implements QualificationAcquisitionService {
    private QualificationAcquisitionMapper mapper;

    @Autowired
    public void setMapper(QualificationAcquisitionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public IPage<QualificationAcquisition> queryList(Page<QualificationAcquisition> page) {

        return mapper.queryList(page);
    }

    @Override
    public int insert(QualificationAcquisition qualificationAcquisition) {
        return mapper.insert( qualificationAcquisition );
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById( id );
    }

    @Override
    public QualificationAcquisition queryById(Integer id) {
        return mapper.queryById(id);
    }
}
