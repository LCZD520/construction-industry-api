package com.industry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.ClassThreeAssessorDO;
import com.industry.bean.entity.ClassThreePersonDO;
import com.industry.bean.request.RemarksRequest;
import com.industry.bean.search.ClassThreePersonSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 三类人员表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
public interface ClassThreePersonService extends IService<ClassThreePersonDO> {
    /**
     * 插入三类人员信息
     *
     * @param classThreePerson ClassThreePersonDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(ClassThreePersonDO classThreePerson);

    /**
     * 获取三类人员信息详情
     *
     * @param id 三类人员信息id
     * @return ClassThreePersonDO
     */
    ClassThreePersonDO getDetailById(Integer id);

    /**
     * 获取待评审人员
     *
     * @param page ListPages<ClassThreeAssessorDO> page
     * @param id   三类人员id
     * @return ListPages<ClassThreeAssessorDO>
     */
    ListPages<ClassThreeAssessorDO> getWaitAssessor(ListPages<ClassThreeAssessorDO> page, Integer id);

    /**
     * 获取未选待评审人员
     *
     * @param page            ListPages<ClassThreeAssessorDO> page
     * @param id              三类人员id
     * @param listSelectedIds List<Integer> 已选三类人员人员id
     * @return ListPages<ClassThreeAssessorDO>
     */
    ListPages<ClassThreeAssessorDO> getUnselectedWaitAssessor(ListPages<ClassThreeAssessorDO> page, Integer id, List<Integer> listSelectedIds);

    /**
     * 下单
     *
     * @param id              三类人员id
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
     * 更新三类人员信息
     *
     * @param classThreePerson ClassThreePersonDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateClassThreePerson(ClassThreePersonDO classThreePerson);

    /**
     * 条件获取三类人员记录
     *
     * @param page            ListPages<ClassThreePersonDO>
     * @param classThreePerson 搜索条件
     * @return ListPages<ClassThreePersonDO>
     */
    ListPages<ClassThreePersonDO> listClassThreePersons(ListPages<ClassThreePersonDO> page, ClassThreePersonSearch classThreePerson);

    /**
     * 删除三类人员
     *
     * @param id 三类人员信息id
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
