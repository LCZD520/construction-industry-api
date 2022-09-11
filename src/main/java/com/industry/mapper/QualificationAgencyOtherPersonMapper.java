package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.QualificationAgencyOtherPersonDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质代办-其他人员 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
@Mapper
public interface QualificationAgencyOtherPersonMapper extends BaseMapper<QualificationAgencyOtherPersonDO> {

    /**
     * 批量插入其他人员
     * @param listOtherPersons List<QualificationAgencyOtherPersonDO>
     * @return 受影响rows
     */
    int insertBatch(List<QualificationAgencyOtherPersonDO> listOtherPersons);

    List<Integer> selectIdsByQualificationAgencyId(@Param("qualificationAgencyId") Integer qualificationAgencyId);
}
