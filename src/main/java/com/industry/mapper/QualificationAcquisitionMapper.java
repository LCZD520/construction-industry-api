package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.request.QualificationAcquisitionStrippingRequest;
import com.industry.bean.search.QualificationAcquisitionSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * <p>
 * 资质收购表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Mapper
public interface QualificationAcquisitionMapper extends BaseMapper<QualificationAcquisitionDO> {

    /**
     * 获取资质收购记录
     *
     * @param page page
     * @return IPage<QualificationAcquisitionDO>
     */
    IPage<QualificationAcquisitionDO> listQualificationAcquisition(Page<QualificationAcquisitionDO> page);

    /**
     * 获取资质分类和等级
     *
     * @param id 资质收购id
     * @return String
     */
    String getQualificationById(Integer id);

    /**
     * 更新资质分类和等级
     *
     * @param id                  资质收购id
     * @param newCategoryAndGrade 新的资质分类和等级
     * @return 受影响rows
     */
    int updateCategoryAndGradeById(@Param("id") Integer id, @Param("newCategoryAndGrade") String newCategoryAndGrade);

    /**
     * 更新资质分类和等级
     *
     * @param request QualificationAcquisitionDO
     * @return 受影响rows
     */
    int insertQualificationAcquisition(QualificationAcquisitionDO request);

    /**
     * 根据转让意向用户查找资质收购记录
     *
     * @param transferCustomer 转让意向用户
     * @return QualificationAcquisitionDO
     */
    QualificationAcquisitionDO getQualificationAcquisitionByTransferCustomer(@Param("transferCustomer") String transferCustomer);

    /**
     * 根据获取详情资质收购详情
     *
     * @param id 资质收购id
     * @return QualificationAcquisitionDO
     */
    QualificationAcquisitionDO getDetailById(@Param("id") Integer id);

    /**
     * 更新资质收购记录状态
     *
     * @param id     资质收购id
     * @param status 状态
     * @return 受影响rows
     */
    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 条件获取资质收购列表总数
     *
     * @param search QualificationAcquisitionSearch
     * @return Long
     */
    Long getCount(@Param("search") QualificationAcquisitionSearch search);

    /**
     * 条件分页获取资质收购列表
     *
     * @param page   ListPages<QualificationAcquisitionDO> page
     * @param search QualificationAcquisitionSearch
     * @return List<QualificationAcquisitionDO>
     */
    List<QualificationAcquisitionDO> listQualificationAcquisitionsByConditionPages(@Param("page") ListPages<QualificationAcquisitionDO> page,
                                                                                   @Param("search") QualificationAcquisitionSearch search);

    /**
     * 更新删除状态
     *
     * @param id      主键id
     * @param deleted 是否被删除
     * @return 受影响rows
     */
    int updateDeleteStatusById(@Param("id") Integer id, @Param("deleted") Boolean deleted);
}
