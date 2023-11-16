/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.entities;

import java.util.ArrayList;
import java.util.Queue;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 *
 * @author pi
 */
 public class Vagon {
    private String nombre;
    
    private String nombreCDEG;
    
    private long ultimaConexion;
    
    
    private boolean desconectado;

    private String puertas;
    
    private ArrayList<JSONObject> comandos;
    
    
    private ArrayList<ModuloConcentradorVagon> mvcs;
    
    public Vagon(){
        this.mvcs = new ArrayList<>();
    
    }

    public ArrayList<ModuloConcentradorVagon> getMVCS() {
        return mvcs;
    }
    
    
    
    public void diferenciaFechas(){
        long date = 0;
        if (ultimaConexion != 0) {
            
            date = System.currentTimeMillis();
            int restaFechas = (int) ((date - ultimaConexion));
            int minutos = restaFechas / (60 * 1000);
            //System.out.println("Tiempo transcurrido: " + minutos);
            desconectado = minutos > 0;
        } else {
            desconectado = true;
        }  
    }
    
    

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(long ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public boolean isDesconectado() {
        return desconectado;
    }

    public void setDesconectado(boolean desconectado) {
        this.desconectado = desconectado;
    }

    public String getPuertas() {
        return puertas;
    }

    public void setPuertas(String puertas) {
        this.puertas = puertas;
    }

    public String getNombreCDEG() {
        return nombreCDEG;
    }

    public void setNombreCDEG(String nombreCDEG) {
        this.nombreCDEG = nombreCDEG;
    }

    public ArrayList<JSONObject> getComandos() {
        return comandos;
    }

    public void setComandos(ArrayList<JSONObject> comandos) {
        this.comandos = comandos;
    }

  

    

    
    
    
    
    
    public boolean processMessage(String idDispositivo, int id, int canal) {
        boolean ret = false;
        ultimaConexion = System.currentTimeMillis();
        
        // Check if a ModuloConcentradorVagon object with the given MAC address already exists
        boolean idExists = false;
        for (ModuloConcentradorVagon mvc : mvcs) {
            if (mvc.getIdDispositivo().equals(idDispositivo)) {
                // MAC address already exists, call the actualizar method
                idExists = true;
                ret = mvc.actualizar(id,canal);
                break;
            }
        }

        // If the MAC address doesn't exist, create a new ModuloConcentradorVagon object and add it to the list
        if (!idExists) {
            ModuloConcentradorVagon newMessage = new ModuloConcentradorVagon(idDispositivo);
            newMessage.actualizar(id, canal);
            mvcs.add(newMessage);
            ret = true;
        }
        return ret;
    }
    
    public void processMessageSinAck(  int canal) {
        
        ultimaConexion = System.currentTimeMillis();
        
        // Check if a ModuloConcentradorVagon object with the given MAC address already exists
        boolean idExists = false;
        for (ModuloConcentradorVagon message : mvcs) {
            if (message.getIdDispositivo().equals("0")) {
                // MAC address already exists, call the actualizar method
                idExists = true;
                message.actualizarSinACK(canal);
                break;
            }
        }

        // If the MAC address doesn't exist, create a new ModuloConcentradorVagon object and add it to the list
        if (!idExists) {
            ModuloConcentradorVagon newMessage = new ModuloConcentradorVagon("0");
            mvcs.add(newMessage);
            
        }
    }
    
    public void actualizarConexionPuertas(String idDispositivo, String conexionPuertas, boolean canal1, boolean canal2){
        boolean idExists = false;
        for (ModuloConcentradorVagon mvc : mvcs) {
            if (mvc.getIdDispositivo().equals(idDispositivo)) {
                // MAC address already exists, call the actualizar method
                mvc.actualizarConexionPuertas(conexionPuertas, canal1, canal2);
                idExists = true;
                break;
            }
        }

        // If the MAC address doesn't exist, create a new ModuloConcentradorVagon object and add it to the list
        if (!idExists) {
            ModuloConcentradorVagon newMessage = new ModuloConcentradorVagon(idDispositivo);
            newMessage.actualizarConexionPuertas(conexionPuertas, canal1, canal2);
            mvcs.add(newMessage);
            
        }
    
    
    }

    public void actualizaConexionVagon() {
        for (ModuloConcentradorVagon mvc : mvcs) {
            mvc.actualicarMCV();
            
        }
    }

    public boolean processMessageEmergency(String idDispositivo, int idRegistro) {
        boolean ret = false;
        ultimaConexion = System.currentTimeMillis();
        
        // Check if a ModuloConcentradorVagon object with the given MAC address already exists
        boolean idExists = false;
        for (ModuloConcentradorVagon mvc : mvcs) {
            if (mvc.getIdDispositivo().equals(idDispositivo)) {
                // MAC address already exists, call the actualizar method
                idExists = true;
                ret = mvc.actualizarBotonEmergencia(idRegistro);
                break;
            }
        }

        // If the MAC address doesn't exist, create a new ModuloConcentradorVagon object and add it to the list
        if (!idExists) {
            ModuloConcentradorVagon newMessage = new ModuloConcentradorVagon(idDispositivo);
            newMessage.actualizarBotonEmergencia(idRegistro);
            mvcs.add(newMessage);
            ret = true;
        }
        return ret;
    }
    

    
}
