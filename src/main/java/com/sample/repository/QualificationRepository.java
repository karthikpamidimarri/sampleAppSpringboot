package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.entity.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Integer>{

}
