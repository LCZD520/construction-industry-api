package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.NoticeDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统公告表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-06-26
 */
@Mapper
public interface NoticeMapper extends BaseMapper<NoticeDO> {

    /**
     * 查询公告列表
     *
     * @param page 分页page对象
     * @return IPage
     */
    IPage<NoticeDO> queryList(Page<NoticeDO> page);

    /**
     * 分页获取已启用的公告
     *
     * @param page ListPages<NoticeDO>
     * @return List<NoticeDO>
     */
    List<NoticeDO> getListEnableds(@Param("page") ListPages<NoticeDO> page);

    /**
     * 获取公告列表总数
     *
     * @return Long
     */
    Long getCountListEnableds();

    /**
     * 条件分页获取列表
     *
     * @param page    ListPages<NoticeDO>
     * @param title   String
     * @param enabled Integer
     * @return List<NoticeDO>
     */
    List<NoticeDO> listByConditionPages(@Param("page") ListPages<NoticeDO> page, @Param("title") String title, @Param("enabled") Integer enabled);

    /**
     * 条件获取总数
     *
     * @param title   String
     * @param enabled Integer
     * @return Long
     */
    Long getCountByCondition(@Param("title") String title, @Param("enabled") Integer enabled);

}
