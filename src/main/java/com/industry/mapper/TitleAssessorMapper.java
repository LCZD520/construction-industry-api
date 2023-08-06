package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.TitleAssessorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 职称评审人员信息表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Mapper
public interface TitleAssessorMapper extends BaseMapper<TitleAssessorDO> {

    /**
     * 插入职称评审人员信息
     *
     * @param listAssessors List<TitleAssessorDO>
     * @return 受影响rows
     */
    int insertBatch(List<TitleAssessorDO> listAssessors);

    /**
     * 删除职称评审人员信息
     *
     * @param id 职称评审id
     * @return 受影响rows
     */
    int deleteBatchByTitleEvaluationId(@Param("id") Integer id);

    /**
     * 删除职称评审人员信息
     *
     * @param listDeleteRecordIds 职称评审人员id集合
     * @return 受影响rows
     */
    int deleteBatchByTitleAssessorIds(@Param("listDeleteRecordIds") List<Integer> listDeleteRecordIds);

    /**
     * 批量更新职称评审人员
     *
     * @param listUpdateRecords   职称评审人员集合
     * @param listUpdateRecordIds 职称评审人员id集合
     * @return 受影响rows
     */
    int updateBatchByIds(@Param("listUpdateRecords") List<TitleAssessorDO> listUpdateRecords
            , @Param("listUpdateRecordIds") List<Integer> listUpdateRecordIds);
}
