package com.industry.service.impl;

import com.industry.bean.entity.TalentCertificatesWhereaboutsFlowDO;
import com.industry.mapper.TalentCertificatesWhereaboutsFlowMapper;
import com.industry.service.TalentCertificatesWhereaboutsFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人才证件去向流水表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
@Service
public class TalentCertificatesWhereaboutsFlowServiceImpl extends ServiceImpl<TalentCertificatesWhereaboutsFlowMapper, TalentCertificatesWhereaboutsFlowDO> implements TalentCertificatesWhereaboutsFlowService {

    private TalentCertificatesWhereaboutsFlowMapper mapper;

    @Autowired
    public void setMapper(TalentCertificatesWhereaboutsFlowMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TalentCertificatesWhereaboutsFlowDO> listCertificatesWhereabouts(Integer id) {
        return mapper.listCertificatesWhereabouts(id);
    }
}
