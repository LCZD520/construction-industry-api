package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentCertificateDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.TalentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才证书表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Mapper
public interface TalentCertificateMapper extends BaseMapper<TalentCertificateDO> {

    /**
     * 插入证书
     *
     * @param listCertificates listCertificates
     * @return 受影响rows
     */
    int insertBatch(List<TalentCertificateDO> listCertificates);

    /**
     * 查询证书id集合
     *
     * @param id talentId
     * @return List<Integer>
     */
    List<Integer> selectIdsByTalentId(Integer id);

    /**
     * 获取人才id集合
     *
     * @param levelMajor        级别专业
     * @param initialConversion 初始/转注
     * @return List<Integer>
     */
    List<Integer> getTalentIdsByCondition(@Param("levelMajor") String levelMajor
            , @Param("initialConversion") Integer initialConversion);

    /**
     * 获取人才集合
     *
     * @param tenderExit          招标出场
     * @param classThreePersonnel 三类人员
     * @param listTalentIds       人才id集合
     * @param page                ListPages<TalentDO>
     * @return List<TalentDO>
     */
    List<TalentDO> getTalentsByTenderExit(@Param("tenderExit") Integer tenderExit
            , @Param("classThreePersonnel") Integer classThreePersonnel
            , @Param("list") List<Integer> listTalentIds
            , @Param("page") ListPages<TalentDO> page);

    /**
     * 获取所有证书
     * @return List<TalentCertificateDO>
     */
    List<TalentCertificateDO> list();
}
