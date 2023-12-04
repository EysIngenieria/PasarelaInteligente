/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DesarrolloJC
 */
@Entity
@Table(name = "OP_RegistroTemporalManatee")
public class OP_RegistroTemporalManatee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private boolean estadoEnvio;
    @Column(length = 10000)
    private String trama;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraOcurrencia;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnvio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isestadoEnvio() {
        return estadoEnvio;
    }

    public void setestadoEnvio(boolean estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public Date getfechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public void setfechaHoraOcurrencia(Date fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }

    public Date getfechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setfechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }
}
