package com.jeep.restapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeep.restapi.Models.JeepModel;

@Repository
public interface JeepRepository extends JpaRepository<JeepModel, Integer>{
}