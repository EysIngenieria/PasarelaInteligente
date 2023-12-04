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
public class ComandoInterfazVisual {

    private String direccionMac;
    private Integer activationTime;
    private String comando;
    private List<String> puertas;

    public String getDireccionMac() {
        return direccionMac;
    }

    public void setDireccionMac(String direccionMac) {
        this.direccionMac = direccionMac;
    }

    public Integer getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(Integer activationTime) {
        this.activationTime = activationTime;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public List<String> getPuertas() {
        return puertas;
    }

    public void setPuertas(List<String> puertas) {
        this.puertas = puertas;
    }

    

    

}
