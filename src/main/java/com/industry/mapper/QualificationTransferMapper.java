package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationTransferDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 资质转让表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Mapper
public interface QualificationTransferMapper extends BaseMapper<QualificationTransferDO> {

    /**
     * 获取资质转账记录
     *
     * @param page page
     * @return IPage<QualificationAcquisitionDO>
     */
    IPage<QualificationTransferDO> listQualificationTransfer(Page<QualificationTransferDO> page);
}
