package com.jeep.restapi.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "jeeps")
public class JeepModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name="code")
    private String code;

    @Column(name="routes")
    private int routes;

    public JeepModel(String code, int routes)
    {
        this.code = code;
        this.routes = routes;
    }
}
