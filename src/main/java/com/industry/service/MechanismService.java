package com.industry.service;

import com.industry.bean.common.SelectOptions;
import com.industry.bean.entity.MechanismDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 机构表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
public interface MechanismService extends IService<MechanismDO> {

    int insert(MechanismDO mechanism);

    List<MechanismDO> queryListMechanisms();

    /**
     * 根据id获取所有子机构
     *
     * @param id
     * @return
     */
    List<MechanismDO> getSubListMechanismsById(Integer id);

    /**
     * 根据id删除机构
     *
     * @param id id
     * @return -1删除记录存储在子机构，删除失败，0删除失败
     */
    int deleteById(Integer id);

    int updateMechanismById(MechanismDO mechanism);

    MechanismDO getById(Integer id);

    List<SelectOptions> listMechanisms();
}
