package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统公告表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-06-26
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 查询公告列表
     * @param page 分页page对象
     * @return IPage
     */
    IPage<Notice> queryList(Page<Notice> page);
}
