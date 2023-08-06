package com.industry.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.industry.bean.common.ResultEntity;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author lc
 * @date 2022/2/22
 */
@Component
public class JsonUtil implements Serializable {

    private static final long serialVersionUID = -2238351509061431858L;

    public void writeJson(HttpServletResponse resp, ResultEntity data) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(new ObjectMapper().writer().writeValueAsString(data).getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
