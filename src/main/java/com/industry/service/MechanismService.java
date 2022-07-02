package com.industry.service;

import com.industry.entity.Mechanism;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 机构表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
public interface MechanismService extends IService<Mechanism> {

    int insert(Mechanism mechanism);

    List<Mechanism> queryListMechanisms();
}
