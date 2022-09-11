package com.industry.mapper;

import com.industry.bean.entity.CertificateCategoryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 证书类别表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Mapper
public interface CertificateCategoryMapper extends BaseMapper<CertificateCategoryDO> {


    /**
     * 插入分类
     * @param certificateCategory CertificateCategoryDO实体
     * @return 受影响行数
     */
    int insertCategory(CertificateCategoryDO certificateCategory);

    CertificateCategoryDO getDetailById(@Param("id") Integer id);
}
