package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
