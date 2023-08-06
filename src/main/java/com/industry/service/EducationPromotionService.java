package com.industry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EducationPromotionAssessorDO;
import com.industry.bean.entity.EducationPromotionDO;
import com.industry.bean.request.RemarksRequest;
import com.industry.bean.search.EducationPromotionSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 学历提升表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
public interface EducationPromotionService extends IService<EducationPromotionDO> {
    /**
     * 插入学历提升信息
     *
     * @param educationPromotion EducationPromotionDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(EducationPromotionDO educationPromotion);

    /**
     * 获取学历提升信息详情
     *
     * @param id 学历提升信息id
     * @return EducationPromotionDO
     */
    EducationPromotionDO getDetailById(Integer id);

    /**
     * 获取待评审人员
     *
     * @param page ListPages<EducationPromotionAssessorDO> page
     * @param id   学历提升id
     * @return ListPages<EducationPromotionAssessorDO>
     */
    ListPages<EducationPromotionAssessorDO> getWaitAssessor(ListPages<EducationPromotionAssessorDO> page, Integer id);

    /**
     * 获取未选待评审人员
     *
     * @param page            ListPages<EducationPromotionAssessorDO> page
     * @param id              学历提升id
     * @param listSelectedIds List<Integer> 已选学历提升人员id
     * @return ListPages<EducationPromotionAssessorDO>
     */
    ListPages<EducationPromotionAssessorDO> getUnselectedWaitAssessor(ListPages<EducationPromotionAssessorDO> page, Integer id, List<Integer> listSelectedIds);

    /**
     * 下单
     *
     * @param id              学历提升id
     * @param listSelectedIds 评审人员
     * @return ResultEntity
     */
    @Transactional(rollbackFor = Exception.class)
    ResultEntity placeOrder(Integer id, List<Integer> listSelectedIds);

    /**
     * 更新备注
     *
     * @param request RemarksRequest
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateRemarks(RemarksRequest request);

    /**
     * 更新学历提升信息
     *
     * @param educationPromotion EducationPromotionDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateEducationPromotion(EducationPromotionDO educationPromotion);

    /**
     * 条件获取学历提升记录
     *
     * @param page               ListPages<EducationPromotionDO>
     * @param educationPromotion 搜索条件
     * @return ListPages<EducationPromotionDO>
     */
    ListPages<EducationPromotionDO> listEducationPromotions(ListPages<EducationPromotionDO> page, EducationPromotionSearch educationPromotion);

    /**
     * 删除学历提升
     *
     * @param id 学历提升信息id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);

    /**
     * 恢复数据
     *
     * @param id 人才id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int recoveryById(Integer id);
}
