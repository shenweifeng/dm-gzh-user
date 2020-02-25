/**
 * 
 */
package com.guozhengood.user.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @date 2014-3-8
 * 
 * @class com.dianmic.dmbxg.domain.BaseModel
 * 
 * @author swf
 * 
 * @Description bean基类
 * 
 */
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * 新增时非空校验
     * 
     * @return
     */
    public abstract boolean check();

}
