package com.guozhengood.user.controller;

import com.guozhengood.user.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: swf
 * @Description: 基础API接口
 * @Date:Create：in 2019/12/9:17:46
 * @Modified By：
 */
@Controller("baseApiController")
@Scope("prototype")
public class BaseApiController {

    Logger log = LoggerFactory.getLogger(BaseApiController.class);


    /**
     * @param request
     * @param e
     * @return
     * @date 2016-4-15
     * @author swf
     * @Description 全局异常处理
     */
    @ExceptionHandler
    public String exception(HttpServletRequest request, Exception e) {

        log.error(String.format("ip=[%s], sc=[%s], e=[%s].", CommonUtil.getIp(request), request.getServletContext(), e.getMessage()));

        e.printStackTrace();

        // 对异常进行判断做相应的处理
        if (e instanceof NullPointerException) {
            return "403";
        } else if (e instanceof IllegalArgumentException) {
            return "403";
        }

        return "403";
    }

}
