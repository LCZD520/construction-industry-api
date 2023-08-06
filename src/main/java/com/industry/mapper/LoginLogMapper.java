package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.LoginLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.LoginLogDO;
import com.industry.bean.search.LoginLogSearch;
import com.industry.bean.search.LoginLogSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 登录日志表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-03-15
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLogDO> {

    /**
     * 条件分页获取登录日志列表
     *
     * @param page   ListPages<LoginLogDO>
     * @param search LoginLogSearch
     * @return List<LoginLogDO>
     */
    List<LoginLogDO> listByConditionPages(
            @Param("page") ListPages<LoginLogDO> page,
            @Param("search") LoginLogSearch search);

    /**
     * 条件分页获取登录日志列表总数
     *
     * @param search LoginLogSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") LoginLogSearch search);
}
