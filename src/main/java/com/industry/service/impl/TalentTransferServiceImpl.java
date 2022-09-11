package com.industry.service.impl;

import com.industry.bean.entity.TalentTransferDO;
import com.industry.mapper.TalentTransferMapper;
import com.industry.service.TalentTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人才转账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Service
public class TalentTransferServiceImpl extends ServiceImpl<TalentTransferMapper, TalentTransferDO> implements TalentTransferService {

    private TalentTransferMapper mapper;

    @Autowired
    public void setMapper(TalentTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(TalentTransferDO talentTransferDO) {
        return mapper.insert(talentTransferDO);
    }

    @Override
    public List<TalentTransferDO> listTalentTransfers(Integer id) {
        return mapper.listTalentTransfers(id);
    }

    @Override
    public int deleteById(Integer id) {

        return mapper.deleteById(id);
    }

    @Override
    public TalentTransferDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }
}
