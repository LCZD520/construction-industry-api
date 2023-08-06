package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.AdvancedSettingDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 高级设置表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Mapper
public interface AdvancedSettingMapper extends BaseMapper<AdvancedSettingDO> {

    IPage<AdvancedSettingDO> queryList(Page<AdvancedSettingDO> page);

    AdvancedSettingDO queryById(Integer id);

    /**
     * 条件分页获取列表
     *
     * @param page       ListPages<AdvancedSettingDO>
     * @param configName configName
     * @param configCode configCode
     * @return Long
     */
    List<AdvancedSettingDO> listByConditionPages(@Param("page") ListPages<AdvancedSettingDO> page,
                                                 @Param("configName") String configName,
                                                 @Param("configCode") String configCode);

    /**
     * 条件获取列表总数
     *
     * @param configName configName
     * @param configCode configCode
     * @return Long
     */
    Long getCountByCondition(@Param("configName") String configName,
                             @Param("configCode") String configCode);
}
