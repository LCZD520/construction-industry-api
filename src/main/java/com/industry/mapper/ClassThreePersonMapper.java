package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.ClassThreePersonDO;
import com.industry.bean.entity.ClassThreeAssessorDO;
import com.industry.bean.request.RemarksRequest;
import com.industry.bean.search.ClassThreePersonSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 三类人员表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Mapper
public interface ClassThreePersonMapper extends BaseMapper<ClassThreePersonDO> {
    /**
     * 插入三类人员信息
     *
     * @param classThreePerson ClassThreePersonDO
     * @return 受影响rows
     */
    int insertClassThreePerson(ClassThreePersonDO classThreePerson);

    /**
     * 三类人员信息总数
     *
     * @return Long
     */
    Long getCount();

    /**
     * 获取三类人员信息
     *
     * @param page ListPages<ClassThreePersonDO>
     * @return List<ClassThreePersonDO>
     */
    List<ClassThreePersonDO> getListClassThreePersons(@Param("page") ListPages<ClassThreePersonDO> page);

    /**
     * 根据id查找记录总数
     *
     * @param id 三类人员信息id
     * @return rows
     */
    int getCountById(@Param("id") Integer id);

    /**
     * 删除三类人员信息
     *
     * @param id 三类人员信息id
     * @return 受影响rows
     */
    int deleteClassThreePersonById(@Param("id") Integer id);

    /**
     * 获取三类人员信息详情
     *
     * @param id 三类人员信息id
     * @return ClassThreePersonDO
     */
    ClassThreePersonDO getDetailById(@Param("id") Integer id);

    /**
     * 获取待评审人员
     *
     * @param page ListPages<ClassThreeAssessorDO> page
     * @param id   三类人员id
     * @return ListPages<ClassThreeAssessorDO>
     */
    List<ClassThreeAssessorDO> getWaitAssessor(
            @Param("page") ListPages<ClassThreeAssessorDO> page
            , @Param("id") Integer id);

    /**
     * 获取待评审人员数
     *
     * @param id 三类人员id
     * @return Long
     */
    Long getCountWaitAssessorById(@Param("id") Integer id);

    /**
     * 获取待评审人员数
     *
     * @param page            ListPages<ClassThreeAssessorDO> page
     * @param id              三类人员id
     * @param listSelectedIds List<Integer> 已选三类人员人员id
     * @return Long
     */
    Long getCountUnselectedWaitAssessorById(
            @Param("page") ListPages<ClassThreeAssessorDO> page
            , @Param("id") Integer id, @Param("listSelectedIds") List<Integer> listSelectedIds);

    /**
     * 获取未选择待评审人员数
     *
     * @param page            ListPages<ClassThreeAssessorDO> page
     * @param id              三类人员id
     * @param listSelectedIds List<Integer> 已选三类人员人员id
     * @return Long
     */
    List<ClassThreeAssessorDO> getUnselectedWaitAssessor(
            @Param("page") ListPages<ClassThreeAssessorDO> page,
            @Param("id") Integer id, @Param("listSelectedIds") List<Integer> listSelectedIds);

    /**
     * 获取未选择待评审人员id
     *
     * @param id 三类人员id
     * @return Long
     */
    List<Integer> getUnselectedWaitAssessorIds(@Param("id") Integer id);

    /**
     * 查找已下单人员的姓名
     *
     * @param list 已下单人员id
     * @return List<String>
     */
    List<String> selectFullNameBatchByIds(@Param("id") Integer id, @Param("list") List<Integer> list);

    /**
     * 更新评审人员状态
     *
     * @param id              订单id
     * @param listSelectedIds 已选评审人员id
     * @return 受影响rows
     */
    int updateBatchAssessorStatusByIds(
            @Param("id") Integer id,
            @Param("listSelectedIds") List<Integer> listSelectedIds);

    /**
     * 获取当日最大订单号
     *
     * @param today String
     * @return String
     */
    String getMaxOrderno(@Param("today") String today);

    /**
     * 获取代办总金额
     *
     * @param id              三类人员id
     * @param listSelectedIds 已选评审人员id
     * @return BigDecimal
     */
    BigDecimal getTotalAmount(
            @Param("id") Integer id,
            @Param("listSelectedIds") List<Integer> listSelectedIds
    );

    /**
     * 更新备注
     *
     * @param remarks RemarksRequest
     * @return 受影响rows
     */
    int updateRemarks(@Param("remarks") RemarksRequest remarks);

    /**
     * 更新三类人员记录状态
     *
     * @param id     三类人员id
     * @param status 状态
     * @return 受影响rows
     */
    int updateStatus(Integer id, int status);

    /**
     * 更新职称记录
     *
     * @param classThreePerson ClassThreePersonDO
     * @return 受影响rows
     */
    int updateClassThreePersonById(@Param("classThreePerson") ClassThreePersonDO classThreePerson);

    /**
     * 获取三类人员人员id集合
     *
     * @param id 三类人员id
     * @return List<Integer>
     */
    List<Integer> getListClassThreePersonsIds(@Param("id") Integer id);

    /**
     * 条件获取三类人员列表总数
     *
     * @param search ClassThreePersonSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") ClassThreePersonSearch search);

    /**
     * 条件分页获取三类人员列表
     *
     * @param page   ListPages<ClassThreePersonDO>
     * @param search ClassThreePersonSearch
     * @return List<ClassThreePersonDO>
     */
    List<ClassThreePersonDO> listClassThreePersonsByConditionPages(
            @Param("page") ListPages<ClassThreePersonDO> page,
            @Param("search") ClassThreePersonSearch search);

    /**
     * 更新删除状态
     *
     * @param id      主键id
     * @param deleted 是否被删除
     * @return 受影响rows
     */
    int updateDeleteStatusById(@Param("id") Integer id, @Param("deleted") Boolean deleted);
}
