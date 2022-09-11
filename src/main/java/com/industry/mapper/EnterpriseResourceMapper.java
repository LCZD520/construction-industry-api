package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseResourceDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.EnterpriseResourceDemandDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 企业资源表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-13
 */
@Mapper
public interface EnterpriseResourceMapper extends BaseMapper<EnterpriseResourceDO> {

    /**
     * 插入企业资源
     *
     * @param enterpriseResourceDO EnterpriseResourceDO
     * @return 受影响rows
     */
    int save(EnterpriseResourceDO enterpriseResourceDO);

    /**
     * 查询所有共享资源列表
     *
     * @param page Page<EnterpriseResourceDO>
     * @return IPage<EnterpriseResourceDO>
     */
    IPage<EnterpriseResourceDO> listSharedEnterpriseResources(Page<EnterpriseResourceDO> page);

    /**
     * 查询所有的企业资源
     *
     * @param page Page<EnterpriseResourceDO>
     * @return IPage<EnterpriseResourceDO>
     */
    IPage<EnterpriseResourceDO> listEnterpriseResources(Page<EnterpriseResourceDO> page);

    /**
     * 根据企业资源id查询企业资源
     *
     * @param id id
     * @return EnterpriseResourceDO
     */
    EnterpriseResourceDO getEnterpriseResourceById(Integer id);

    /**
     * 根据用户id查询企业资源
     *
     * @param page   Page<EnterpriseResourceDO>
     * @param userId userId
     * @return IPage<EnterpriseResourceDO>
     */
    IPage<EnterpriseResourceDO> listEnterpriseResourcesByUserId(Page<EnterpriseResourceDO> page, @Param("userId") Integer userId);

}
