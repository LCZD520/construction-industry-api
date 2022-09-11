package com.industry.mapper;

import com.industry.bean.entity.TalentTransferDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才转账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Mapper
public interface TalentTransferMapper extends BaseMapper<TalentTransferDO> {

    /**
     * 获取所有人才转账
     * @param id 人才id
     * @return List<TalentTransferDO>
     */
    List<TalentTransferDO> listTalentTransfers(@Param("id") Integer id);

    /**
     * 获取人才转账详情
     * @param id 人才转账id
     * @return TalentTransferDO
     */
    TalentTransferDO getDetailById(@Param("id") Integer id);
}
