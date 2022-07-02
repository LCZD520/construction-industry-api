package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统公告表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-26
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 添加公告
     * @param notice 公告实体
     * @return 1 添加成功，0 添加失败
     */
    int insert(Notice notice);

    /**
     * 公告删除
     * @param id id
     * @return 1 删除成功，0 删除失败
     */
    int delete(Integer id);

    /**
     * 查询公告列表
     * @param page 分页page对象
     * @return IPage
     */
    IPage<Notice> queryList(Page<Notice> page);

    /**
     * 通过id查询公告
     * @param id
     * @return
     */
    Notice queryById(Integer id);
}
