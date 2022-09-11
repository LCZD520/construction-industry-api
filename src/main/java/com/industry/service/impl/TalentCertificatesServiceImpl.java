package com.industry.service.impl;

import com.industry.bean.entity.TalentCertificatesDO;
import com.industry.bean.entity.TalentCertificatesWhereaboutsFlowDO;
import com.industry.bean.request.UpdateTalentCertificatesWhereaboutsRequest;
import com.industry.mapper.TalentCertificatesMapper;
import com.industry.mapper.TalentCertificatesWhereaboutsFlowMapper;
import com.industry.service.TalentCertificatesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 人才证件表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
@Service
public class TalentCertificatesServiceImpl extends ServiceImpl<TalentCertificatesMapper, TalentCertificatesDO> implements TalentCertificatesService {

    private TalentCertificatesMapper mapper;

    private TalentCertificatesWhereaboutsFlowMapper flowMapper;

    @Autowired
    public void setMapper(TalentCertificatesMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setWhereaboutsFlowMapper(TalentCertificatesWhereaboutsFlowMapper flowMapper) {
        this.flowMapper = flowMapper;
    }

    @Override
    public int insertBatch(List<TalentCertificatesDO> list) {
        int rows = mapper.insertBatch(list);
        List<TalentCertificatesWhereaboutsFlowDO> list1 = new ArrayList<>();
        for (TalentCertificatesDO talentCertificates : list) {
            TalentCertificatesWhereaboutsFlowDO whereaboutsFlow
                    = new TalentCertificatesWhereaboutsFlowDO();
            whereaboutsFlow.setRemark(talentCertificates.getRemark());
            whereaboutsFlow.setCertificatesWhereabouts(talentCertificates.getCertificatesWhereabouts());
            whereaboutsFlow.setTalentCertificatesId(talentCertificates.getTalentCertificatesId());
            list1.add(whereaboutsFlow);
        }
        int i = flowMapper.insertBatch(list1);
        return rows + i;
    }

    @Override
    public List<TalentCertificatesDO> listCertificates(Integer id) {
        return mapper.listCertificates(id);
    }

    @Override
    public int deleteById(Integer id) {
        TalentCertificatesDO talentCertificatesDO = mapper.selectById(id);
        final List<TalentCertificatesWhereaboutsFlowDO> list = flowMapper.listCertificatesWhereabouts(id);
        if (list.isEmpty()) {
            return -1;
        } else {
            List<Integer> listIds = new ArrayList<>();
            for (TalentCertificatesWhereaboutsFlowDO whereaboutsFlowDO : list) {
                listIds.add(whereaboutsFlowDO.getId());
            }
            flowMapper.deleteBatchIds(listIds);
        }
        if (talentCertificatesDO == null) {
            return -1;
        }
        return mapper.deleteById(id);
    }

    @Override
    public int updateCertificateBatchById(UpdateTalentCertificatesWhereaboutsRequest whereaboutsRequest) {
        List<Integer> listTalentCertificatesId = whereaboutsRequest.getListTalentCertificatesId();
        List<TalentCertificatesDO> list = mapper.selectBatchIds(whereaboutsRequest.getListTalentCertificatesId());
        List<TalentCertificatesWhereaboutsFlowDO> list1 = new ArrayList<>();
        int certificatesSize = list.size();
        int certificatesIdSize = listTalentCertificatesId.size();

        if (certificatesSize == certificatesIdSize) {
            for (int i = 0; i < certificatesSize; i++) {
                TalentCertificatesWhereaboutsFlowDO whereaboutsFlowDO = new TalentCertificatesWhereaboutsFlowDO();
                whereaboutsFlowDO.setTalentCertificatesId(
                        listTalentCertificatesId.get(i));
                whereaboutsFlowDO.setCertificatesWhereabouts(
                        whereaboutsRequest.getCertificatesWhereabouts());
                whereaboutsFlowDO.setRemark(
                        whereaboutsRequest.getRemark());
                list1.add(whereaboutsFlowDO);
            }
            flowMapper.insertBatch(list1);
            return mapper.updateCertificateBatchById(whereaboutsRequest);
        }
        return 0;
    }

}
