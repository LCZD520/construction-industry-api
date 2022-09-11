package com.industry.service;

import com.industry.bean.entity.CertificateCategoryDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.PermissionDO;
import com.industry.bean.request.CertificateCategoryRequest;

import java.util.List;

/**
 * <p>
 * 证书类别表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
public interface CertificateCategoryService extends IService<CertificateCategoryDO> {

    int insert(CertificateCategoryDO certificateCategory);


    /**
     * 获取证书分类tree
     *
     * @return list-tree
     */
    List<CertificateCategoryDO> listCertificateCategoryTree();

    /**
     * 获取证书分类详情
     *
     * @param id 证书id
     * @return CertificateCategoryDO
     */
    CertificateCategoryDO getDetailById(Integer id);
}
