package com.yanbing.manage.managecontrol.mongo.controller;

import com.yanbing.manage.managecontrol.log.SysLog;
import com.yanbing.manage.managecontrol.mongo.entity.City;
import com.yanbing.manage.managecontrol.mongo.repository.CityRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-12 14:41
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
@RestController
@RequestMapping("/mongoDb")
public class MongController {
    @Resource
    private CityRepository repository;
    @PostMapping
    @SysLog("我要写的内容是:{#city.getId()}和{@demoBean.say()}")
    public Mono<City> saveCity(@RequestBody  City city) {
      return repository.save(city);
    }

    @GetMapping("/list")
    public Flux<City> getlist() {
        return repository.findAll();
    }

    @GetMapping("query")
    public Flux<City> getlist(@RequestParam("cityName")String cityName) {
        return repository.getAllByCityNameLike(cityName);
    }

}
