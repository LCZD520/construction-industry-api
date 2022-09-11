package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 资质收购表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Mapper
public interface QualificationAcquisitionMapper extends BaseMapper<QualificationAcquisitionDO> {

    /**
     * 获取资质收购记录
     *
     * @param page page
     * @return IPage<QualificationAcquisitionDO>
     */
    IPage<QualificationAcquisitionDO> listQualificationAcquisition(Page<QualificationAcquisitionDO> page);
}
