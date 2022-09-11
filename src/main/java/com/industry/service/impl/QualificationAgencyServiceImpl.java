package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.QualificationAgencyConstructorDO;
import com.industry.bean.entity.QualificationAgencyDO;
import com.industry.bean.entity.QualificationAgencyOtherPersonDO;
import com.industry.mapper.QualificationAgencyConstructorMapper;
import com.industry.mapper.QualificationAgencyMapper;
import com.industry.mapper.QualificationAgencyOtherPersonMapper;
import com.industry.service.QualificationAgencyConstructorService;
import com.industry.service.QualificationAgencyOtherPersonService;
import com.industry.service.QualificationAgencyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资质代办表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
@Slf4j
@Service
public class QualificationAgencyServiceImpl extends ServiceImpl<QualificationAgencyMapper, QualificationAgencyDO> implements QualificationAgencyService {

    private QualificationAgencyMapper mapper;

    private QualificationAgencyConstructorMapper constructorMapper;

    private QualificationAgencyOtherPersonMapper otherPersonMapper;

    private QualificationAgencyConstructorService constructorService;

    private QualificationAgencyOtherPersonService otherPersonService;

    @Autowired
    public void setMapper(QualificationAgencyMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setConstructorMapper(QualificationAgencyConstructorMapper constructorMapper) {
        this.constructorMapper = constructorMapper;
    }

    @Autowired
    public void setOtherPersonMapper(QualificationAgencyOtherPersonMapper otherPersonMapper) {
        this.otherPersonMapper = otherPersonMapper;
    }

    @Autowired
    public void setConstructorService(QualificationAgencyConstructorService constructorService) {
        this.constructorService = constructorService;
    }

    @Autowired
    public void setOtherPersonService(QualificationAgencyOtherPersonService otherPersonService) {
        this.otherPersonService = otherPersonService;
    }

    @Override
    public boolean updateQualificationAgencyById(QualificationAgencyDO qualificationAgency) {
        updateById(qualificationAgency);
        List<QualificationAgencyConstructorDO> listConstructors = qualificationAgency.getListConstructors();
        log.info("listConstructors:{}", listConstructors);
        List<QualificationAgencyOtherPersonDO> listOtherPersons = qualificationAgency.getListOtherPersons();
        log.info("listOtherPersons:{}", listOtherPersons);
        List<Integer> listConstructorIds = new ArrayList<>();
        List<Integer> listOtherPersonIds = new ArrayList<>();
        Integer qualificationAgencyId = qualificationAgency.getId();
        listConstructors.forEach(item -> {
            item.setQualificationAgencyId(qualificationAgencyId);
            listConstructorIds.add(item.getId());
        });
        listOtherPersons.forEach(item -> {
            item.setQualificationAgencyId(qualificationAgencyId);
            listOtherPersonIds.add(item.getId());
        });
        final List<Integer> integers
                = constructorMapper.selectIdsByQualificationAgencyId(qualificationAgencyId);
        final List<Integer> integers2
                = otherPersonMapper.selectIdsByQualificationAgencyId(qualificationAgencyId);
        // 新的资质代办建造师
        List<QualificationAgencyConstructorDO> listNullQualificationAgencyConstructors
                = listConstructors.stream().filter(item ->
                item.getId() == null).collect(Collectors.toList());
        log.info("listNullQualificationAgencyConstructors:{}", listNullQualificationAgencyConstructors);
        // 旧的资质代办建造师
        List<QualificationAgencyConstructorDO> listExistQualificationAgencyConstructors
                = listConstructors.stream().filter(item ->
                item.getId() != null).collect(Collectors.toList());
        log.info("listExistQualificationAgencyConstructors:{}", listExistQualificationAgencyConstructors);
        if (!listNullQualificationAgencyConstructors.isEmpty()) {
            constructorService.saveBatch(listNullQualificationAgencyConstructors);
        }
        if (!listExistQualificationAgencyConstructors.isEmpty()) {
            constructorService.updateBatchById(listExistQualificationAgencyConstructors);
        }

        // 新的资质代办建造师
        List<QualificationAgencyOtherPersonDO> listNullQualificationAgencyOtherPersons
                = listOtherPersons.stream().filter(item ->
                item.getId() == null).collect(Collectors.toList());
        log.info("listNullQualificationAgencyOtherPersons:{}", listNullQualificationAgencyOtherPersons);
        // 旧的资质代办建造师
        List<QualificationAgencyOtherPersonDO> listExistQualificationAgencyOtherPersons
                = listOtherPersons.stream().filter(item ->
                item.getId() != null).collect(Collectors.toList());
        log.info("listExistQualificationAgencyOtherPersons:{}", listExistQualificationAgencyOtherPersons);
        if (!listNullQualificationAgencyOtherPersons.isEmpty()) {
            otherPersonService.saveBatch(listNullQualificationAgencyOtherPersons);
        }
        if (!listExistQualificationAgencyOtherPersons.isEmpty()) {
            otherPersonService.updateBatchById(listExistQualificationAgencyOtherPersons);
        }

        // 删除
        if (!integers.isEmpty() && !listConstructorIds.isEmpty()) {
            List<Integer> collect
                    = integers.stream()
                    .filter(item -> !listConstructorIds.contains(item))
                    .collect(Collectors.toList());
            if (!collect.isEmpty()) {
                constructorMapper.deleteBatchIds(collect);
            }
        }
        if (!integers2.isEmpty() && !listOtherPersonIds.isEmpty()) {
            List<Integer> collect
                    = integers2.stream()
                    .filter(item -> !listOtherPersonIds.contains(item))
                    .collect(Collectors.toList());
            if (!collect.isEmpty()) {
                otherPersonMapper.deleteBatchIds(collect);
            }
        }
        return true;
    }

    @Override
    public QualificationAgencyDO getQualificationAgencyById(Integer id) {
        return mapper.getQualificationAgencyById(id);
    }

    @Override
    public IPage<QualificationAgencyDO> listQualificationAgencys(Page<QualificationAgencyDO> page) {
        return mapper.listQualificationAgencys(page);
    }

    @Override
    public int insert(QualificationAgencyDO qualificationAgency) {
        int insert = mapper.insert(qualificationAgency);
        final Integer qualificationAgencyId = qualificationAgency.getId();
        log.info("qualificationAgencyId:{}", qualificationAgencyId);
        List<QualificationAgencyConstructorDO> listConstructors
                = qualificationAgency.getListConstructors();
        List<QualificationAgencyOtherPersonDO> listOtherPersons
                = qualificationAgency.getListOtherPersons();
        if (!listConstructors.isEmpty()) {
            listConstructors.forEach(
                    item -> item.setQualificationAgencyId(qualificationAgencyId));
            log.info("listConstructors:{}", listConstructors);
            constructorMapper.insertBatch(listConstructors);
        }
        if (!listOtherPersons.isEmpty()) {
            listOtherPersons.forEach(
                    item -> item.setQualificationAgencyId(qualificationAgencyId));
            log.info("listOtherPersons:{}", listOtherPersons);
            otherPersonMapper.insertBatch(listOtherPersons);
        }
        return insert;
    }
}
