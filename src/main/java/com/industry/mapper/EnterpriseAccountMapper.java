package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseAccountDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 公司账户表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Mapper
public interface EnterpriseAccountMapper extends BaseMapper<EnterpriseAccountDO> {

    IPage<EnterpriseAccountDO> queryList(Page<EnterpriseAccountDO> page);

    EnterpriseAccountDO queryById(Integer id);
}
