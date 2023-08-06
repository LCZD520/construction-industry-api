package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.LoginLogDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.search.LoginLogSearch;

/**
 * <p>
 * 登录日志表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-03-15
 */
public interface LoginLogService extends IService<LoginLogDO> {

    /**
     * 条件分页获取登录日志列表
     *
     * @param page   ListPages<LoginLogDO>
     * @param search LoginLogSearch
     * @return ListPages<LoginLogDO>
     */
    ListPages<LoginLogDO> listByConditionPages(ListPages<LoginLogDO> page, LoginLogSearch search);
}
