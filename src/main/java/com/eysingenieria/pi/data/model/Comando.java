/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.data.model;

import java.util.Date;

/**
 *
 * @author DesarrolloJC
 */
public class Comando {

    private String versionTrama;
    private String idRegistro;
    private String idOperador;
    private String idEstacion;
    private String idVagon;
    private String idPuerta;
    private String codigoPuerta;
    private String fechaHoraLecturaDato;
    private String fechaHoraEnvioDato;
    private Integer tipoTrama;
    private Integer codigoMensaje;
    private String mensaje;
    private Integer codigoMensajeReproducir;
    private String fechaHoraInicioReproduccion;
    private String fechaHoraFinalReproduccion;
    private Integer cantidadReproduccion;
    private Integer activadoDesactivado;
    private String fechaHoraInicioActivaciondesactivacion;
    private String fechaHoraFinalActivaciondesactivacion;

    public String getVersionTrama() {
        return versionTrama;
    }

    public void setVersionTrama(String versionTrama) {
        this.versionTrama = versionTrama;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(String idOperador) {
        this.idOperador = idOperador;
    }

    public String getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(String idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getIdVagon() {
        return idVagon;
    }

    public void setIdVagon(String idVagon) {
        this.idVagon = idVagon;
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

    public String getFechaHoraLecturaDato() {
        return fechaHoraLecturaDato;
    }

    public void setFechaHoraLecturaDato(String fechaHoraLecturaDato) {
        this.fechaHoraLecturaDato = fechaHoraLecturaDato;
    }

    public String getFechaHoraEnvioDato() {
        return fechaHoraEnvioDato;
    }

    public void setFechaHoraEnvioDato(String fechaHoraEnvioDato) {
        this.fechaHoraEnvioDato = fechaHoraEnvioDato;
    }

    public Integer getTipoTrama() {
        return tipoTrama;
    }

    public void setTipoTrama(Integer tipoTrama) {
        this.tipoTrama = tipoTrama;
    }

    public Integer getCodigoMensaje() {
        return codigoMensaje;
    }

    public void setCodigoMensaje(Integer codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigoMensajeReproducir() {
        return codigoMensajeReproducir;
    }

    public void setCodigoMensajeReproducir(int codigoMensajeReproducir) {
        this.codigoMensajeReproducir = codigoMensajeReproducir;
    }

    public String getFechaHoraInicioReproduccion() {
        return fechaHoraInicioReproduccion;
    }

    public void setFechaHoraInicioReproduccion(String fechaHoraInicioReproduccion) {
        this.fechaHoraInicioReproduccion = fechaHoraInicioReproduccion;
    }

    public String getFechaHoraFinalReproduccion() {
        return fechaHoraFinalReproduccion;
    }

    public void setFechaHoraFinalReproduccion(String fechaHoraFinalReproduccion) {
        this.fechaHoraFinalReproduccion = fechaHoraFinalReproduccion;
    }

    public Integer getCantidadReproduccion() {
        return cantidadReproduccion;
    }

    public void setCantidadReproduccion(Integer cantidadReproduccion) {
        this.cantidadReproduccion = cantidadReproduccion;
    }

    public Integer getActivadoDesactivado() {
        return activadoDesactivado;
    }

    public void setActivadoDesactivado(Integer activadoDesactivado) {
        this.activadoDesactivado = activadoDesactivado;
    }

    public String getFechaHoraInicioActivaciondesactivacion() {
        return fechaHoraInicioActivaciondesactivacion;
    }

    public void setFechaHoraInicioActivaciondesactivacion(String fechaHoraInicioActivaciondesactivacion) {
        this.fechaHoraInicioActivaciondesactivacion = fechaHoraInicioActivaciondesactivacion;
    }

    public String getFechaHoraFinalActivaciondesactivacion() {
        return fechaHoraFinalActivaciondesactivacion;
    }

    public void setFechaHoraFinalActivaciondesactivacion(String fechaHoraFinalActivaciondesactivacion) {
        this.fechaHoraFinalActivaciondesactivacion = fechaHoraFinalActivaciondesactivacion;
    }

}
