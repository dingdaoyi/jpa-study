package com.yanbing.manage.managecontrol.log;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.service.ApiListing;

import java.lang.reflect.Method;


/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-12 17:18
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
@Aspect
@Order
@Slf4j
@Component
@AllArgsConstructor
public class SysLogAspect {


    @Around("@annotation(sysLog)")
    public Object aroundSysLog(ProceedingJoinPoint point, SysLog sysLog) throws Throwable {
        String key = sysLog.value();
        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        String valu = rduceAnnotationValue(key, args, method);
        System.out.println("s = " + valu);
        return point.proceed();
    }

    /**
     * 检查替换注解中的{#aa.bb} spel表达式
     * @param key
     * @param args
     * @param method
     * @return
     */
    private String rduceAnnotationValue(String key, Object[] args, Method method) {
        String[] parms = getParmsByDetails(key);

        for (int i = 0; i < parms.length; i++) {
            String parm = parms[i];
            if (parm.startsWith("{#")) {
                parms[i]= parseKey(parm, method,args);
            }
        }
        return String.join("", parms);
    }

    /**
     * 获取缓存的key
     * key 定义在注解上，支持SPEL表达式
     * @return
     */
    private String parseKey(String key, Method method, Object [] args){

        if(StringUtils.isEmpty(key)) return null;

        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        assert paraNameArr != null ;
        for(int i = 0; i<paraNameArr.length; i++){
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context,String.class);
    }
    /**
     * 获取RESTful风格参数数组
     * @param details
     */
    private   String[] getParmsByDetails(String details){
        String[] parms  = details.replace("{","-{").replace("}","}-").split("-");
        return parms;
    }


}
