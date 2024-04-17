package com.jeep.restapi.Models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="jeep_routes")
public class JeepRouteModel {
    @EmbeddedId
    private JeepRouteId id;

    public JeepRouteModel(JeepRouteId id){
        this.id = id;
    }
}
