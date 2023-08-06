package com.industry.auth;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industry.bean.entity.MechanismDO;
import com.industry.bean.entity.RoleDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author lc
 * @description: 账户基本信息, 用于认证和授权
 * @date 2021/6/18 20:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class AuthUser implements UserDetails {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "机构主键id")
    @TableField("mechanism_id")
    private Integer mechanismId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "更新人id")
    @TableField(value = "regenerator_id", fill = FieldFill.INSERT_UPDATE)
    private Integer regeneratorId;

    @ApiModelProperty(value = "是否可用")
    @TableField("is_enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "姓名")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "联系方式")
    @TableField("telephone_number")
    private String telephoneNumber;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty(value = "是否在职")
    @TableField("is_on_job")
    private Boolean onJob;

    @ApiModelProperty(value = "是否首次登录")
    @TableField("is_first_login")
    private Boolean firstLogin;

    @ApiModelProperty(value = "所在机构")
    @TableField(exist = false)
    private MechanismDO mechanism;

    @ApiModelProperty(value = "角色id列表")
    @TableField(exist = false)
    private List<Integer> listRoleIds;

    @ApiModelProperty(value = "角色列表")
    @TableField(exist = false)
    private List<RoleDO> listRoles;

    /**
     * 拥有的权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 获取权限
     *
     * @return 所有权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
