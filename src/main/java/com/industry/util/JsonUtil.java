package com.industry.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

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
    public void writeJson(HttpServletResponse resp, ResultEntity data) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(new ObjectMapper().writer().writeValueAsString(data));
        writer.flush();
        writer.close();
    }
}
