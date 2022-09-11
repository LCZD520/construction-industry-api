package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationAgencyDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资质代办表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
@Mapper
public interface QualificationAgencyMapper extends BaseMapper<QualificationAgencyDO> {

    /**
     * 获取所有的资质代办列表
     *
     * @param page (Page<QualificationAgencyDO>
     * @return IPage<QualificationAgencyDO>
     */
    IPage<QualificationAgencyDO> listQualificationAgencys(Page<QualificationAgencyDO> page);

    /**
     * 获取资质代办
     * @param id 资质代办id
     * @return QualificationAgencyDO
     */
    QualificationAgencyDO getQualificationAgencyById(@Param("id") Integer id);
}
