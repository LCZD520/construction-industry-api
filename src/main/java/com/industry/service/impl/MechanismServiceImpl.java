package com.industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industry.entity.Mechanism;
import com.industry.entity.Permission;
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
public class MechanismServiceImpl extends ServiceImpl<MechanismMapper, Mechanism> implements MechanismService {

    private MechanismMapper mapper;

    @Autowired
    public void setMapper(MechanismMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(Mechanism mechanism) {
        return mapper.insert(mechanism);
    }

    @Override
    public List<Mechanism> queryListMechanisms() {
        QueryWrapper<Mechanism> queryWrapper = new QueryWrapper<>();
        List<Mechanism> mechanisms = new ArrayList<>();
        queryWrapper.eq("parent_id", 0);
        // 一级机构
        List<Mechanism> rootPermission = mapper.selectList(queryWrapper);
        queryWrapper.clear();
        queryWrapper.ne("parent_id", 0);
        // 非一级机构
        List<Mechanism> nonRootPermission = mapper.selectList(queryWrapper);
        for (Mechanism mechanism : rootPermission) {
            mechanisms.add(getSubList(mechanism, nonRootPermission));
        }
        return mechanisms;
    }

    private Mechanism getSubList(Mechanism rootMechanism, List<Mechanism> list) {
        for (Mechanism mechanism : list) {
            if (rootMechanism.getMechanismId().equals(mechanism.getParentId())) {
                rootMechanism.getSubListMechanisms().add(getSubList(mechanism, list));
            }
        }
        return rootMechanism;
    }
}
