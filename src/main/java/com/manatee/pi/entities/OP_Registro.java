/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author DesarrolloJC
 */
@Entity
@Table(name = "OP_Registro")
public class OP_Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(length = 5000)
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
