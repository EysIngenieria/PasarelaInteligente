/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author DesarrolloJC
 */
@Entity
@Table(name = "CFG_CamposAlarma")
public class CFG_CamposAlarma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "IdConfiguracion", referencedColumnName = "Id")
    private CFG_Configuracion configuracion;
    @ManyToOne
    @JoinColumn(name = "IdAlarma", referencedColumnName = "Id")
    private CFG_Alarma alarma;
    @ManyToOne
    @JoinColumn(name = "IdCampoValido", referencedColumnName = "Id")
    private CFG_CamposValidos camposValidos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CFG_Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(CFG_Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    public CFG_Alarma getAlarma() {
        return alarma;
    }

    public void setAlarma(CFG_Alarma alarma) {
        this.alarma = alarma;
    }

    public CFG_CamposValidos getCamposValidos() {
        return camposValidos;
    }

    public void setCamposValidos(CFG_CamposValidos camposValidos) {
        this.camposValidos = camposValidos;
    }

}
