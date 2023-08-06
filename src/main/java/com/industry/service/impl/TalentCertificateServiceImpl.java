package com.industry.service.impl;

import com.industry.bean.entity.TalentCertificateDO;
import com.industry.mapper.TalentCertificateMapper;
import com.industry.service.TalentCertificateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人才证书表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Service
public class TalentCertificateServiceImpl extends ServiceImpl<TalentCertificateMapper, TalentCertificateDO> implements TalentCertificateService {

    private TalentCertificateMapper mapper;

    @Autowired
    public void setMapper(TalentCertificateMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TalentCertificateDO> getList() {
        return mapper.list();
    }
}
