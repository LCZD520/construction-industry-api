package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.TalentResourceCertificateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 人才资源证书表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-12
 */
@Mapper
public interface TalentResourceCertificateMapper extends BaseMapper<TalentResourceCertificateDO> {

    /**
     * 插入证书
     *
     * @param listCertificates listCertificates
     * @return 受影响rows
     */
    int insertBatch(List<TalentResourceCertificateDO> listCertificates);

    /**
     * 查询证书id集合
     *
     * @param id talentResourceId
     * @return List<Integer>
     */
    List<Integer> selectIdsByTalentResourceId(Integer id);

}
