package com.yanbing.manage.managecontrol.repository;

import com.yanbing.manage.managecontrol.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-05 18:08
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
