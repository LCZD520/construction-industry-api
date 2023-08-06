package com.industry.service.impl;

import com.industry.bean.entity.ClassThreePersonnelBankAccountDO;
import com.industry.bean.entity.ClassThreePersonnelBankAccountDO;
import com.industry.bean.request.ClassThreePersonnelBankAccountRequest;
import com.industry.mapper.ClassThreePersonnelBankAccountMapper;
import com.industry.mapper.ClassThreePersonnelBankAccountMapper;
import com.industry.service.ClassThreePersonnelBankAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 三类人员转账银行账户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Service
public class ClassThreePersonnelBankAccountServiceImpl extends ServiceImpl<ClassThreePersonnelBankAccountMapper, ClassThreePersonnelBankAccountDO> implements ClassThreePersonnelBankAccountService {
    
    private ClassThreePersonnelBankAccountMapper mapper;

    @Autowired
    public void setMapper(ClassThreePersonnelBankAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(ClassThreePersonnelBankAccountDO classThreePersonnelBankAccountDO) {
        synchronized (this) {
            return mapper.insert(classThreePersonnelBankAccountDO);
        }
    }

    @Override
    public List<ClassThreePersonnelBankAccountRequest> getListClassThreePersonnelBankAccountById(Integer id) {
        return mapper.getListClassThreePersonnelBankAccountById(id);
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final ClassThreePersonnelBankAccountDO classThreePersonnelBankAccountDO = mapper.selectById(id);
            if (classThreePersonnelBankAccountDO == null) {
                return -1;
            }
            return mapper.deleteById(id);
        }
    }

    @Override
    public ClassThreePersonnelBankAccountRequest getClassThreePersonnelBankAccountById(Integer id) {
        return mapper.getClassThreePersonnelBankAccountById(id);
    }

    @Override
    public int updateClassThreePersonnelBankAccountById(ClassThreePersonnelBankAccountDO classThreePersonnelBankAccountDO) {
        synchronized (this) {
            final ClassThreePersonnelBankAccountDO talentBankAccount = mapper.selectById(classThreePersonnelBankAccountDO.getId());
            if (talentBankAccount == null) {
                return -1;
            }
            return mapper.updateById(classThreePersonnelBankAccountDO);
        }
    }
}

