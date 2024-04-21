package com.jeep.restapi.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeep.restapi.Models.JeepModel;
import com.jeep.restapi.Repositories.JeepRepository;

@Service
public class JeepService {
    @Autowired
    private JeepRepository jeepRepository;

    JeepService(JeepRepository jeepRepository)
    {
        this.jeepRepository = jeepRepository;
    }

    public List<JeepModel> GetAll(){
        return jeepRepository.findAll();
    }

    public JeepModel GetById(int id)
    {
        return jeepRepository.findById(id).orElseThrow();
    }

    public JeepModel Create(JeepModel jeep){
        
        if(jeep != null && isValidJeepCode(jeep.getCode()))
        {   
            List<JeepModel> jeeps = GetAll();

            Optional<JeepModel> existingJeep = jeeps.stream()
                                                .filter(existing -> existing.getCode().equals(jeep.getCode()))
                                                .findFirst();
            
            if(!existingJeep.isPresent())
            { 
                return jeepRepository.save(jeep);
            }
        }

        return null;
    }

    public JeepModel Update(JeepModel jeep)
    {
        if(jeep != null && isValidJeepCode(jeep.getCode()))
        {
            try {
                JeepModel targetJeep = GetById(jeep.getId());

                targetJeep.setCode(jeep.getCode());
                jeepRepository.save(targetJeep);
                return targetJeep;
            } catch (NoSuchElementException e) {
                return Create(jeep);
            }
        }

        return null;
    }

    public void Delete(int id)
    {
        var targetJeep = GetById(id);

        if(targetJeep!=null)
            jeepRepository.deleteById(id);
    }

    private boolean isValidJeepCode(String code) {
        if (code != null && code.length() == 3) {
            if (Character.isDigit(code.charAt(0)) && Character.isDigit(code.charAt(1))) {
                return true;
            }
        }
        return false;
    }
}
