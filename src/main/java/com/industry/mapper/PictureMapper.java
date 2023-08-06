package com.industry.mapper;

import com.industry.bean.entity.PictureDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.PictureTempDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 图片表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-18
 */
@Mapper
public interface PictureMapper extends BaseMapper<PictureDO> {

    int insertPictureBatch(@Param("list") List<PictureDO> listPictures);

    int insertPictureBatch2(@Param("list") List<PictureTempDO> listPictures);

    List<PictureDO> listPicturesByResourceId(@Param("namespace") String namespace
            , @Param("type") String type
            , @Param("resourceId") Integer resourceId);

    /**
     * 获取图片集合
     *
     * @param listIds 图片id集合
     * @return List<PictureDO>
     */
    List<PictureDO> listPicturesByIds(@Param("listIds") List<Integer> listIds);

    /**
     * 批量删除图片
     *
     * @param listIds 图片id集合
     * @return 受影响rows
     */
    int deleteBatchByIds(@Param("listIds") List<Integer> listIds);

    /**
     * 批量删除图片
     *
     * @param listDeleteRecordIds 图片resource_id集合
     * @param namespace           命名空间
     * @param type                类型
     * @return 受影响rows
     */
    int deleteBatchByConditions(@Param("listDeleteRecordIds") List<Integer> listDeleteRecordIds
            , @Param("namespace") String namespace
            , @Param("type") String type);

    /**
     * 获取图片id集合
     *
     * @param id 职称评审id
     * @return List<Integer>
     */
    List<Integer> getListIdsByTitleEvaluationId(@Param("id") Integer id);

    /**
     * 获取图片id集合
     *
     * @param id 三类人员id
     * @return List<Integer>
     */
    List<Integer> getListIdsByClassThreePersonId(@Param("id") Integer id);

    /**
     * 获取图片id集合
     *
     * @param id 学历提升id
     * @return List<Integer>
     */
    List<Integer> getListIdsByEducationPromotionId(@Param("id") Integer id);
}
