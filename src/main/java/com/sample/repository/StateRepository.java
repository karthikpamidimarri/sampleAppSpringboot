package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.entity.State;

public interface StateRepository extends JpaRepository<State, Integer>{

}
