package com.industry.service;

import com.industry.bean.entity.TalentCertificatesDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.request.UpdateTalentCertificatesWhereaboutsRequest;
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
}
