package com.guozhengood.user.dao;

import com.guozhengood.user.domain.SysLogCommon;
import org.springframework.stereotype.Component;

@Component("sysLogCommonMapper")
public interface SysLogCommonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLogCommon record);

    int insertSelective(SysLogCommon record);

    SysLogCommon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLogCommon record);

    int updateByPrimaryKey(SysLogCommon record);
}