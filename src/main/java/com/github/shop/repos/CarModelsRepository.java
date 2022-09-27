package com.github.shop.repos;

import com.github.shop.modelsBD.CarModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelsRepository extends CrudRepository<CarModel, Long> {
}
