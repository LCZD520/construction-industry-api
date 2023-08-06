package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentCertificatesDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.TalentDO;
import com.industry.bean.request.UpdateTalentCertificatesWhereaboutsRequest;
import com.industry.bean.search.TalentCertificatesSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 人才证件表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
public interface TalentCertificatesService extends IService<TalentCertificatesDO> {

    int insertBatch(List<TalentCertificatesDO> list);

    List<TalentCertificatesDO> listCertificates(Integer id);

    int deleteById(Integer id);

    int updateCertificateBatchById(UpdateTalentCertificatesWhereaboutsRequest whereaboutsRequest);

    /**
     * 获取所有人才证件列表
     *
     * @param page ListPages<TalentDO>
     * @return ListPages<TalentDO>
     */
    ListPages<TalentDO> listAllCertificates(ListPages<TalentDO> page);

    /**
     * 获取人才证件去向详情
     *
     * @param id   人才id
     * @param page ListPages<TalentCertificatesDO>
     * @return ListPages<TalentCertificatesDO>
     */
    ListPages<TalentCertificatesDO> listPageCertificates(Integer id, ListPages<TalentCertificatesDO> page);

    /**
     * 条件分页获取人才证书列表
     * @param page ListPages<TalentDO>
     * @param search TalentCertificatesSearch
     * @return ListPages<TalentDO>
     */
    ListPages<TalentDO> listByConditionPages(ListPages<TalentDO> page, TalentCertificatesSearch search);
}
