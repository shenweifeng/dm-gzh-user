package com.guozhengood.user.service.impl;

import com.guozhengood.user.dao.SysLogCommonMapper;
import com.guozhengood.user.domain.SysLogCommon;
import com.guozhengood.user.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: swf
 * @Description:
 * @Date:Create：in 2019/12/13:14:45
 * @Modified By：
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogCommonMapper sysLogCommonMapper;

    @Override
    public int save(SysLogCommon obj) {
        return sysLogCommonMapper.insertSelective(obj);
    }
}
