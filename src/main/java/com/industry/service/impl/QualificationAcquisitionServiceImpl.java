package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.mapper.QualificationAcquisitionMapper;
import com.industry.service.QualificationAcquisitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

/**
 * <p>
 * 资质收购表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Service
public class QualificationAcquisitionServiceImpl extends ServiceImpl<QualificationAcquisitionMapper, QualificationAcquisitionDO> implements QualificationAcquisitionService {

    private QualificationAcquisitionMapper mapper;

    @Autowired
    public void setMapper(QualificationAcquisitionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(QualificationAcquisitionDO qualificationAcquisition) {
        return mapper.insert(qualificationAcquisition);
    }

    @Override
    public IPage<QualificationAcquisitionDO> listQualificationAcquisition(Page<QualificationAcquisitionDO> page) {
        return mapper.listQualificationAcquisition(page);
    }

    @Override
    public int updateQualificationAcquisitionById(QualificationAcquisitionDO qualificationAcquisition) {
        final QualificationAcquisitionDO qualificationAcquisitionDO = mapper.selectById(qualificationAcquisition);
        if (qualificationAcquisitionDO != null) {
            return mapper.updateById(qualificationAcquisition);
        }
        return 0;
    }

    @Override
    public QualificationAcquisitionDO getDetailById(Integer id) {
        return mapper.selectById(id);
    }
}
