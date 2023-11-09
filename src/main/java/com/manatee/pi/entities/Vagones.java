/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.entities;

import java.util.List;

/**
 *
 * @author DesarrolloJC
 */
public class Vagones {
    private int id;
    private List<String> puertas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getPuertas() {
        return puertas;
    }

    public void setPuertas(List<String> puertas) {
        this.puertas = puertas;
    }

}
