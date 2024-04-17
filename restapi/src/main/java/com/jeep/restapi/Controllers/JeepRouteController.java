package com.jeep.restapi.Controllers;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeep.restapi.Models.JeepRouteId;
import com.jeep.restapi.Models.JeepRouteModel;
import com.jeep.restapi.Services.JeepRouteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/jeepRoute")
public class JeepRouteController {
    private JeepRouteService jeepRouteService;
    
    JeepRouteController(JeepRouteService jeepRouteService)
    {
        this.jeepRouteService = jeepRouteService;
    }

    @GetMapping("")
    public ResponseEntity<List<JeepRouteModel>> GetAll() {
        List<JeepRouteModel> jeepRoutes = jeepRouteService.GetAll();
        if(!jeepRoutes.isEmpty())
            return ResponseEntity.ok(jeepRoutes);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{jeepId}/{routeId}")
    public ResponseEntity<JeepRouteModel> GetById(@PathVariable int jeepId, @PathVariable int routeId) {
        JeepRouteId id = new JeepRouteId(jeepId, routeId);
        JeepRouteModel jeepRoute = jeepRouteService.GetById(id);
        return jeepRoute != null ? ResponseEntity.ok(jeepRoute) : ResponseEntity.notFound().build();
    }
    
    @PostMapping("")
    public ResponseEntity<JeepRouteModel> Create(@RequestBody JeepRouteModel jeepRoute) {
        if(jeepRoute != null)
        {
            try {
                JeepRouteModel newJeepRoute = jeepRouteService.Create(jeepRoute);
                if(newJeepRoute!=null)
                    return ResponseEntity.ok(newJeepRoute);
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<JeepRouteModel> Update(@RequestBody JeepRouteModel jeepRoute) {
        if(jeepRoute != null)
        {
            JeepRouteModel updatedJeepRoute = jeepRouteService.Update(jeepRoute);
            if(updatedJeepRoute!=null)
                return ResponseEntity.ok(updatedJeepRoute);
        }        
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{jeepId}/{routeId}")
    public ResponseEntity<Void> Delete(@PathVariable int jeepId, @PathVariable int routeId)
    {
        JeepRouteId id = new JeepRouteId(jeepId, routeId);

        try {
            jeepRouteService.Delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
