package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAgencyEntryDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.search.QualificationAgencyEntrySearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质代办入账表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-02-26
 */
public interface QualificationAgencyEntryService extends IService<QualificationAgencyEntryDO> {

    /**
     * 资质代办入账添加
     *
     * @param qualificationAgencyEntryDO QualificationAgencyEntryDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(QualificationAgencyEntryDO qualificationAgencyEntryDO);

    /**
     * 获取所有入账记录
     *
     * @param id 资质代办id
     * @return List<QualificationAgencyEntryDO>
     */
    List<QualificationAgencyEntryDO> getList(Integer id);

    /**
     * 分页获取资质代办入账列表
     *
     * @param page   ListPages<QualificationAgencyEntryDO>
     * @param search QualificationAgencyEntrySearch
     * @return ListPages<QualificationAgencyEntryDO>
     */
    ListPages<QualificationAgencyEntryDO> listByConditionPages(ListPages<QualificationAgencyEntryDO> page, QualificationAgencyEntrySearch search);

    /**
     * 获取资质代办入账
     *
     * @param id 主键id
     * @return QualificationAgencyEntryDO
     */
    QualificationAgencyEntryDO getDetailById(Integer id);
}
