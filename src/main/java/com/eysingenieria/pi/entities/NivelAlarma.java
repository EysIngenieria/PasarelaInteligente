/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.entities;

/**
 *
 * @author DesarrolloJC
 */
public class NivelAlarma {

    private String codigoNivelAlarma;
    private int valorNivelAlarmaInferior;
    private int valorNivelAlarmaSuperior;

    public String getCodigoNivelAlarma() {
        return codigoNivelAlarma;
    }

    public void setCodigoNivelAlarma(String codigoNivelAlarma) {
        this.codigoNivelAlarma = codigoNivelAlarma;
    }

    public int getValorNivelAlarmaInferior() {
        return valorNivelAlarmaInferior;
    }

    public void setValorNivelAlarmaInferior(int valorNivelAlarmaInferior) {
        this.valorNivelAlarmaInferior = valorNivelAlarmaInferior;
    }

    public int getValorNivelAlarmaSuperior() {
        return valorNivelAlarmaSuperior;
    }

    public void setValorNivelAlarmaSuperior(int valorNivelAlarmaSuperior) {
        this.valorNivelAlarmaSuperior = valorNivelAlarmaSuperior;
    }

}
