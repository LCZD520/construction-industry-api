package com.industry.mapper;

import com.industry.bean.entity.PermissionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionDO> {

    List<PermissionDO> queryListPermissions();
}
