package com.industry.service;

import com.industry.bean.entity.PictureDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
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
     * @param id 图片id
     * @return 删除rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);

}
