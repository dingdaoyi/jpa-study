package com.yanbing.manage.managecontrol.service.impl;

import com.yanbing.manage.managecontrol.bo.UserBo;
import com.yanbing.manage.managecontrol.entity.Dept;
import com.yanbing.manage.managecontrol.entity.Role;
import com.yanbing.manage.managecontrol.entity.User;
import com.yanbing.manage.managecontrol.repository.DeptRepository;
import com.yanbing.manage.managecontrol.repository.RoleRepository;
import com.yanbing.manage.managecontrol.repository.UserRepository;
import com.yanbing.manage.managecontrol.service.IUserService;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.utils.$;
import net.dreamlu.mica.core.utils.BeanUtil;
import org.springframework.format.number.money.MonetaryAmountFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-05 18:07
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private DeptRepository deptRepository;

    @Resource
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public R<User> selectUser(Long id) {
        Optional<User> user = userRepository.findById(id);
      return   user.map(R::success).orElseGet(() -> R.fail("查询失败"));

    }

    @Override
    public List<User> selectUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public Mono<Long> save(User user,Long deptId) {
        Optional<Dept> optional = deptRepository.findById(deptId);
        user.setDept(optional.orElse(null));
        return Mono.just( userRepository.save(user).getId());
    }

    @Override
    public Mono<Long> updateUser(UserBo user) {
        Optional<User> optional = userRepository.findById(user.getId());
        User u = optional.orElseThrow(()->new RuntimeException("没有查到对应的用户"));
        BeanUtil.copyNonNull(user, u);
        Long deptId = user.getDeptId();
        if (deptId!=null) {
            Optional<Dept> dept = deptRepository.findById(deptId);
                u.setDept(dept.orElse(u.getDept()));
        }
        List<Long> list = user.getRoleIds();
        if ($.isNotEmpty(list)) {
            List<Role> roles = roleRepository.findAllById(list);
            u.setRoleList(roles);
        }
        u = userRepository.saveAndFlush(u);

        return Mono.just(u.getId());
    }

    @Transactional
    @Override
    public Mono<R> deleteUser(Long id) {
        userRepository.deleteById(id);
        return Mono.just(R.success());
    }
}
