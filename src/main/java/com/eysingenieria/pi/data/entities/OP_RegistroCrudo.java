/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.data.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author DesarrolloJC
 */
@Entity
@Table(name = "OP_RegistroCrudo")
public class OP_RegistroCrudo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String origen;
    private String idPuerta;
    private String codigoPuerta;
    private String idVagon;
    @Column(length = 5000)
    private String trama;
    private String canal;
    private String funcion;
    private boolean estadoProcesado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraOcurrencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getIdPuerta() {
        return idPuerta;
    }

    public void setIdPuerta(String idPuerta) {
        this.idPuerta = idPuerta;
    }

    public String getCodigoPuerta() {
        return codigoPuerta;
    }

    public void setCodigoPuerta(String codigoPuerta) {
        this.codigoPuerta = codigoPuerta;
    }

    public String getIdVagon() {
        return idVagon;
    }

    public void setIdVagon(String idVagon) {
        this.idVagon = idVagon;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public boolean isEstado() {
        return estadoProcesado;
    }

    public void setEstado(boolean estadoProcesado) {
        this.estadoProcesado = estadoProcesado;
    }

    public Date getFechaOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public void setFechaOcurrencia(Date fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
    
    

}
