package com.yanbing.manage.managecontrol.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-12 17:23
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
//@Aspect
//@Component
public class LogAspect {
    @Around("@annotation(sysLog))")
    public void exec(SysLog sysLog , ProceedingJoinPoint pointcut) throws Throwable {
        String[] argsName =  ((MethodSignature)pointcut.getSignature()).getParameterNames();
        for (String s : argsName) {
            System.out.println("s = " + s);
        }
        pointcut.proceed();
    }

}
