/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "CFG_NivelAlarma")
public class CFG_NivelAlarma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private int secuencia;
    private int nivelInferior;
    private int nivelSuperior;
    private String valor;
    @ManyToOne
    @JoinColumn(name = "IdAlarma", referencedColumnName = "Id")
    private CFG_Alarma alarma;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public int getNivelInferior() {
        return nivelInferior;
    }

    public void setNivelInferior(int nivelInferior) {
        this.nivelInferior = nivelInferior;
    }

    public int getNivelSuperior() {
        return nivelSuperior;
    }

    public void setNivelSuperior(int nivelSuperior) {
        this.nivelSuperior = nivelSuperior;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public CFG_Alarma getAlarma() {
        return alarma;
    }

    public void setAlarma(CFG_Alarma alarma) {
        this.alarma = alarma;
    }

}
