package com.yanbing.manage.managecontrol.log;

import java.lang.annotation.*;

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
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  SysLog {
    String value() default "";
}
