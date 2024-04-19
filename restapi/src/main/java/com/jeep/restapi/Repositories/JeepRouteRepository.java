package com.jeep.restapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeep.restapi.Models.JeepRouteId;
import com.jeep.restapi.Models.JeepRouteModel;

@Repository
public interface JeepRouteRepository extends JpaRepository<JeepRouteModel, JeepRouteId>{
}
