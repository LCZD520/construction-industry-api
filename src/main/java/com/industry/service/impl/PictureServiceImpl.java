package com.industry.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.PictureDO;
import com.industry.mapper.PictureMapper;
import com.industry.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-18
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, PictureDO> implements PictureService {


    private PictureMapper mapper;

    @Autowired
    public void setMapper(PictureMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insertBatch(List<PictureDO> listPictures) {
        return mapper.insertPictureBatch(listPictures);
    }

    @Override
    public List<PictureDO> listPictures(String namespace, String type, Integer resourceId) {
        return mapper.listPicturesByResourceId(namespace, type, resourceId);
    }

    @Override
    public int deleteById(Integer id) {
        final PictureDO picture = mapper.selectById(id);
        if (picture == null) {
            return -1;
        }
        return mapper.deleteById(id);
    }

    @Override
    public List<PictureDO> listPicturesByIds(List<Integer> listIds) {
        return mapper.listPicturesByIds(listIds);
    }

    @Override
    public int deleteBatchByIds(List<Integer> listIds) {
        synchronized (this) {
            final List<PictureDO> list = mapper.listPicturesByIds(listIds);
            if (list.size() == listIds.size()) {
                return mapper.deleteBatchByIds(listIds);
            }
            return -1;
        }
    }

}
