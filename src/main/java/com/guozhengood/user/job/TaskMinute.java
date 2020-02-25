package com.guozhengood.user.job;

import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 禁止并发执行
@DisallowConcurrentExecution
public class TaskMinute {

    // @Autowired
    // private CommonService commonService;

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(TaskMinute.class);

    public void run() {
//        LOG.info("[TaskMinute]：开始执行！");

        // TODO DO SOMETHING

//        LOG.info("[TaskMinute]：执行完毕！");
    }

}
