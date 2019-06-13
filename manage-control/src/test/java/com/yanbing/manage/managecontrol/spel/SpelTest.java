package com.yanbing.manage.managecontrol.spel;

import com.yanbing.manage.managecontrol.mongo.entity.City;
import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-13 10:47
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
public class SpelTest {
    @Test
    public void testEl() {
        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();

        City city = new City();
        city.setId(1L);
        city.setCityName("成都");
        city.setProvinceId(2233L);
        city.setDescription("四川省");
        context.setVariable("city",city);
        context.setVariable("yaya","yaya");
        //Long value = parser.parseExpression("{city.id}").getValue(context,Long.class);
        //Long value1 = parser.parseExpression("#{city.id}").getValue(context,Long.class);
        Long value2 = parser.parseExpression("#city.provinceId").getValue(context,Long.class);
        String value1 = parser.parseExpression("{#yaya}").getValue(context,String.class);
        //System.out.println("value2 = " + value);
        System.out.println("value2 = " + value1);
        System.out.println("value2 = " + value2);
    }
}
