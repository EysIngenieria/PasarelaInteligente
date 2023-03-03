/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.entities;

import java.util.List;

/**
 *
 * @author DesarrolloJC
 */
public class Alarma {

    private int id;
    private String codigoAlarma;
    private String nombreAlarma;
    private String variableAlarma;
    private List<NivelAlarma> nivelAlarma;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoAlarma() {
        return codigoAlarma;
    }

    public void setCodigoAlarma(String codigoAlarma) {
        this.codigoAlarma = codigoAlarma;
    }

    public String getNombreAlarma() {
        return nombreAlarma;
    }

    public void setNombreAlarma(String nombreAlarma) {
        this.nombreAlarma = nombreAlarma;
    }

    public String getVariableAlarma() {
        return variableAlarma;
    }

    public void setVariableAlarma(String variableAlarma) {
        this.variableAlarma = variableAlarma;
    }

    public List<NivelAlarma> getNivelAlarma() {
        return nivelAlarma;
    }

    public void setNivelAlarma(List<NivelAlarma> nivelAlarma) {
        this.nivelAlarma = nivelAlarma;
    }

}
