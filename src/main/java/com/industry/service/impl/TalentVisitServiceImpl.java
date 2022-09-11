package com.industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.TalentVisitDO;
import com.industry.mapper.TalentVisitMapper;
import com.industry.service.TalentVisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人才回访表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
@Slf4j
@Service
public class TalentVisitServiceImpl extends ServiceImpl<TalentVisitMapper, TalentVisitDO> implements TalentVisitService {

    private TalentVisitMapper mapper;

    @Autowired
    public void setMapper(TalentVisitMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(TalentVisitDO talentVisitDO) {
        return mapper.insert(talentVisitDO);
    }

    @Override
    public IPage<TalentVisitDO> listTalentVisits(Page<TalentVisitDO> page, Integer talentId) {
        return mapper.listTalentVisits(page, talentId);
    }

    @Override
    public int updateTalentVisitById(TalentVisitDO talentVisitDO) {
        TalentVisitDO talentVisit = mapper.selectById(talentVisitDO.getId());
        if (talentVisit != null) {
            UpdateWrapper<TalentVisitDO> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", talentVisitDO.getId());
            updateWrapper.set("visit_content", talentVisitDO.getVisitContent());
            updateWrapper.set("is_need_visit_again", talentVisitDO.getNeedVisitAgain());
            updateWrapper.set("visit_again_time", talentVisitDO.getVisitAgainTime());
            return mapper.update(talentVisitDO, updateWrapper);
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        TalentVisitDO talentVisitDO = mapper.selectById(id);
        if (talentVisitDO != null) {
            return mapper.deleteById(id);
        }
        return 0;
    }
}
