package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.EducationPromotionAssessorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 学历提升人员信息表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Mapper
public interface EducationPromotionAssessorMapper extends BaseMapper<EducationPromotionAssessorDO> {

    /**
     * 插入学历提升人员信息
     *
     * @param listAssessors List<EducationPromotionAssessorDO>
     * @return 受影响rows
     */
    int insertBatch(List<EducationPromotionAssessorDO> listAssessors);

    /**
     * 删除学历提升人员信息
     *
     * @param id 学历提升id
     * @return 受影响rows
     */
    int deleteBatchByEducationPromotionId(@Param("id") Integer id);

    /**
     * 删除学历提升人员信息
     *
     * @param listDeleteRecordIds 学历提升人员id集合
     * @return 受影响rows
     */
    int deleteBatchByEducationPromotionAssessorIds(@Param("listDeleteRecordIds") List<Integer> listDeleteRecordIds);

    /**
     * 批量更新学历提升人员
     *
     * @param listUpdateRecords   学历提升人员集合
     * @param listUpdateRecordIds 学历提升人员id集合
     * @return 受影响rows
     */
    int updateBatchByIds(@Param("listUpdateRecords") List<EducationPromotionAssessorDO> listUpdateRecords
            , @Param("listUpdateRecordIds") List<Integer> listUpdateRecordIds);
}
