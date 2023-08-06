package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.QualificationAgencyDO;
import com.industry.bean.entity.QualificationAgencyPerformConfirmDO;
import com.industry.mapper.QualificationAgencyMapper;
import com.industry.mapper.QualificationAgencyPerformConfirmMapper;
import com.industry.service.QualificationAgencyPerformConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资质代办执行确认表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Service
public class QualificationAgencyPerformConfirmServiceImpl extends ServiceImpl<QualificationAgencyPerformConfirmMapper, QualificationAgencyPerformConfirmDO> implements QualificationAgencyPerformConfirmService {

    private QualificationAgencyPerformConfirmMapper mapper;

    private QualificationAgencyMapper qualificationAgencyMapper;

    @Autowired
    public void setMapper(QualificationAgencyPerformConfirmMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setQualificationAgencyMapper(QualificationAgencyMapper qualificationAgencyMapper) {
        this.qualificationAgencyMapper = qualificationAgencyMapper;
    }

    @Override
    public int performConfirm(QualificationAgencyPerformConfirmDO confirmDO) {
        synchronized (this) {
            final Integer id = confirmDO.getQualificationAgencyId();
            final QualificationAgencyDO qualificationAgencyDO = qualificationAgencyMapper.selectById(id);
            final QualificationAgencyPerformConfirmDO qualificationAgencyPerformConfirmDO
                    = mapper.selectByQualificationAgencyId(id);
            if (qualificationAgencyDO != null) {
                if (qualificationAgencyPerformConfirmDO == null) {
                    final Boolean confirmResult = confirmDO.getConfirmResult();
                    int status = confirmResult ? 2 : 4;
                    qualificationAgencyMapper.updateStatusById(status, id);
                    return mapper.insert(confirmDO);
                } else {
                    return -1;
                }
            }
            return -2;
        }
    }
}
