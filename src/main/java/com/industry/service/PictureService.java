package com.industry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.PictureDO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-18
 */
public interface PictureService extends IService<PictureDO> {

    @Transactional(rollbackFor = Exception.class)
    int insertBatch(List<PictureDO> listPictures);


    List<PictureDO> listPictures(String namespace, String type, Integer resourceId);

    /**
     * 删除图片
     *
     * @param id 图片id
     * @return 删除rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);

    /**
     * 批量获取图片
     *
     * @param listIds List<Integer>
     * @return List<PictureDO>
     */
    List<PictureDO> listPicturesByIds(List<Integer> listIds);

    /**
     * 批量删除图片
     *
     * @param listIds 图片id集合
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteBatchByIds(List<Integer> listIds);
}
