package com.guozhengood.user.aspect;

import com.google.gson.Gson;
import com.guozhengood.user.annotation.ISysLog;
import com.guozhengood.user.domain.SysLogCommon;
import com.guozhengood.user.service.SysLogService;
import com.guozhengood.user.util.CommonUtil;
import com.guozhengood.user.util.HttpContextUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class SysLogAspect {

	Logger log = LoggerFactory.getLogger(SysLogAspect.class);

	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("@annotation(com.guozhengood.user.annotation.ISysLog)")
	public void logPointCut() { 
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

//		log.info("result={}",new Gson().toJson(result));

		if(result != null && result instanceof ResponseEntity){
			// 有返回值的才执行日志操作
			//保存日志
			saveSysLog(point, time, (ResponseEntity)result);
		}

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time, ResponseEntity result) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLogCommon sysLog = new SysLogCommon();
		ISysLog syslog = method.getAnnotation(ISysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		String params = String.format("result=[%s]", new Gson().toJson(result));
		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			if(args != null && args.length>0){
				String param = new Gson().toJson(args[0]);
				params = String.format("%s, params=[%s].", params, param);
			}
		}catch (Exception e){
		}

		sysLog.setParams(params);

		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(CommonUtil.getIp(request));

		//用户名
//		User user = ((User) SecurityUtils.getSubject().getPrincipal());
//		sysLog.setUserName(user.getUserName());
//		sysLog.setUserId(user.getUserId());

		sysLog.setTime(time);
		sysLog.setCreateDate(new Date());
		//保存系统日志
//		sysLogService.save(sysLog);

		log.info(sysLog.toString());
	}
}
