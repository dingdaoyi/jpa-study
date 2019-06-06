package com.yanbing.manage.managecontrol.service;

import com.yanbing.manage.managecontrol.bo.UserBo;
import com.yanbing.manage.managecontrol.entity.User;
import net.dreamlu.mica.core.result.R;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-05 18:03
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
public interface IUserService {

    R<User> selectUser(Long id);

    List<User> selectUsers();

    Mono<Long> save(User user,Long deptId);

    Mono<Long> updateUser(UserBo user);

    Mono<R> deleteUser(Long id);
}
