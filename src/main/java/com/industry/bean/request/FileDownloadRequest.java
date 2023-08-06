package com.industry.bean.request;

import lombok.Data;

import java.util.List;

/**
 * @author lc
 * @date 2022/12/23
 */
@Data
public class FileDownloadRequest {
    private List<Integer> listIds;
}
