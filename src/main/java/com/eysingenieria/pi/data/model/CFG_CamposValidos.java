/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.data.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author DesarrolloJC
 */
@Entity
@Table(name = "CFG_CamposValidos") 
public class CFG_CamposValidos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private String nombre;
    private String tipoDato;
    private String tipoCampoValido;
    private boolean manejaNivel;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCampo() {
        return tipoDato;
    }

    public void setTipoCampo(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getTipoCampoValido() {
        return tipoCampoValido;
    }

    public void setTipoCampoValido(String tipoCampoValido) {
        this.tipoCampoValido = tipoCampoValido;
    }

    public boolean isManejaNivel() {
        return manejaNivel;
    }

    public void setManejaNivel(boolean manejaNivel) {
        this.manejaNivel = manejaNivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
