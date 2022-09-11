package com.industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.CertificateCategoryDO;
import com.industry.mapper.CertificateCategoryMapper;
import com.industry.service.CertificateCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 证书类别表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Service
public class CertificateCategoryServiceImpl extends ServiceImpl<CertificateCategoryMapper, CertificateCategoryDO> implements CertificateCategoryService {

    private CertificateCategoryMapper mapper;

    @Autowired
    public void setMapper(CertificateCategoryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(CertificateCategoryDO certificateCategory) {
//        synchronized (this) {
//            final CertificateCategoryDO certificateCategoryDO
//                    = mapper.selectByCategoryName(certificateCategory.getCategoryName());
//            if (certificateCategoryDO == null) {
//                return mapper.insertCategory(certificateCategory);
//            }
//            return -1;
//        }
        return mapper.insertCategory(certificateCategory);
    }

    @Override
    public List<CertificateCategoryDO> listCertificateCategoryTree() {
        QueryWrapper<CertificateCategoryDO> queryWrapper = new QueryWrapper<>();
        List<CertificateCategoryDO> listCertificateCategory = new ArrayList<>();
        queryWrapper.eq("parent_id", 0);
        // 一级分类
        List<CertificateCategoryDO> rootCategory = mapper.selectList(queryWrapper);
        queryWrapper.clear();
        queryWrapper.ne("parent_id", 0);
        // 非一级分类
        List<CertificateCategoryDO> nonRootCategory = mapper.selectList(queryWrapper);
        for (CertificateCategoryDO certificateCategoryDO : rootCategory) {
            certificateCategoryDO.setListCertificateCategory(new ArrayList<>());
            listCertificateCategory.add(getSubList(certificateCategoryDO, nonRootCategory));
        }
        return listCertificateCategory;
    }

    @Override
    public CertificateCategoryDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }

    private CertificateCategoryDO getSubList(CertificateCategoryDO rootCertificateCategory, List<CertificateCategoryDO> list) {
        for (CertificateCategoryDO certificateCategoryDO : list) {
            if (rootCertificateCategory.getId().equals(certificateCategoryDO.getParentId())) {
                rootCertificateCategory.getListCertificateCategory()
                        .add(getSubList(certificateCategoryDO, list));
            }
        }
        return rootCertificateCategory;
    }
}
