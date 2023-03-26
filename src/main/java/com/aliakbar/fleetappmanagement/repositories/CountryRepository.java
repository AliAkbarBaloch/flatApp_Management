package com.aliakbar.fleetappmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliakbar.fleetappmanagement.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}