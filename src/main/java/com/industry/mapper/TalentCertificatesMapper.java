package com.industry.mapper;

import com.industry.bean.entity.TalentCertificatesDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.request.UpdateTalentCertificatesWhereaboutsRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才证件表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
@Mapper
public interface TalentCertificatesMapper extends BaseMapper<TalentCertificatesDO> {

    int insertBatch(@Param("list") List<TalentCertificatesDO> list);

    List<TalentCertificatesDO> listCertificates(@Param("id") Integer id);

    int updateCertificateBatchById(@Param("whereaboutsRequest") UpdateTalentCertificatesWhereaboutsRequest whereaboutsRequest);
}
