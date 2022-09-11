package com.industry.mapper;

import com.industry.bean.entity.TalentCertificatesWhereaboutsFlowDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才证件去向流水表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
@Mapper
public interface TalentCertificatesWhereaboutsFlowMapper extends BaseMapper<TalentCertificatesWhereaboutsFlowDO> {

    int insertBatch(List<TalentCertificatesWhereaboutsFlowDO> list1);

    List<TalentCertificatesWhereaboutsFlowDO> listCertificatesWhereabouts(@Param("id") Integer id);
}
