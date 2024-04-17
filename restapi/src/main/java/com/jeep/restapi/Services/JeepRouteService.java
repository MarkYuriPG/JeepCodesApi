package com.jeep.restapi.Services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeep.restapi.Models.JeepRouteId;
import com.jeep.restapi.Models.JeepRouteModel;
import com.jeep.restapi.Repositories.JeepRouteRepository;

@Service
public class JeepRouteService {
    @Autowired
    private final JeepRouteRepository jeepRouteRepository;
    private final JeepService jeepService;
    private final RouteService routeService;

    public JeepRouteService(JeepRouteRepository jeepRouteRepository, JeepService jeepService,
    RouteService routeService) {
        this.jeepRouteRepository = jeepRouteRepository;
        this.jeepService = jeepService;
        this.routeService = routeService;
    }

    public List<JeepRouteModel> GetAll()
    {
        return jeepRouteRepository.findAll();
    }

    public JeepRouteModel GetById(JeepRouteId id){
        return jeepRouteRepository.findById(id).orElseThrow();
    }

    public JeepRouteModel Create(JeepRouteModel jeepRoute)
    {
        if(jeepRoute != null)
        {   
            int jeepId = jeepRoute.getId().getJeepId();
            int routeId = jeepRoute.getId().getRouteId();

            if(jeepService.GetById(jeepId)!=null && routeService.GetById(routeId)!=null)
            {
                List<JeepRouteModel> jeepRoutes = GetAll();

                Optional<JeepRouteModel> existingJeepRoute = jeepRoutes.stream()
                                                .filter(existing -> existing.getId().equals(jeepRoute.getId()))
                                                .findFirst();
            
                if(!existingJeepRoute.isPresent())
                { 
                    return jeepRouteRepository.save(jeepRoute);
                }
            }
        }
        return null;
    }

    public JeepRouteModel Update(JeepRouteModel jeepRoute){
        if(jeepRoute!=null)
        {
            try {
                JeepRouteModel targetJeepRoute = GetById(jeepRoute.getId());
                targetJeepRoute.setId(jeepRoute.getId());
                jeepRouteRepository.save(targetJeepRoute);
                return targetJeepRoute;
            } catch (NoSuchElementException e) {
                return Create(jeepRoute);
            }
        }
        return null;
    }

    public void Delete(JeepRouteId id){
        jeepRouteRepository.deleteById(id);
    }
}
