package com.industry.service;

import com.industry.bean.entity.TalentCertificatesWhereaboutsFlowDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 人才证件去向流水表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
public interface TalentCertificatesWhereaboutsFlowService extends IService<TalentCertificatesWhereaboutsFlowDO> {

    List<TalentCertificatesWhereaboutsFlowDO> listCertificatesWhereabouts(Integer id);
}
