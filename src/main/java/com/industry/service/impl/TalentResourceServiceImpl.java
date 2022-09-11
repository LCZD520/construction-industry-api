package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.TalentResourceCertificateDO;
import com.industry.bean.entity.TalentResourceDO;
import com.industry.mapper.TalentResourceCertificateMapper;
import com.industry.mapper.TalentResourceMapper;
import com.industry.service.TalentResourceCertificateService;
import com.industry.service.TalentResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 人才资源表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-12
 */
@Slf4j
@Service
public class TalentResourceServiceImpl extends ServiceImpl<TalentResourceMapper, TalentResourceDO> implements TalentResourceService {

    private TalentResourceMapper mapper;

    private TalentResourceCertificateMapper talentResourceCertificateMapper;

    private TalentResourceCertificateService service;

    @Autowired
    public void setMapper(TalentResourceMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setTalentResourceCertificateMapper(TalentResourceCertificateMapper talentResourceCertificateMapper) {
        this.talentResourceCertificateMapper = talentResourceCertificateMapper;
    }

    @Autowired
    public void setService(TalentResourceCertificateService service) {
        this.service = service;
    }

    @Override
    public boolean updateTalentResourceById(TalentResourceDO talentResource) {
        updateById(talentResource);
        List<TalentResourceCertificateDO> listCertificates = talentResource.getListCertificates();
        List<Integer> listCertificateIds = new ArrayList<>();
        listCertificates.forEach(item -> {
            item.setTalentResourceId(talentResource.getId());
            listCertificateIds.add(item.getId());
        });
        final List<Integer> integers
                = talentResourceCertificateMapper.selectIdsByTalentResourceId(talentResource.getId());
        int size = listCertificateIds.size();
        final int size1 = integers.size();
        if (size >= size1) {
            // 保存或修改
            return service.saveOrUpdateBatch(listCertificates);
        }
        // 删除
        List<Integer> collect
                = integers.stream()
                .filter(item -> !listCertificateIds.contains(item))
                .collect(Collectors.toList());
        talentResourceCertificateMapper.deleteBatchIds(collect);
        return true;
    }

    @Override
    public IPage<TalentResourceDO> listTalentResources(Page<TalentResourceDO> page) {
        return mapper.listTalentResources(page);
    }

    @Override
    public TalentResourceDO getTalentResourceById(Integer id) {
        return mapper.getTalentResourceById(id);
    }

    @Override
    public int insert(TalentResourceDO talentResource) {
        log.info("talentResource:{}", talentResource);
        int save = mapper.save(talentResource);
        Integer id = talentResource.getId();
        log.info("talentResourceId:{}", id);
        List<TalentResourceCertificateDO> listCertificates
                = talentResource.getListCertificates();
        log.info("listCertificates:{}", listCertificates);
        if (listCertificates != null
                && !listCertificates.isEmpty()) {
            listCertificates.forEach(item -> item.setTalentResourceId(id));
            talentResourceCertificateMapper.insertBatch(listCertificates);
        }
        return save;
    }

    @Override
    public IPage<TalentResourceDO> listTalentResourcesByUserId(Page<TalentResourceDO> page, Integer userId) {
        return mapper.listTalentResourcesByUserId(page, userId);
    }

    @Override
    public IPage<TalentResourceDO> listSharedTalentResources(Page<TalentResourceDO> page) {
        return mapper.listSharedTalentResources(page);
    }
}
