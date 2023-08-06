package com.industry.service;

import com.industry.bean.entity.QualificationCategoryDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质类别表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
public interface QualificationCategoryService extends IService<QualificationCategoryDO> {

    /**
     * 获取资质分类tree
     *
     * @return list-tree
     */
    List<QualificationCategoryDO> listQualificationCategoryTree();

    /**
     * 获取资质分类详情
     *
     * @param id 证书id
     * @return CertificateCategoryDO
     */
    QualificationCategoryDO getDetailById(Integer id);

    /**
     * 插入资质类别
     *
     * @param qualificationCategory QualificationCategoryDO
     * @return 受影响rows
     */
    int insert(QualificationCategoryDO qualificationCategory);

    /**
     * 删除资质分类
     *
     * @param id id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);
}
