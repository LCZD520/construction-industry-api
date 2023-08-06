package com.industry.bean.entity;

import lombok.Data;

/**
 * @author win10
 */
@Data
public class LoginUserInfo {
    private String username;
    private String token;
    private String ip;
    private String ipCityInfo;
}
