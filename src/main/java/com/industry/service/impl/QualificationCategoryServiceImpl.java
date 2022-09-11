package com.industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industry.bean.entity.QualificationCategoryDO;
import com.industry.bean.entity.QualificationCategoryDO;
import com.industry.mapper.QualificationCategoryMapper;
import com.industry.service.QualificationCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资质类别表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Slf4j
@Service
public class QualificationCategoryServiceImpl extends ServiceImpl<QualificationCategoryMapper, QualificationCategoryDO> implements QualificationCategoryService {

    private QualificationCategoryMapper mapper;

    @Autowired
    public void setMapper(QualificationCategoryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<QualificationCategoryDO> listQualificationCategoryTree() {
        QueryWrapper<QualificationCategoryDO> queryWrapper = new QueryWrapper<>();
        List<QualificationCategoryDO> listQualificationCategory = new ArrayList<>();
        queryWrapper.eq("parent_id", 0);
        // 一级分类
        List<QualificationCategoryDO> rootCategory = mapper.selectList(queryWrapper);
        queryWrapper.clear();
        queryWrapper.ne("parent_id", 0);
        // 非一级分类
        List<QualificationCategoryDO> nonRootCategory = mapper.selectList(queryWrapper);
        for (QualificationCategoryDO qualificationCategoryDO : rootCategory) {
            qualificationCategoryDO.setListQualificationCategory(new ArrayList<>());
            listQualificationCategory.add(getSubList(qualificationCategoryDO, nonRootCategory));
        }
        return listQualificationCategory;
    }

    @Override
    public QualificationCategoryDO getDetailById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public int insert(QualificationCategoryDO qualificationCategory) {
        return mapper.insert(qualificationCategory);
    }

    private QualificationCategoryDO getSubList(QualificationCategoryDO rootQualificationCategory, List<QualificationCategoryDO> list) {
        for (QualificationCategoryDO qualificationCategoryDO : list) {
            if (rootQualificationCategory.getId().equals(qualificationCategoryDO.getParentId())) {
                rootQualificationCategory.getListQualificationCategory()
                        .add(getSubList(qualificationCategoryDO, list));
            }
        }
        return rootQualificationCategory;
    }
}
