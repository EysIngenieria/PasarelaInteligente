/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.data;

import java.util.Queue;
import org.json.JSONArray;


/**
 *
 * @author pi
 */
public class Vagon {
    private String nombreVagon;
    
    private String nombreCDEGVagon;
    
    private long ultimaConexionVagon;
    
    private long ultimaConexionCanalAVagon;
    
    private long ultimaConexionCanalBVagon;
    
    private boolean desconectadoVagon;

    private String puertasVagon;
    
    private Queue comandosVagon;
    
    private int ultimo_registroVagon;
    
    public void getDiferenciaFechasVagon(){
        long date = 0;
        if (ultimaConexionVagon != 0) {
            
            date = System.currentTimeMillis();
            int restaFechas = (int) ((date - ultimaConexionVagon));
            int minutos = restaFechas / (60 * 1000);
            desconectadoVagon = minutos > 0;
        } else {
            desconectadoVagon = true;
        }  
    }

    public long getUltimaConexionCanalA() {
        return ultimaConexionCanalAVagon;
    }

    public void setUltimaConexionCanalA(long ultimaConexionCanalA) {
        this.ultimaConexionCanalAVagon = ultimaConexionCanalA;
    }

    public long getUltimaConexionCanalB() {
        return ultimaConexionCanalBVagon;
    }

    public void setUltimaConexionCanalB(long ultimaConexionCanalB) {
        this.ultimaConexionCanalBVagon = ultimaConexionCanalB;
    }
    
    public String getNombre() {
        return nombreVagon;
    }

    public void setNombre(String nombre) {
        this.nombreVagon = nombre;
    }

    public long getUltimaConexion() {
        return ultimaConexionVagon;
    }

    public void setUltimaConexion(long ultimaConexion) {
        this.ultimaConexionVagon = ultimaConexion;
    }

    public boolean isDesconectado() {
        return desconectadoVagon;
    }

    public void setDesconectado(boolean desconectado) {
        this.desconectadoVagon = desconectado;
    }

    public String getPuertas() {
        return puertasVagon;
    }

    public void setPuertas(String puertas) {
        this.puertasVagon = puertas;
    }

    public String getNombreCDEG() {
        return nombreCDEGVagon;
    }

    public void setNombreCDEG(String nombreCDEG) {
        this.nombreCDEGVagon = nombreCDEG;
    }

    public Queue getComandos() {
        return comandosVagon;
    }

    public void setComandos(Queue comandos) {
        this.comandosVagon = comandos;
    }

    public boolean desconexionA() {
        long date = 0;
        if (ultimaConexionCanalAVagon != 0) {
            
            date = System.currentTimeMillis();
            double restaFechas = ((date - ultimaConexionCanalAVagon));
            double segundos = restaFechas / (1000);
            //System.out.println("Tiempo transcurrido: " + minutos);
            return segundos > 14;
        } else {
            return true;
        }
    }
    public boolean desconexionB() {
        long date = 0;
        if (ultimaConexionCanalBVagon != 0) {
            
            date = System.currentTimeMillis();
            double restaFechas = ((date - ultimaConexionCanalBVagon));
            double segundos = restaFechas / (1000);
            //System.out.println("Tiempo transcurrido: " + minutos);
            return segundos > 14;
        } else {
            return true;
        }
    }

    public int getUltimo_registro() {
        return ultimo_registroVagon;
    }

    public void setUltimo_registro(int ultimo_registro) {
        this.ultimo_registroVagon = ultimo_registro;
    }
    
    public boolean nuevoMensaje(int nuevo) {
        boolean aux = false;
        if (ultimo_registroVagon == 0) {
            ultimo_registroVagon = nuevo;
            aux = true;
        } else if (ultimo_registroVagon!=nuevo) {
            ultimo_registroVagon = nuevo;
            aux = true;
        }
        return aux;
    }

    
}
