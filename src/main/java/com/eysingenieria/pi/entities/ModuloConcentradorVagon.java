/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.entities;

import java.util.ArrayList;

/**
 *
 * @author DesarrolloJC
 */
public class ModuloConcentradorVagon {
    
    String idDispositivo;
    long ultimaConexion;
    ArrayList<Canal> canales;
    String conexionPuertas;
    int idRegistroEmergencia;
 
    public ModuloConcentradorVagon( String idDispositivo) {
        
        canales = new ArrayList<Canal>();
        this.idDispositivo = idDispositivo;
        this.ultimaConexion = System.currentTimeMillis();
    }

    public int getIdRegistroEmergencia() {
        return idRegistroEmergencia;
    }

    public void setIdRegistroEmergencia(int idRegistroEmergencia) {
        this.idRegistroEmergencia = idRegistroEmergencia;
    }

    public String getConexionPuertas() {
        return conexionPuertas;
    }

    public void setConexionPuertas(String conexionPuertas) {
        this.conexionPuertas = conexionPuertas;
    }

    

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public long getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(long ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public ArrayList<Canal> getCanales() {
        return canales;
    }
    
    
    
    public boolean actualizar(int newId, int canal) {
        boolean ret = false;
        boolean canalExists = false;
        for (Canal message : canales) {
            if (message.getCanal()==canal) {
                // MAC address already exists, call the actualizar method
                canalExists = true;
                ret = message.actualizar(newId);
                break;
            }
        }

        // If the MAC address doesn't exist, create a new ModuloConcentradorVagon object and add it to the list
        if (!canalExists) {
            Canal newMessage = new Canal(canal, newId);
            canales.add(newMessage);
            ret = true;
        }
        return ret;
    }
    
    public void actualizarSinACK( int canal) {
       
        boolean canalExists = false;
        for (Canal message : canales) {
            if (message.getCanal()==canal) {
                message.actualizarSinACK();
                canalExists = true;
                
                break;
            }
        }

        // If the MAC address doesn't exist, create a new ModuloConcentradorVagon object and add it to the list
        if (!canalExists) {
            Canal newMessage = new Canal(canal, 0);
            canales.add(newMessage);
            
        }
    }
    
    public boolean desconectado() {
        long date = System.currentTimeMillis();
        long restaFechas = date - ultimaConexion;
        long minutos = restaFechas / (60 * 1000);

        return minutos > 1;
    }
    
    public void actualizarConexionPuertas(String conexionPuertas, boolean canal1, boolean canal2){
        boolean canalExists = false;
        for (Canal message : canales) {
            if (canal1 && canal2) {
                actualicarMCV();
                message.setConexionPuertas(conexionPuertas);
                canalExists = true;
            
                break;
            } else if (canal1) {
                if (message.getCanal() == 1) {
                    message.actualizarSinACK();
                    message.setConexionPuertas(conexionPuertas);
                    canalExists = true;
                }
                
            }else{
                if (message.getCanal() == 2) {
                    message.actualizarSinACK();
                    message.setConexionPuertas(conexionPuertas);
                    canalExists = true;
                }
            
            }
        }

        // If the MAC address doesn't exist, create a new ModuloConcentradorVagon object and add it to the list
        if (!canalExists) {
            Canal newMessage = null;
            if (canal1 && canal2) {
                setConexionPuertas(conexionPuertas);
                actualicarMCV();

            } else if (canal1) {
                newMessage = new Canal(1, 0);
                newMessage.actualizarSinACK();
                newMessage.setConexionPuertas(conexionPuertas);
                canales.add(newMessage);

            } else {
                newMessage = new Canal(2, 0);
                newMessage.actualizarSinACK();
                newMessage.setConexionPuertas(conexionPuertas);
                canalExists = true;
                canales.add(newMessage);

            }
            
            
        }
    
    }

    void actualicarMCV() {
        for (Canal canale : canales) {
            canale.actualizarSinACK();
        }
    }

    boolean actualizarBotonEmergencia(int idRegistro) {
        boolean ret = false;
        actualicarMCV();
        if(getIdRegistroEmergencia() != idRegistro){
            ret = true;
            idRegistroEmergencia = idRegistro;
        }
        
        return ret;
    }
}




