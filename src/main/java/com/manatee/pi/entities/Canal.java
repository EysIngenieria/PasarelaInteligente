/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.entities;

/**
 *
 * @author DesarrolloJR
 */
public class Canal{
    private int canal;
    private long ultimaConexcion;
    private int idRegistro;
    private String conexionPuertas;

    public Canal(int canal, int idRegistro) {
        this.canal = canal;
        this.ultimaConexcion = System.currentTimeMillis();
        this.idRegistro = idRegistro;
    }

    public String getConexionPuertas() {
        return conexionPuertas;
    }

    public void setConexionPuertas(String conexionPuertas) {
        this.conexionPuertas = conexionPuertas;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public long getUltimaConexcion() {
        return ultimaConexcion;
    }

    public void setUltimaConexcion(long ultimaConexcion) {
        this.ultimaConexcion = ultimaConexcion;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }
    
    public boolean actualizar(int newId) {
        boolean ret = false;
        if (this.idRegistro != newId) {
            setIdRegistro(newId);
            setUltimaConexcion(System.currentTimeMillis());
            ret = true;
        }
        return ret;
    }
    
    public void actualizarSinACK() {

        setUltimaConexcion(System.currentTimeMillis());

    }
    public boolean conectado() {
        long date = System.currentTimeMillis();
        long restaFechas = date - ultimaConexcion;
        long minutos = restaFechas / (60 * 1000);

        return !(minutos > 1);
    }

}
