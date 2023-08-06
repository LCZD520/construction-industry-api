package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.AdvancedSettingDO;
import com.industry.bean.entity.NoticeDO;
import com.industry.mapper.NoticeMapper;
import com.industry.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private NoticeMapper mapper;

    @Autowired
    public void setNoticeMapper(NoticeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(NoticeDO notice) {
        return mapper.insert(notice);
    }

    @Override
    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public IPage<NoticeDO> queryList(Page<NoticeDO> page) {
        return mapper.queryList(page);
    }

    @Override
    public NoticeDO queryById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public ListPages<NoticeDO> getListEnableds(ListPages<NoticeDO> page) {
        List<NoticeDO> list
                = mapper.getListEnableds(page);
        page.setTotal(mapper.getCountListEnableds());
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        page.setList(list);
        return page;
    }

    @Override
    public ListPages<NoticeDO> listByConditionPages(ListPages<NoticeDO> page, String title, Integer enabled) {
        final List<NoticeDO> list = mapper.listByConditionPages(page, title, enabled);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(title, enabled));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }
}
