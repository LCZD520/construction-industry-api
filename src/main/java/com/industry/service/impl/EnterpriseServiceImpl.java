package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseDO;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.mapper.EnterpriseDemandMapper;
import com.industry.mapper.EnterpriseMapper;
import com.industry.service.EnterpriseDemandService;
import com.industry.service.EnterpriseResourceDemandService;
import com.industry.service.EnterpriseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Slf4j
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, EnterpriseDO> implements EnterpriseService {

    private EnterpriseMapper mapper;

    private EnterpriseDemandMapper demandMapper;

    private EnterpriseDemandService service;

    @Autowired
    public void setMapper(EnterpriseMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setDemandMapper(EnterpriseDemandMapper demandMapper) {
        this.demandMapper = demandMapper;
    }

    @Autowired
    public void setService(EnterpriseDemandService service) {
        this.service = service;
    }

    @Override
    public int insert(EnterpriseDO enterpriseDO) {
        final int insert = mapper.insert(enterpriseDO);
        final Integer enterpriseId = enterpriseDO.getId();
        List<EnterpriseDemandDO> listEnterpriseDemands = enterpriseDO.getListEnterpriseDemands();
        listEnterpriseDemands.forEach(item -> item.setEnterpriseId(enterpriseId));
        log.info("listEnterpriseDemands:{}", listEnterpriseDemands);
        final int rows = demandMapper.insertBatch(listEnterpriseDemands);

        return insert + rows;
    }

    @Override
    public IPage<EnterpriseDO> listEnterprises(Page<EnterpriseDO> page) {
        return mapper.listEnterprises(page);
    }

    @Override
    public EnterpriseDO getEnterpriseById(Integer id) {
        return mapper.getEnterpriseById(id);
    }

    @Override
    public boolean updateEnterpriseById(EnterpriseDO enterprise) {
        updateById(enterprise);
        List<EnterpriseDemandDO> listDemands = enterprise.getListEnterpriseDemands();
        List<Integer> listDemandIds = new ArrayList<>();
        listDemands.forEach(item -> {
            item.setEnterpriseId(enterprise.getId());
            listDemandIds.add(item.getId());
        });
        final List<Integer> integers
                = demandMapper
                .selectIdsByEnterpriseId(enterprise.getId());
        // 新的企业需求
        List<EnterpriseDemandDO> listNullEnterpriseDemands
                = listDemands.stream().filter(item ->
                item.getId() == null).collect(Collectors.toList());
        // 旧的企业需求
        List<EnterpriseDemandDO> listExistEnterpriseDemands
                = listDemands.stream().filter(item ->
                item.getId() != null).collect(Collectors.toList());
        if (!listNullEnterpriseDemands.isEmpty()) {
            service.saveBatch(listNullEnterpriseDemands);
        }
        if (!listExistEnterpriseDemands.isEmpty()) {
            service.updateBatchById(listExistEnterpriseDemands);
        }
        // 删除
        if (!integers.isEmpty() && !listDemandIds.isEmpty()) {
            List<Integer> collect
                    = integers.stream()
                    .filter(item -> !listDemandIds.contains(item))
                    .collect(Collectors.toList());
            if (!collect.isEmpty()) {
                demandMapper.deleteBatchIds(collect);
            }
        }
        return true;
    }

    @Override
    public List<EnterpriseDemandDO> getEnterpriseDemandsById(Integer id) {
        return mapper.getEnterpriseDemandsById(id);
    }
}
