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
@Table(name = "Puertas")
public class Puerta implements Serializable{
    @Id
    private Integer id;
    @Basic
    private String canal;
    private String vagon;
    private String idPuerta;
    private String descripcion;

    private String estado;

    private Boolean estadoErrorCritico;

    private String nivelAlarma1;

    private String nivelAlarma2;

    private Integer ciclosApertura;

    private Long horasServicio;

    private Boolean estadoAperturaCierre;

    private Double velocidadMotor;

    private Double fuerzaMotor;

    private Integer tipoEnergizacion;

    private Integer entradasApertura;

    private Integer tiempoApertura;

    private Integer porcentajeApertura;

    private Integer usoBotonManual;

    
    private String fechaHoraInicioActivacionDesactivacion;
    
    private String fechaHoraFinalActivacionDesactivacion;
    
    private Integer activadoDesactivado;
    
    
    private String fechaHoraInicioReproduccion;
    
    
    private String fechaHoraFinalReproduccion;
    
    private Integer cantidadReproduccion;
    
    private Integer codigoMensajeReproduccion;
    
    private Boolean estadoCalibracion;
    
    private Integer ContadorEncoder;
    
    private Integer estadoPoder;
    
    private Integer voltajeBateria;
    
    private Double PorcentajeCargaBaterias;
    
    private Integer voltajeVI;
    
    private String tiempoOperacionMotor;
    
    private Integer modoOperacion;
    
    private Integer velocidadCierre;
    
    private Integer velocidadApertura;
    
    private Integer tiempoPausa;
    
    private Integer estadoBluetooth;
    
    private Integer estadoBotonManual;
    
    private Boolean procesarReproduccion;
    
    private Integer tiempoActivado;
    
    private Integer cantidadReproduccionT;
    
    private Integer tiempoReproduccion;
    
    private Integer tiempoReproduccionT;
    
    private long ultimaConexion; 

    public Integer getTiempoReproduccionT() {
        return tiempoReproduccionT;
    }

    public void setTiempoReproduccionT(Integer tiempoReproduccionT) {
        this.tiempoReproduccionT = tiempoReproduccionT;
    }
    
    

    public Integer getTiempoReproduccion() {
        return tiempoReproduccion;
    }

    public void setTiempoReproduccion(Integer tiempoReproduccion) {
        this.tiempoReproduccion = tiempoReproduccion;
    }
    
    
    public Integer getCantidadReproduccionT() {
        return cantidadReproduccionT;
    }

    public void setCantidadReproduccionT(Integer cantidadReproduccionT) {
        this.cantidadReproduccionT = cantidadReproduccionT;
    }
    
    

    public Boolean getProcesarReproduccion() {
        return procesarReproduccion;
    }

    public void setProcesarReproduccion(Boolean procesarReproduccion) {
        this.procesarReproduccion = procesarReproduccion;
    }

    public Integer getTiempoActivado() {
        return tiempoActivado;
    }

    public void setTiempoActivado(Integer tiempoActivado) {
        this.tiempoActivado = tiempoActivado;
    }
    
    

    public Integer getEstadoBluetooth() {
        return estadoBluetooth;
    }

    public void setEstadoBluetooth(Integer estadoBluetooth) {
        this.estadoBluetooth = estadoBluetooth;
    }

    public Integer getTiempoPausa() {
        return tiempoPausa;
    }

    public void setTiempoPausa(Integer tiempoPausa) {
        this.tiempoPausa = tiempoPausa;
    }

    public Integer getVelocidadCierre() {
        return velocidadCierre;
    }

    public void setVelocidadCierre(Integer velocidadCierre) {
        this.velocidadCierre = velocidadCierre;
    }

    public Integer getVelocidadApertura() {
        return velocidadApertura;
    }

    public void setVelocidadApertura(Integer velocidadApertura) {
        this.velocidadApertura = velocidadApertura;
    }
    
    

    public Integer getModoOperacion() {
        return modoOperacion;
    }

    public void setModoOperacion(Integer modoOperacion) {
        this.modoOperacion = modoOperacion;
    }


    public String getTiempoOperacionMotor() {
        return tiempoOperacionMotor;
    }

    public void setTiempoOperacionMotor(String tiempoOperacionMotor) {
        this.tiempoOperacionMotor = tiempoOperacionMotor;
    }

    public Integer getVoltajeVI() {
        return voltajeVI;
    }

    public void setVoltajeVI(Integer voltajeVI) {
        this.voltajeVI = voltajeVI;
    }

    public Double getPorcentajeCargaBaterias() {
        return PorcentajeCargaBaterias;
    }

    public void setPorcentajeCargaBaterias(Double PorcentajeCargaBaterias) {
        this.PorcentajeCargaBaterias = PorcentajeCargaBaterias;
    }

    public Integer getVoltajeBateria() {
        return voltajeBateria;
    }

    public void setVoltajeBateria(Integer voltajeBataria) {
        this.voltajeBateria = voltajeBataria;
    }

    public Integer getEstadoPoder() {
        return estadoPoder;
    }

    public void setEstadoPoder(Integer estadoPoder) {
        this.estadoPoder = estadoPoder;
    }

    public Integer getUsoBotonManual() {
        return usoBotonManual;
    }

    public void setUsoBotonManual(Integer usoBotonManual) {
        this.usoBotonManual = usoBotonManual;
    }

    public Integer getPorcentajeApertura() {
        return porcentajeApertura;
    }

    public void setPorcentajeApertura(Integer porcentajeApertura) {
        this.porcentajeApertura = porcentajeApertura;
    }

    public Integer getTiempoApertura() {
        return tiempoApertura;
    }

    public void setTiempoApertura(Integer tiempoApertura) {
        this.tiempoApertura = tiempoApertura;
    }

    public Integer getEntradasApertura() {
        return entradasApertura;
    }

    public void setEntradasApertura(Integer entradasApertura) {
        this.entradasApertura = entradasApertura;
    }
    
    
    

    public Integer getContadorEncoder() {
        return ContadorEncoder;
    }

    public void setContadorEncoder(Integer ContadorEncoder) {
        this.ContadorEncoder = ContadorEncoder;
    }

    public Boolean isEstadoCalibracion() {
        return estadoCalibracion;
    }

    public void setEstadoCalibracion(Boolean estadoCalibracion) {
        this.estadoCalibracion = estadoCalibracion;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getVagon() {
        return vagon;
    }

    public void setVagon(String vagon) {
        this.vagon = vagon;
    }

    public String getIdPuerta() {
        return idPuerta;
    }

    public void setIdPuerta(String idPuerta) {
        this.idPuerta = idPuerta;
    }

    public Integer getEstadoBotonManual() {
        return estadoBotonManual;
    }

    public void setEstadoBotonManual(Integer estadoBotonManual) {
        this.estadoBotonManual = estadoBotonManual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstadoErrorCritico() {
        return estadoErrorCritico;
    }

    public void setEstadoErrorCritico(Boolean estadoEstadoErrorCritico) {
        this.estadoErrorCritico = estadoEstadoErrorCritico;
    }

    public String getNivelAlarma1() {
        return nivelAlarma1;
    }

    public void setNivelAlarma1(String nivelAlarma1) {
        this.nivelAlarma1 = nivelAlarma1;
    }

    public String getNivelAlarma2() {
        return nivelAlarma2;
    }

    public void setNivelAlarma2(String nivelAlarma2) {
        this.nivelAlarma2 = nivelAlarma2;
    }

    public Integer getCiclosApertura() {
        return ciclosApertura;
    }

    public void setCiclosApertura(Integer ciclosApertura) {
        this.ciclosApertura = ciclosApertura;
    }

    public Long getHorasServicio() {
        return horasServicio;
    }

    public void setHorasServicio(Long horasServicio) {
        this.horasServicio = horasServicio;
    }

    public Boolean isEstadoAperturaCierre() {
        return estadoAperturaCierre;
    }

    public void setEstadoAperturaCierre(Boolean estadoAperturaCierre) {
        this.estadoAperturaCierre = estadoAperturaCierre;
    }

    public Double getVelocidadMotor() {
        return velocidadMotor;
    }

    public void setVelocidadMotor(Double velocidadMotor) {
        this.velocidadMotor = velocidadMotor;
    }

    public Double getFuerzaMotor() {
        return fuerzaMotor;
    }

    public void setFuerzaMotor(Double fuerzaMotor) {
        this.fuerzaMotor = fuerzaMotor;
    }

    public Integer getTipoEnergizacion() {
        return tipoEnergizacion;
    }

    public void setTipoEnergizacion(Integer tipoEnergizacion) {
        this.tipoEnergizacion = tipoEnergizacion;
    }

    public String getFechaHoraInicioActivacionDesactivacion() {
        return fechaHoraInicioActivacionDesactivacion;
    }

    public void setFechaHoraInicioActivacionDesactivacion(String fechaHoraInicioActivacionDesactivacion) {
        this.fechaHoraInicioActivacionDesactivacion = fechaHoraInicioActivacionDesactivacion;
    }

    public String getFechaHoraFinalActivacionDesactivacion() {
        return fechaHoraFinalActivacionDesactivacion;
    }

    public void setFechaHoraFinalActivacionDesactivacion(String fechaHoraFinalActivacionDesactivacion) {
        this.fechaHoraFinalActivacionDesactivacion = fechaHoraFinalActivacionDesactivacion;
    }

    

    public Integer getActivadoDesactivado() {
        return activadoDesactivado;
    }

    public void setActivadoDesactivado(Integer activadoDesactivado) {
        this.activadoDesactivado = activadoDesactivado;
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

    public Integer getCodigoMensajeReproduccion() {
        return codigoMensajeReproduccion;
    }

    public void setCodigoMensajeReproduccion(Integer codigoMensajeReproduccion) {
        this.codigoMensajeReproduccion = codigoMensajeReproduccion;
    }

    public long getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(long ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    
    public boolean conexion() {
        long date = 0;
        if (ultimaConexion != 0) {
            
            date = System.currentTimeMillis();
            double restaFechas = ((date - ultimaConexion));
            
            //System.out.println("Tiempo transcurrido: " + minutos);
            return restaFechas < 120000;
        } else {
            return false;
        }
    }
    
     
    
    
    
    
}
