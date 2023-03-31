/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.entities;

import java.util.Queue;
import org.json.JSONArray;



/**
 *
 * @author pi
 */
public class Vagon {
    private String nombre;
    
    private String nombreCDEG;
    
    private long ultimaConexion;
    
    private long ultimaConexionCanalA;
    
    private long ultimaConexionCanalB;
    
    private boolean desconectado;

    private String puertas;
    
    private Queue comandos;
    
    private int ultimo_registro_canalA;
    private int ultimo_registro_canalB;
    
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

    public long getUltimaConexionCanalA() {
        return ultimaConexionCanalA;
    }

    public void setUltimaConexionCanalA(long ultimaConexionCanalA) {
        this.ultimaConexionCanalA = ultimaConexionCanalA;
    }

    public long getUltimaConexionCanalB() {
        return ultimaConexionCanalB;
    }

    public void setUltimaConexionCanalB(long ultimaConexionCanalB) {
        this.ultimaConexionCanalB = ultimaConexionCanalB;
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

    public Queue getComandos() {
        return comandos;
    }

    public void setComandos(Queue comandos) {
        this.comandos = comandos;
    }

    public boolean desconexionA() {
        long date = 0;
        if (ultimaConexionCanalA != 0) {
            
            date = System.currentTimeMillis();
            double restaFechas = ((date - ultimaConexionCanalA));
            double segundos = restaFechas / (1000);
            //System.out.println("Tiempo transcurrido: " + minutos);
            return segundos > 14;
        } else {
            return true;
        }
    }
    public boolean desconexionB() {
        long date = 0;
        if (ultimaConexionCanalB != 0) {
            
            date = System.currentTimeMillis();
            double restaFechas = ((date - ultimaConexionCanalB));
            double segundos = restaFechas / (1000);
            //System.out.println("Tiempo transcurrido: " + minutos);
            return segundos > 14;
        } else {
            return true;
        }
    }

    public int getUltimo_registro_canalA() {
        return ultimo_registro_canalA;
    }

    public void setUltimo_registro_canalA(int ultimo_registro_canalA) {
        this.ultimo_registro_canalA = ultimo_registro_canalA;
    }

    public int getUltimo_registro_canalB() {
        return ultimo_registro_canalB;
    }

    public void setUltimo_registro_canalB(int ultimo_registro_canalB) {
        this.ultimo_registro_canalB = ultimo_registro_canalB;
    }

    
    
    public boolean nuevoMensajeCanalA(int nuevo) {
        boolean aux = false;
        if (ultimo_registro_canalA == 0) {
            ultimo_registro_canalA = nuevo;
            aux = true;
        } else if (ultimo_registro_canalA!=nuevo) {
            ultimo_registro_canalA = nuevo;
            aux = true;
        }
        return aux;
    }
    public boolean nuevoMensajeCanalB(int nuevo) {
        boolean aux = false;
        if (ultimo_registro_canalB == 0) {
            ultimo_registro_canalB = nuevo;
            aux = true;
        } else if (ultimo_registro_canalB!=nuevo) {
            ultimo_registro_canalB = nuevo;
            aux = true;
        }
        return aux;
    }

    
}
