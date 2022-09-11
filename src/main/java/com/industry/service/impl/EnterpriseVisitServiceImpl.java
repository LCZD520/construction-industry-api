package com.industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.EnterpriseVisitDO;
import com.industry.mapper.EnterpriseVisitMapper;
import com.industry.service.EnterpriseVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业回访表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-10
 */
@Service
public class EnterpriseVisitServiceImpl extends ServiceImpl<EnterpriseVisitMapper, EnterpriseVisitDO> implements EnterpriseVisitService {

    private EnterpriseVisitMapper mapper;

    @Autowired
    public void setMapper(EnterpriseVisitMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int updateEnterpriseVisitById(EnterpriseVisitDO enterpriseVisitDO) {
        EnterpriseVisitDO enterpriseVisit = mapper.selectById(enterpriseVisitDO.getId());
        if (enterpriseVisit != null) {
            UpdateWrapper<EnterpriseVisitDO> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", enterpriseVisitDO.getId());
            updateWrapper.set("visit_content", enterpriseVisitDO.getVisitContent());
            updateWrapper.set("is_need_visit_again", enterpriseVisitDO.getNeedVisitAgain());
            updateWrapper.set("visit_again_time", enterpriseVisitDO.getVisitAgainTime());
            return mapper.update(enterpriseVisitDO, updateWrapper);
        }
        return 0;
    }

    @Override
    public IPage<EnterpriseVisitDO> listEnterpriseVisits(Page<EnterpriseVisitDO> page, Integer enterpriseId) {
        return mapper.listEnterpriseVisits(page, enterpriseId);
    }

    @Override
    public int deleteById(Integer id) {
        EnterpriseVisitDO enterpriseVisitDO = mapper.selectById(id);
        if (enterpriseVisitDO != null) {
            return mapper.deleteById(id);
        }
        return 0;
    }

    @Override
    public int insert(EnterpriseVisitDO enterpriseVisitDO) {
        return mapper.insert(enterpriseVisitDO);
    }
}
