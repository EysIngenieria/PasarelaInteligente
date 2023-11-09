/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.entities;

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
@Table(name = "OP_RegistroTemporal")
public class OP_RegistroTemporal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private boolean estadoEnvio;
    @Basic
    private boolean estadoEnvioManatee;
    @Column(length = 5000)
    private long IDManatee;
    @Column(length = 5000)
    private String trama;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraOcurrencia;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnvio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrimerIntento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getFechaPrimerIntento() {
        return fechaPrimerIntento;
    }

    public void setFechaPrimerIntento(Date fechaPrimerIntento) {
        this.fechaPrimerIntento = fechaPrimerIntento;
    }

    public boolean isEstadoEnvioManatee() {
        return estadoEnvioManatee;
    }

    public void setEstadoEnvioManatee(boolean estadoEnvioManatee) {
        this.estadoEnvioManatee = estadoEnvioManatee;
    }

    public long getIDManatee() {
        return IDManatee;
    }

    public void setIDManatee(long IDManatee) {
        this.IDManatee = IDManatee;
    }
    
}
