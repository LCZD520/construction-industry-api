package com.industry.service.impl;

import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.common.SelectOptions;
import com.industry.bean.entity.PermissionDO;
import com.industry.bean.entity.RoleDO;
import com.industry.bean.entity.UserRoleDO;
import com.industry.bean.request.ModifyPasswordRequest;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.UserMapper;
import com.industry.service.UserRoleService;
import com.industry.service.UserService;
import com.industry.util.LocalCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private ResultEntity result;

    public static final String DEFAULT_PASSWORD
            = "$2a$10$8flAbamtFJ9ycFxyArpRQuX8S0DPWCRcMErncTbldt.njwWbFXKLO";

    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Resource
    private TransactionDefinition transactionDefinition;

    private UserMapper userMapper;

    private UserRoleService userRoleService;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    public AuthUser queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    @Override
    public List<SelectOptions> listUsers() {
        return userMapper.listUsers();
    }

    @Override
    public ListPages<AuthUser> listAllUsers(ListPages<AuthUser> page) {
        page.setList(userMapper.listAllUsers(page));
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        page.setPageSize(page.getPageSize());
        page.setTotal(userMapper.getCount());
        return page;
    }

    @Override
    public ListPages<AuthUser> listUsersByMechanismId(ListPages<AuthUser> page, Integer mechanismId, String fullName, Boolean onJob) {
        final List<Integer> mechanismIds = userMapper.selectMechanismIds(mechanismId);
        List<AuthUser> authUsers = userMapper.listUsersByMechanismIds(page, mechanismIds, fullName, onJob);
        page.setList(authUsers);
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        page.setPageSize(page.getPageSize());
        page.setTotal(userMapper.getCountByMechanismId(mechanismId, fullName, onJob));
        return page;
    }

    @Override
    public AuthUser getDetailById(Integer id) {
        return userMapper.getDetailById(id);
    }

    @Override
    public int insert(AuthUser user) {
        synchronized (this) {
            TransactionStatus transactionStatus
                    = dataSourceTransactionManager
                    .getTransaction(transactionDefinition);
            try {
                AuthUser user1 = userMapper.queryUserByUsername(user.getUsername());
                if (user1 == null) {
                    String encode = new BCryptPasswordEncoder().encode(user.getPassword());
                    log.info("encode:{}", encode);
                    user.setPassword(encode);
                    log.info("user:{}", user);
                    List<Integer> listRoleIds = user.getListRoleIds();
                    int rows = userMapper.insertUser(user);
                    List<UserRoleDO> list = new ArrayList<>();
                    Integer userId = user.getUserId();
                    listRoleIds.forEach(item -> {
                        UserRoleDO userRole = new UserRoleDO();
                        userRole.setUserId(userId);
                        userRole.setRoleId(item);
                        list.add(userRole);
                    });
                    userRoleService.saveBatch(list);
                    dataSourceTransactionManager.commit(transactionStatus);
                    return rows;
                }
                return -1;
            } catch (Exception e) {
                dataSourceTransactionManager.rollback(transactionStatus);
            }
        }
        return 0;
    }

    @Override
    public int update(AuthUser user) {
        synchronized (this) {
            TransactionStatus transactionStatus
                    = dataSourceTransactionManager
                    .getTransaction(transactionDefinition);
            List<Integer> newListRoleIds = user.getListRoleIds();
            try {
                final Integer userId = user.getUserId();
                List<Integer> oldListRoleIds = userMapper.getListRoleIds(userId);
                List<Integer> addRoleIds =
                        newListRoleIds.stream().filter(item -> !oldListRoleIds.contains(item)).collect(Collectors.toList());
                List<Integer> deleteRoleIds =
                        oldListRoleIds.stream().filter(item -> !newListRoleIds.contains(item)).collect(Collectors.toList());
                List<UserRoleDO> list = new ArrayList<>();
                addRoleIds.forEach(item -> {
                    UserRoleDO userRole = new UserRoleDO();
                    userRole.setUserId(userId);
                    userRole.setRoleId(item);
                    list.add(userRole);
                });
                AuthUser user1 = userMapper.queryUserByUsername(user.getUsername());
                if (user1 == null) {
                    return -1;
                }
                final int i = userMapper.updateUserById(user);
                if (list.size() > 0) {
                    userRoleService.saveBatch(list);
                }
                if (deleteRoleIds.size() > 0) {
                    userMapper.removeByRoleIds(deleteRoleIds, userId);
                }
                dataSourceTransactionManager.commit(transactionStatus);
                return i;
            } catch (Exception e) {
                dataSourceTransactionManager.rollback(transactionStatus);
            }
            return 0;
        }
    }

    @Override
    public int resetPassword(Integer id) {
        synchronized (this) {
            AuthUser user = userMapper.selectByUserId(id);
            if (user == null) {
                return -1;
            }
            return userMapper.resetPassword(id, DEFAULT_PASSWORD);
        }
    }

    @Override
    public int deleteByUserId(Integer id) {
        return userMapper.deleteByUserId(id);
    }

    @Override
    public List<PermissionDO> getAuthorityByUsername(String username) {
        return userMapper.getAuthorityByUsername(username);
    }

    @Override
    public List<String> getAuthorityURIByUsername(String username) {
        return userMapper.getAuthorityURIByUsername(username);
    }

    @Override
    public List<RoleDO> listRolesByUsername(String username) {
        return userMapper.listRolesByUsername(username);
    }

    @Override
    public ResultEntity modifyPassword(ModifyPasswordRequest request) {
        final AuthUser user = userMapper.selectByUserId(request.getId());
        if (user == null) {
            return result.failure(ResultCodeEnum.USER_ACCOUNT_NO_FOUND_ERROR);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String oldPassword = encoder.encode(request.getOldPassword());
        String newPassword = encoder.encode(request.getNewPassword());
//        boolean matches1 = encoder.matches(user.getPassword(), oldPassword);
//        if (!matches1) {
//            return result.failure(ResultCodeEnum.USER_MODIFIED_OLD_PASSWORD_ERROR);
//        }
//        boolean matches = encoder.matches(oldPassword, newPassword);
//        if (matches) {
//            return result.failure(ResultCodeEnum.USER_MODIFIED_PASSWORD_SAME_ERROR);
//        }
        request.setNewPassword(newPassword);
        final int rows = userMapper.modifyPassword(request);
        if (rows > 0) {
            LocalCacheUtil.deleteUser(user.getUsername());
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED_PASSWORD);
        }
        return result.failure(ResultCodeEnum.USER_MODIFIED_PASSWORD_ERROR);
    }
}
