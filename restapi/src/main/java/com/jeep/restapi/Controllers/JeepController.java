package com.jeep.restapi.Controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeep.restapi.Models.JeepModel;
import com.jeep.restapi.Services.JeepService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/jeep")
public class JeepController {
    private JeepService jeepService;

    public JeepController(JeepService jeepService)
    {
        this.jeepService = jeepService;
    }

    @GetMapping("")
    public ResponseEntity<List<JeepModel>> GetAll(){
        List<JeepModel> jeeps = jeepService.GetAll();
        if(!jeeps.isEmpty())
            return ResponseEntity.ok(jeeps);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JeepModel> GetById(@PathVariable int id){
        try {
            JeepModel targetJeep = jeepService.GetById(id);
            return ResponseEntity.ok(targetJeep);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<JeepModel> Create(@RequestBody JeepModel jeep) {
        if(jeep!=null && jeep.getId()==0)
        {
            JeepModel newJeep = jeepService.Create(jeep);
            if(newJeep!=null)
                return ResponseEntity.ok(newJeep);
        }    
        return ResponseEntity.badRequest().build();
    }
    
    @PutMapping("")
    public ResponseEntity<JeepModel> Update(@RequestBody JeepModel jeep) {
        if(jeep!=null)
        {
            try {
                JeepModel updatedJeep = jeepService.Update(jeep);
                return ResponseEntity.ok(updatedJeep);
            } catch (NoSuchElementException e) {
                JeepModel newJeep = jeepService.Create(jeep);
                return ResponseEntity.ok(newJeep);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable int id){
        try {
            jeepService.Delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
