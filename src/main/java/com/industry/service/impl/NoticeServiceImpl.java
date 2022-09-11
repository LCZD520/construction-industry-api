package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.NoticeDO;
import com.industry.mapper.NoticeMapper;
import com.industry.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统公告表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-06-26
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, NoticeDO> implements NoticeService {

    private NoticeMapper noticeMapper;

    @Autowired
    public void setNoticeMapper(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    public int insert(NoticeDO notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public int delete(Integer id) {
        return noticeMapper.deleteById(id);
    }

    @Override
    public IPage<NoticeDO> queryList(Page<NoticeDO> page) {
        return noticeMapper.queryList(page);
    }

    @Override
    public NoticeDO queryById(Integer id) {
        return noticeMapper.selectById(id);
    }
}
