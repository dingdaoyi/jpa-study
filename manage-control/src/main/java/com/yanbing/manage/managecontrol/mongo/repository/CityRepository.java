package com.yanbing.manage.managecontrol.mongo.repository;

import com.yanbing.manage.managecontrol.mongo.entity.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-12 14:43
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
public interface CityRepository extends ReactiveMongoRepository<City,Long> {

   Flux<City> findByCityName(String cityName);

   Flux<City> getAllByCityNameLike(String cityName);
}
