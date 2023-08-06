package com.industry.service;

import com.industry.bean.entity.TalentCertificateDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 人才证书表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
public interface TalentCertificateService extends IService<TalentCertificateDO> {

    /**
     * 获取所有人才证书
     * @return List<TalentCertificateDO>
     */
    List<TalentCertificateDO> getList();
}
