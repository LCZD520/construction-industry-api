package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseResourceDO;
import com.industry.bean.entity.EnterpriseResourceDemandDO;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.EnterpriseResourceDemandDO;
import com.industry.mapper.EnterpriseDemandMapper;
import com.industry.mapper.EnterpriseResourceDemandMapper;
import com.industry.mapper.EnterpriseResourceMapper;
import com.industry.service.EnterpriseResourceDemandService;
import com.industry.service.EnterpriseResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业资源表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-13
 */
@Slf4j
@Service
public class EnterpriseResourceServiceImpl extends ServiceImpl<EnterpriseResourceMapper, EnterpriseResourceDO> implements EnterpriseResourceService {

    private EnterpriseResourceMapper mapper;

    private EnterpriseResourceDemandMapper enterpriseResourceDemandMapper;

    private EnterpriseResourceDemandService service;

    @Autowired
    public void setMapper(EnterpriseResourceMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setService(EnterpriseResourceDemandService service) {
        this.service = service;
    }

    @Autowired
    public void setEnterpriseResourceDemandMapper(EnterpriseResourceDemandMapper enterpriseResourceDemandMapper) {
        this.enterpriseResourceDemandMapper = enterpriseResourceDemandMapper;
    }

    @Override
    public int insert(EnterpriseResourceDO enterpriseResourceDO) {
        int save = mapper.save(enterpriseResourceDO);
        Integer id = enterpriseResourceDO.getId();
        List<EnterpriseResourceDemandDO> listEnterpriseDemands = enterpriseResourceDO.getListEnterpriseDemands();
        if (!listEnterpriseDemands.isEmpty()) {
            listEnterpriseDemands.forEach(item -> item.setEnterpriseResourceId(id));
            enterpriseResourceDemandMapper.insertBatch(listEnterpriseDemands);
        }
        return save;
    }

    @Override
    public EnterpriseResourceDO getEnterpriseResourceById(Integer id) {
        return mapper.getEnterpriseResourceById(id);
    }

    @Override
    public IPage<EnterpriseResourceDO> listEnterpriseResources(Page<EnterpriseResourceDO> page) {
        return mapper.listEnterpriseResources(page);
    }

    @Override
    public IPage<EnterpriseResourceDO> listSharedEnterpriseResources(Page<EnterpriseResourceDO> page) {
        return mapper.listSharedEnterpriseResources(page);
    }

    @Override
    public IPage<EnterpriseResourceDO> listEnterpriseResourcesByUserId(Page<EnterpriseResourceDO> page, Integer userId) {
        return mapper.listEnterpriseResourcesByUserId(page, userId);
    }

    @Override
    public boolean updateEnterpriseResourceById(EnterpriseResourceDO enterprise) {
        updateById(enterprise);
        List<EnterpriseResourceDemandDO> listDemands = enterprise.getListEnterpriseDemands();
        List<Integer> listDemandIds = new ArrayList<>();
        listDemands.forEach(item -> {
            item.setEnterpriseResourceId(enterprise.getId());
            listDemandIds.add(item.getId());
        });
        final List<Integer> integers
                = enterpriseResourceDemandMapper
                .selectIdsByEnterpriseResourceId(enterprise.getId());
        // 新的企业需求
        List<EnterpriseResourceDemandDO> listNullEnterpriseResourceDemands
                = listDemands.stream().filter(item ->
                item.getId() == null).collect(Collectors.toList());
        // 旧的企业需求
        List<EnterpriseResourceDemandDO> listExistEnterpriseResourceDemands
                = listDemands.stream().filter(item ->
                item.getId() != null).collect(Collectors.toList());
        if (!listNullEnterpriseResourceDemands.isEmpty()) {
            service.saveBatch(listNullEnterpriseResourceDemands);
        }
        if (!listExistEnterpriseResourceDemands.isEmpty()) {
            service.updateBatchById(listExistEnterpriseResourceDemands);
        }
        // 删除
        if (!integers.isEmpty() && !listDemandIds.isEmpty()) {
            List<Integer> collect
                    = integers.stream()
                    .filter(item -> !listDemandIds.contains(item))
                    .collect(Collectors.toList());
            if (!collect.isEmpty()) {
                enterpriseResourceDemandMapper.deleteBatchIds(collect);
            }
        }
        return true;
    }
}
