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
public class Configuracion {
    private String versionTrama;
    private int tipoTrama;
    private String fechaHoraConfiguracion;
    private String fechaHoraDifusion;
    private String fechaHoraPruebaTrama;
    private String fechaHoraVigencia;
    private List<String> cabecera;
    private List<String> frecuenciaEnvio;
    private List<Evento> evento;
    private List<Alarma> alarma;

    public String getVersionTrama() {
        return versionTrama;
    }

    public void setVersionTrama(String versionTrama) {
        this.versionTrama = versionTrama;
    }

    public int getTipoTrama() {
        return tipoTrama;
    }

    public void setTipoTrama(int tipoTrama) {
        this.tipoTrama = tipoTrama;
    }

    public String getFechaHoraConfiguracion() {
        return fechaHoraConfiguracion;
    }

    public void setFechaHoraConfiguracion(String fechaHoraConfiguracion) {
        this.fechaHoraConfiguracion = fechaHoraConfiguracion;
    }

    public String getFechaHoraDifusion() {
        return fechaHoraDifusion;
    }

    public void setFechaHoraDifusion(String fechaHoraDifusion) {
        this.fechaHoraDifusion = fechaHoraDifusion;
    }

    public String getFechaHoraPruebaTrama() {
        return fechaHoraPruebaTrama;
    }

    public void setFechaHoraPruebaTrama(String fechaHoraPruebaTrama) {
        this.fechaHoraPruebaTrama = fechaHoraPruebaTrama;
    }

    public String getFechaHoraVigencia() {
        return fechaHoraVigencia;
    }

    public void setFechaHoraVigencia(String fechaHoraVigencia) {
        this.fechaHoraVigencia = fechaHoraVigencia;
    }

    public List<String> getCabecera() {
        return cabecera;
    }

    public void setCabecera(List<String> cabecera) {
        this.cabecera = cabecera;
    }

    public List<String> getFrecuenciaEnvio() {
        return frecuenciaEnvio;
    }

    public void setFrecuenciaEnvio(List<String> frecuenciaEnvio) {
        this.frecuenciaEnvio = frecuenciaEnvio;
    }

    public List<Evento> getEvento() {
        return evento;
    }

    public void setEvento(List<Evento> evento) {
        this.evento = evento;
    }

    public List<Alarma> getAlarma() {
        return alarma;
    }

    public void setAlarma(List<Alarma> alarma) {
        this.alarma = alarma;
    }

}
