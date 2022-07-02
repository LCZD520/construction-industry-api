package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.QualificationAcquisition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 资质收购表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-02
 */
@Mapper
public interface QualificationAcquisitionMapper extends BaseMapper<QualificationAcquisition> {

    IPage<QualificationAcquisition> queryList(Page<QualificationAcquisition> page);

    QualificationAcquisition queryById(Integer id);
}
