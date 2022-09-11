package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.QualificationAgencyConstructorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质代办-建造师 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
@Mapper
public interface QualificationAgencyConstructorMapper extends BaseMapper<QualificationAgencyConstructorDO> {

    /**
     * 批量插入建造师
     * @param listConstructors List<QualificationAgencyConstructorDO>
     * @return 受影响rows
     */
    int insertBatch(List<QualificationAgencyConstructorDO> listConstructors);

    List<Integer> selectIdsByQualificationAgencyId(@Param("qualificationAgencyId") Integer qualificationAgencyId);
}
