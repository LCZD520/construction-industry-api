package com.industry.mapper;

import com.industry.bean.entity.QualificationAgencyPerformConfirmDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资质代办执行确认表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Mapper
public interface QualificationAgencyPerformConfirmMapper extends BaseMapper<QualificationAgencyPerformConfirmDO> {

    /**
     * 查询资质代办执行确认记录
     *
     * @param id 资质代办id
     * @return QualificationAgencyPerformConfirmDO
     */
    QualificationAgencyPerformConfirmDO selectByQualificationAgencyId(@Param("id") Integer id);
}
