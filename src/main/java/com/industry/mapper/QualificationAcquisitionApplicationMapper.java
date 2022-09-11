package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationAcquisitionApplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 资质收购申请表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-02
 */
@Mapper
public interface QualificationAcquisitionApplicationMapper extends BaseMapper<QualificationAcquisitionApplication> {

    IPage<QualificationAcquisitionApplication> queryList(Page<QualificationAcquisitionApplication> page);

    QualificationAcquisitionApplication queryById(Integer id);
}
