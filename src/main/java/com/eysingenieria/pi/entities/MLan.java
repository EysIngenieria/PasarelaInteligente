/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.entities;

import java.util.Date;

/**
 *
 * @author Josue
 */
public class MLan {
    
    private long ultimaconexion;
    
    private String ip;

    public MLan() {
    }

    public long getUltimaconexion() {
        return ultimaconexion;
    }

    public void setUltimaconexion(long ultimaconexion) {
        this.ultimaconexion = ultimaconexion;
    }

    

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public boolean conectado(){
        long date = 0;
        if (ultimaconexion != 0) {
            
            date = System.currentTimeMillis();
            int restaFechas = (int) ((date - ultimaconexion));
            int minutos = restaFechas / (60 * 1000);
            //System.out.println("Tiempo transcurrido: " + minutos);
            boolean re = minutos <= 1;
            return re;
        } else {
            return false;
        }
    }
    
    
    
}
