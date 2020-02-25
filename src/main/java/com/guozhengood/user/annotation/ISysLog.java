package com.guozhengood.user.annotation;

import java.lang.annotation.*;

/**
 * @desc: 功能描述：系统日志注解
 * @param:
 * @return:
 * @auther: swf
 * @date: 2019/12/13 14:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ISysLog {

	String value() default "";
}
