package com.jeep.restapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeep.restapi.Models.RouteModel;

@Repository
public interface RouteRepository extends JpaRepository<RouteModel, Integer>{
}
