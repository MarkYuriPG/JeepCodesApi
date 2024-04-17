package com.jeep.restapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeep.restapi.Models.JeepRouteId;
import com.jeep.restapi.Models.JeepRouteModel;

public interface JeepRouteRepository extends JpaRepository<JeepRouteModel, JeepRouteId>{
}
