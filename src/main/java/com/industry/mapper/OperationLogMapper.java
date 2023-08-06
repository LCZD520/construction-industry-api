package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.OperationLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.OperationLogSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLogDO> {

    /**
     * 条件分页获取操作日志列表
     *
     * @param page   ListPages<OperationLogDO>
     * @param search OperationLogSearch
     * @return List<OperationLogDO>
     */
    List<OperationLogDO> listByConditionPages(
            @Param("page") ListPages<OperationLogDO> page,
            @Param("search") OperationLogSearch search);

    /**
     * 条件分页获取操作日志列表总数
     *
     * @param search OperationLogSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") OperationLogSearch search);
}
