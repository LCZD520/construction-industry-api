package com.industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industry.bean.common.SelectOptions;
import com.industry.bean.entity.MechanismDO;
import com.industry.mapper.MechanismMapper;
import com.industry.service.MechanismService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 机构表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
@Service
public class MechanismServiceImpl extends ServiceImpl<MechanismMapper, MechanismDO> implements MechanismService {

    private MechanismMapper mapper;

    @Autowired
    public void setMapper(MechanismMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(MechanismDO mechanism) {
        return mapper.insert(mechanism);
    }

    @Override
    public List<MechanismDO> queryListMechanisms() {
        QueryWrapper<MechanismDO> queryWrapper = new QueryWrapper<>();
        List<MechanismDO> mechanisms = new ArrayList<>();
        queryWrapper.eq("parent_id", 0);
        // 一级机构
        List<MechanismDO> rootPermission = mapper.selectList(queryWrapper);
        queryWrapper.clear();
        queryWrapper.ne("parent_id", 0);
        // 非一级机构
        List<MechanismDO> nonRootPermission = mapper.selectList(queryWrapper);
        for (MechanismDO mechanism : rootPermission) {
            mechanisms.add(getSubList(mechanism, nonRootPermission));
        }
        return mechanisms;
    }

    @Override
    public List<MechanismDO> getSubListMechanismsById(Integer id) {
        return mapper.getSubListMechanismsById(id);
    }

    @Override
    public int deleteById(Integer id) {
        List<MechanismDO> subList = this.getSubListMechanismsById(id);
        if (subList.isEmpty()) {
            return mapper.deleteById(id);
        }
        return -1;
    }

    @Override
    public int updateMechanismById(MechanismDO mechanism) {
        MechanismDO mechanism1 = this.getById(mechanism.getMechanismId());
        if (mechanism1 == null) {
            return 0;
        }
        return mapper.updateById(mechanism);
    }

    @Override
    public MechanismDO getById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<SelectOptions> listMechanisms() {
        return mapper.listMechanisms();
    }

    private MechanismDO getSubList(MechanismDO rootMechanism, List<MechanismDO> list) {
        rootMechanism.setSubListMechanisms(new ArrayList<>());
        for (MechanismDO mechanism : list) {
            if (rootMechanism.getMechanismId().equals(mechanism.getParentId())) {
                rootMechanism.getSubListMechanisms().add(getSubList(mechanism, list));
            }
        }
        return rootMechanism;
    }
}
