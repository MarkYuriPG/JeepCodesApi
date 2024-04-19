package com.jeep.restapi.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class JeepRouteId implements Serializable {
    @Column(name = "jeep_id")
    private int jeepId;

    @Column(name = "route_id")
    private int routeId;

    public JeepRouteId(int jeepId, int routeId){
        this.jeepId = jeepId;
        this.routeId = routeId;
    }
}
