package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationTransferDO;
import com.industry.mapper.QualificationTransferMapper;
import com.industry.service.QualificationTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资质转让表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Service
public class QualificationTransferServiceImpl extends ServiceImpl<QualificationTransferMapper, QualificationTransferDO> implements QualificationTransferService {

    private QualificationTransferMapper mapper;

    @Autowired
    public void setMapper(QualificationTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(QualificationTransferDO qualificationTransfer) {
        return mapper.insert(qualificationTransfer);
    }

    @Override
    public IPage<QualificationTransferDO> listQualificationTransfer(Page<QualificationTransferDO> page) {
        return mapper.listQualificationTransfer(page);
    }

    @Override
    public int updateQualificationTransferById(QualificationTransferDO qualificationTransfer) {
        QualificationTransferDO qualificationTransferDO = mapper.selectById(qualificationTransfer);
        if (qualificationTransferDO != null) {
            mapper.updateById(qualificationTransfer);
        }
        return 0;
    }

    @Override
    public QualificationTransferDO getDetailById(Integer id) {
        return mapper.selectById(id);
    }
}
