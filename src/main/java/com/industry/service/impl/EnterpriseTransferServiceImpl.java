package com.industry.service.impl;

import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EnterpriseTransferDO;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.request.EnterpriseTransferRequest;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.EnterpriseTransferMapper;
import com.industry.service.EnterpriseTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 企业转账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-09-09
 */
@Slf4j
@Service
public class EnterpriseTransferServiceImpl extends ServiceImpl<EnterpriseTransferMapper, EnterpriseTransferDO> implements EnterpriseTransferService {

    private ResultEntity result;

    private EnterpriseTransferMapper mapper;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setMapper(EnterpriseTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<OrderSelectedTalentDO> getWaitAssignTalents(Integer id) {
        return mapper.getWaitAssignTalents(id);
    }

    @Override
    public ResultEntity insert(EnterpriseTransferRequest request) {
        EnterpriseTransferDO transfer = new EnterpriseTransferDO();
        transfer.setAccountName(request.getAccountName());
        transfer.setEnterpriseId(request.getEnterpriseId());
        transfer.setTransferAmount(request.getTransferAmount());
        transfer.setBankName(request.getBankName());
        transfer.setBankCardNo(request.getBankCardNo());
        transfer.setOpenBank(request.getOpenBank());
        transfer.setFundsPurpose(request.getFundsPurpose());
        transfer.setRemark(request.getRemark());
        List<Integer> orderSelectedTalentIds = request.getOrderSelectedTalentIds();

        synchronized (this) {
            List<Integer> waitAssignTalentIds = mapper.getWaitAssignTalentIds(request.getEnterpriseId());
            List<Integer> lis = new ArrayList<>();
            for (Integer id : orderSelectedTalentIds) {
                if (!waitAssignTalentIds.contains(id)) {
                    lis.add(id);
                }
            }
            if (lis.size() > 0) {
                List<String> list = mapper.selectFullNameByIds(lis);
                return result.failure(ResultCodeEnum.FAIL_APPLY_EXISTED_TALENT, list);
            } else {
                mapper.insert(transfer);
                mapper.updateInfoByIds(transfer.getId(), orderSelectedTalentIds);
                return result.success(ResultCodeEnum.SUCCESS_APPLY_TRANSFER);
            }
        }
    }

    @Override
    public List<EnterpriseTransferDO> getListTransferRecords(Integer id) {
        return mapper.getListTransferRecords(id);
    }

    @Override
    public EnterpriseTransferDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }
}
