package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TitleEvaluationTransferDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 职称评审转账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Mapper
public interface TitleEvaluationTransferMapper extends BaseMapper<TitleEvaluationTransferDO> {

    /**
     * 添加职称评审转账记录
     *
     * @param titleEvaluationTransferDO TitleEvaluationTransferDO
     * @return 受影响rows
     */
    int insertTransferOrder(TitleEvaluationTransferDO titleEvaluationTransferDO);

    /**
     * 获取转账记录
     *
     * @param id 职称评审订单id
     * @return List<TitleEvaluationTransferDO>
     */
    List<TitleEvaluationTransferDO> getListTransfersByOrderId(@Param("id") Integer id);

    /**
     * 分页获取获取转账记录
     *
     * @param page ListPages<TitleEvaluationTransferDO>
     * @return List<TitleEvaluationTransferDO>
     */
    List<TitleEvaluationTransferDO> getListTransferRecordsPage(@Param("page") ListPages<TitleEvaluationTransferDO> page);

    /**
     * 获取转账记录总数
     *
     * @return Long
     */
    Long getCount();

    /**
     * 获取职称评审转账详情
     *
     * @param id 职称评审转账记录id
     * @return QualificationAgencyTransferDO
     */
    TitleEvaluationTransferDO getDetailById(@Param("id") Integer id);
}
