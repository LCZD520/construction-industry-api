package com.industry.mapper;

import com.industry.bean.entity.PictureDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    List<PictureDO> listPicturesByResourceId(@Param("namespace") String namespace
            , @Param("type") String type
            , @Param("resourceId") Integer resourceId);
}
