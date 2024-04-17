package com.jeep.restapi.Controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeep.restapi.Models.RouteModel;
import com.jeep.restapi.Services.RouteService;

@RestController
@RequestMapping("api/route")
public class RouteController {
    private RouteService routeService;

    RouteController(RouteService routeService){
        this.routeService = routeService;
    }
    
    @GetMapping("")
    public ResponseEntity<List<RouteModel>> GetAll(){
        List<RouteModel> routes = routeService.GetAll();
        if(!routes.isEmpty())
            return ResponseEntity.ok(routes);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteModel> GetById(@PathVariable int id){
        try {
            RouteModel targetRoute = routeService.GetById(id);
            return ResponseEntity.ok(targetRoute);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<RouteModel> Create(@RequestBody RouteModel route) {
        if(route!=null && route.getId()==0)
        {
            RouteModel newRoute = routeService.Create(route);
            if(newRoute!=null)
                return ResponseEntity.ok(newRoute);
        }    
        return ResponseEntity.badRequest().build();
    }
    
    @PutMapping("")
    public ResponseEntity<RouteModel> Update(@RequestBody RouteModel route) {
        if(route!=null)
        {
            try {
                RouteModel updatedRoute = routeService.Update(route);
                return ResponseEntity.ok(updatedRoute);
            } catch (NoSuchElementException e) {
                RouteModel newRoute = routeService.Create(route);
                return ResponseEntity.ok(newRoute);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable int id){
        try {
            routeService.Delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
