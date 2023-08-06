package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.SelectGroupOptions;
import com.industry.bean.entity.EntryRegistrationDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.search.EntryRegistrationSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 人才入账登记表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-04-18
 */
public interface EntryRegistrationService extends IService<EntryRegistrationDO> {

    /**
     * 条件获取入账登记记录
     *
     * @param page   ListPages<EntryRegistrationDO>
     * @param search EntryRegistrationSearch
     * @return ListPages<EntryRegistrationDO>
     */
    ListPages<EntryRegistrationDO> listByConditionPages(ListPages<EntryRegistrationDO> page, EntryRegistrationSearch search);

    /**
     * 分组获取客户下拉选项
     *
     * @return List<SelectGroupOptions>
     */
    List<SelectGroupOptions> getAllCustomersByGroup();

    /**
     * 插入入账登记记录
     *
     * @param entryRegistration EntryRegistrationDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(EntryRegistrationDO entryRegistration);

    /**
     * 删除入账登记
     *
     * @param id 主键id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);
}
