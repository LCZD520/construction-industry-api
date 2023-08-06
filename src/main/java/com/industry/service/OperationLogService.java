package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.OperationLogDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.search.OperationLogSearch;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
public interface OperationLogService extends IService<OperationLogDO> {

    /**
     * 分页获取操作日志列表
     *
     * @param page   ListPages<OperationLogDO>
     * @param search OperationLogSearch
     * @return ListPages<OperationLogDO>
     */
    ListPages<OperationLogDO> listByConditionPages(ListPages<OperationLogDO> page, OperationLogSearch search);

}
