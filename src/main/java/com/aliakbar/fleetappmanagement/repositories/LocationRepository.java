package com.aliakbar.fleetappmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aliakbar.fleetappmanagement.models.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

}
