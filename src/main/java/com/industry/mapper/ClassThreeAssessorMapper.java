package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.ClassThreeAssessorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 三类人员人员信息表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Mapper
public interface ClassThreeAssessorMapper extends BaseMapper<ClassThreeAssessorDO> {

    /**
     * 插入三类人员人员信息
     *
     * @param listAssessors List<ClassThreeAssessorDO>
     * @return 受影响rows
     */
    int insertBatch(List<ClassThreeAssessorDO> listAssessors);

    /**
     * 删除三类人员人员信息
     *
     * @param id 三类人员id
     * @return 受影响rows
     */
    int deleteBatchByClassThreeEvaluationId(@Param("id") Integer id);

    /**
     * 删除三类人员人员信息
     *
     * @param listDeleteRecordIds 三类人员人员id集合
     * @return 受影响rows
     */
    int deleteBatchByClassThreeAssessorIds(@Param("listDeleteRecordIds") List<Integer> listDeleteRecordIds);

    /**
     * 批量更新三类人员人员
     *
     * @param listUpdateRecords   三类人员人员集合
     * @param listUpdateRecordIds 三类人员人员id集合
     * @return 受影响rows
     */
    int updateBatchByIds(@Param("listUpdateRecords") List<ClassThreeAssessorDO> listUpdateRecords
            , @Param("listUpdateRecordIds") List<Integer> listUpdateRecordIds);
}
