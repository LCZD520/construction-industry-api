package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.TalentVisitDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 人才回访表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
public interface TalentVisitService extends IService<TalentVisitDO> {

    int insert(TalentVisitDO talentVisitDO);

    IPage<TalentVisitDO> listTalentVisits(Page<TalentVisitDO> page, Integer talentId);

    int updateTalentVisitById(TalentVisitDO talentVisitDO);

    int deleteById(Integer id);
}
