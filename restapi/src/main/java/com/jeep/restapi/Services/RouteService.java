package com.jeep.restapi.Services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeep.restapi.Models.RouteModel;
import com.jeep.restapi.Repositories.RouteRepository;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    RouteService(RouteRepository routeRepository){
        this.routeRepository = routeRepository;
    }

    public List<RouteModel> GetAll()
    {
        return routeRepository.findAll();
    }

    public RouteModel GetById(int id){
        return routeRepository.findById(id).orElseThrow();
    }

    public RouteModel Create(RouteModel route){
        if(route != null)
        {   
            List<RouteModel> routes = GetAll();

            Optional<RouteModel> existingRoute = routes.stream()
                                                .filter(existing -> existing.getName().equals(route.getName()))
                                                .findFirst();
            
            if(!existingRoute.isPresent())
            { 
                return routeRepository.save(route);
            }
        }

        return null;
    }

    public RouteModel Update(RouteModel route){
        if(route!=null)
        {
            try {
                RouteModel targetRoute = GetById(route.getId());
                targetRoute.setName(route.getName());
                routeRepository.save(targetRoute);
                return targetRoute;
            } catch (NoSuchElementException e) {
                return Create(route);
            }
        }
        return null;
    }

    public void Delete(int id){
        var targetRoute = GetById(id);

        if(targetRoute != null)
            routeRepository.deleteById(id);
    }
}
