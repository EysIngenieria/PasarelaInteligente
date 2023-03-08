/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.data.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author DesarrolloJC
 */
@Entity
@Table(name = "CFG_CamposEvento")
public class CFG_CamposEvento implements Serializable {

    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "IdConfiguracion", referencedColumnName = "Id")
    private CFG_Configuracion configuracion;
    @ManyToOne
    @JoinColumn(name = "IdEvento", referencedColumnName = "Id")
    private CFG_Evento evento;
    @ManyToOne
    @JoinColumn(name = "IdCampoValido", referencedColumnName = "Id")
    private CFG_CamposValidos camposValidos;

    public CFG_CamposEvento() {
        super();
    }

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

    public CFG_Evento getEvento() {
        return evento;
    }

    public void setEvento(CFG_Evento evento) {
        this.evento = evento;
    }

    public CFG_CamposValidos getCamposValidos() {
        return camposValidos;
    }

    public void setCamposValidos(CFG_CamposValidos camposValidos) {
        this.camposValidos = camposValidos;
    }

}
