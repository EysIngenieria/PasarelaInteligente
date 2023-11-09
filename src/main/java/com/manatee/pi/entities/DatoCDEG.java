/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.entities;

import java.util.Date;

/**
 *
 * @author DesarrolloJC
 */
public class DatoCDEG {

    private String versionTrama;
    private Long idRegistro;
    private String idOperador;
    private String idEstacion;
    private String idVagon;
    private String idPuerta;
    private String codigoPuerta;
    private Date fechaHoraLecturaDato;
    private Date fechaHoraEnvioDato;
    private String fechaHoraInicioActivaciondesactivacion;
    private String fechaHoraFinalActivaciondesactivacion;
    private Integer tipoTrama;
    private boolean tramaRetransmitida;
    private String codigoEvento;
    private String codigoAlarma;
    
    private Integer ciclosApertura;
    private Long horasServicio;
    private Boolean estadoAperturaCierrePuertas;
    private Boolean estadoErrorCritico;
    private Integer tipoEnergizacion;
    private Double porcentajeCargaBaterias;
    private Double velocidaMotor;
    private Double fuerzaMotor;
    private Integer numeroEventoBusEstacion;
    private String idVehiculo;
    private String placaVehiculo;
    private String tipologiaVehiculo;
    private String numeroParada;
    private String nombreEstacion;
    private String nombreVagon;
    private Integer modoOperacion;
    private Integer tiempoApertura;
    private String tipoTramaBusEstacion;
    private Integer entradasApertura;
    private Integer porcentajeApertura;
    private Integer contadorEncoder;
    private Boolean estadoCalibracion;
    private Integer voltajeBateria;
    private Integer voltajeVI;
    private Integer porcentajeBateria;
    private Integer estadoPoder;
    private String tiempoOperacionMotor;
    private Integer velocidadApertura;
    private Integer velocidadCierre;
    private Integer tiempoPausa;
    private Integer estadoBluetooth;

    private String nivelAlarma;
    private String nivelAlarma1;
    private String nivelAlarma2;
    private Integer nivelAlarmaSuperior1;
    private Integer nivelAlarmaInferior2;
    private String codigoNivelAlarma;

    private Integer activadoDesactivado;
    private Integer estadoBotonManual;
    private Integer usoBotonManual;
    private Integer tiempoActivado;

    private String fechaHoraInicioReproducción;
    private String fechaHoraFinReproducción;
    private Integer cantidadReproduccion;
    private Integer codigoMensajeReproducir;
    private Boolean procesarReproduccion;
    private Integer tiempoReproduccion;
    private Integer tiempoReproduccionT;
    private Integer cantidadReproduccionT;
    
    private String canal;
    private String info;

    public String getVersionTrama() {
        return versionTrama;
    }

    public void setVersionTrama(String versionTrama) {
        this.versionTrama = versionTrama;
    }

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
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

    public Date getFechaHoraLecturaDato() {
        return fechaHoraLecturaDato;
    }

    public void setFechaHoraLecturaDato(Date fechaHoraLecturaDato) {
        this.fechaHoraLecturaDato = fechaHoraLecturaDato;
    }

    public Date getFechaHoraEnvioDato() {
        return fechaHoraEnvioDato;
    }

    public void setFechaHoraEnvioDato(Date fechaHoraEnvioDato) {
        this.fechaHoraEnvioDato = fechaHoraEnvioDato;
    }

    public Integer getTipoTrama() {
        return tipoTrama;
    }

    public void setTipoTrama(Integer tipoTrama) {
        this.tipoTrama = tipoTrama;
    }

    public Boolean getTramaRetransmitida() {
        return tramaRetransmitida;
    }

    public void setTramaRetransmitida(Boolean tramaRetransmitida) {
        this.tramaRetransmitida = tramaRetransmitida;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getCodigoAlarma() {
        return codigoAlarma;
    }

    public void setCodigoAlarma(String codigoAlarma) {
        this.codigoAlarma = codigoAlarma;
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

    public Boolean getEstadoAperturaCierrePuertas() {
        return estadoAperturaCierrePuertas;
    }

    public void setEstadoAperturaCierrePuertas(Boolean estadoAperturaCierrePuertas) {
        this.estadoAperturaCierrePuertas = estadoAperturaCierrePuertas;
    }

    public Boolean getEstadoErrorCritico() {
        return estadoErrorCritico;
    }

    public void setEstadoErrorCritico(Boolean estadoErrorCritico) {
        this.estadoErrorCritico = estadoErrorCritico;
    }

    public Integer getTipoEnergizacion() {
        return tipoEnergizacion;
    }

    public void setTipoEnergizacion(Integer tipoEnergizacion) {
        this.tipoEnergizacion = tipoEnergizacion;
    }

    public Double getPorcentajeCargaBaterias() {
        return porcentajeCargaBaterias;
    }

    public void setPorcentajeCargaBaterias(double porcentajeCargaBaterias) {
        this.porcentajeCargaBaterias = porcentajeCargaBaterias;
    }

    public Double getVelocidaMotor() {
        return velocidaMotor;
    }

    public void setVelocidaMotor(Double velocidaMotor) {
        this.velocidaMotor = velocidaMotor;
    }

    public Double getFuerzaMotor() {
        return fuerzaMotor;
    }

    public void setFuerzaMotor(Double fuerzaMotor) {
        this.fuerzaMotor = fuerzaMotor;
    }

    public Integer getNumeroEventoBusEstacion() {
        return numeroEventoBusEstacion;
    }

    public void setNumeroEventoBusEstacion(Integer numeroEventoBusEstacion) {
        this.numeroEventoBusEstacion = numeroEventoBusEstacion;
    }

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getTipologiaVehiculo() {
        return tipologiaVehiculo;
    }

    public void setTipologiaVehiculo(String tipologiaVehiculo) {
        this.tipologiaVehiculo = tipologiaVehiculo;
    }

    public String getNumeroParada() {
        return numeroParada;
    }

    public void setNumeroParada(String numeroParada) {
        this.numeroParada = numeroParada;
    }

    public String getNombreEstacion() {
        return nombreEstacion;
    }

    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }

    public String getNombreVagon() {
        return nombreVagon;
    }

    public void setNombreVagon(String nombreVagon) {
        this.nombreVagon = nombreVagon;
    }

    public Integer getModoOperacion() {
        return modoOperacion;
    }

    public void setModoOperacion(Integer modoOperacion) {
        this.modoOperacion = modoOperacion;
    }

    public Integer getTiempoApertura() {
        return tiempoApertura;
    }

    public void setTiempoApertura(Integer tiempoApertura) {
        this.tiempoApertura = tiempoApertura;
    }

    public String getTipoTramaBusEstacion() {
        return tipoTramaBusEstacion;
    }

    public void setTipoTramaBusEstacion(String tipoTramaBusEstacion) {
        this.tipoTramaBusEstacion = tipoTramaBusEstacion;
    }

    public Integer getEntradasApertura() {
        return entradasApertura;
    }

    public void setEntradasApertura(Integer entradasApertura) {
        this.entradasApertura = entradasApertura;
    }

    public Integer getPorcentajeApertura() {
        return porcentajeApertura;
    }

    public void setPorcentajeApertura(int porcentajeApertura) {
        this.porcentajeApertura = porcentajeApertura;
    }

    public Integer getContadorEncoder() {
        return contadorEncoder;
    }

    public void setContadorEncoder(Integer contadorEncoder) {
        this.contadorEncoder = contadorEncoder;
    }

    public Boolean getEstadoCalibracion() {
        return estadoCalibracion;
    }

    public void setEstadoCalibracion(Boolean estadoCalibracion) {
        this.estadoCalibracion = estadoCalibracion;
    }

    public Integer getVoltajeBateria() {
        return voltajeBateria;
    }

    public void setVoltajeBateria(Integer voltajeBateria) {
        this.voltajeBateria = voltajeBateria;
    }

    public Integer getVoltajeVI() {
        return voltajeVI;
    }

    public void setVoltajeVI(Integer voltajeVI) {
        this.voltajeVI = voltajeVI;
    }

    public Integer getPorcentajeBateria() {
        return porcentajeBateria;
    }

    public void setPorcentajeBateria(int porcentajeBateria) {
        this.porcentajeBateria = porcentajeBateria;
    }

    public Integer getEstadoPoder() {
        return estadoPoder;
    }

    public void setEstadoPoder(int estadoPoder) {
        this.estadoPoder = estadoPoder;
    }

    public String getTiempoOperacionMotor() {
        return tiempoOperacionMotor;
    }

    public void setTiempoOperacionMotor(String tiempoOperacionMotor) {
        this.tiempoOperacionMotor = tiempoOperacionMotor;
    }

    public Integer getVelocidadApertura() {
        return velocidadApertura;
    }

    public void setVelocidadApertura(Integer velocidadApertura) {
        this.velocidadApertura = velocidadApertura;
    }

    public Integer getVelocidadCierre() {
        return velocidadCierre;
    }

    public void setVelocidadCierre(Integer velocidadCierre) {
        this.velocidadCierre = velocidadCierre;
    }

    public Integer getTiempoPausa() {
        return tiempoPausa;
    }

    public void setTiempoPausa(Integer tiempoPausa) {
        this.tiempoPausa = tiempoPausa;
    }

    public boolean isTramaRetransmitida() {
        return tramaRetransmitida;
    }

    public void setTramaRetransmitida(boolean tramaRetransmitida) {
        this.tramaRetransmitida = tramaRetransmitida;
    }

    public Integer getEstadoBluetooth() {
        return estadoBluetooth;
    }

    public void setEstadoBluetooth(Integer estadoBluetooth) {
        this.estadoBluetooth = estadoBluetooth;
    }

    public String getNivelAlarma() {
        return nivelAlarma;
    }

    public void setNivelAlarma(String nivelAlarma) {
        this.nivelAlarma = nivelAlarma;
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

    public Integer getNivelAlarmaSuperior1() {
        return nivelAlarmaSuperior1;
    }

    public void setNivelAlarmaSuperior1(Integer nivelAlarmaSuperior1) {
        this.nivelAlarmaSuperior1 = nivelAlarmaSuperior1;
    }

    public Integer getNivelAlarmaInferior2() {
        return nivelAlarmaInferior2;
    }

    public void setNivelAlarmaInferior2(Integer nivelAlarmaInferior2) {
        this.nivelAlarmaInferior2 = nivelAlarmaInferior2;
    }

    public Integer getActivadoDesactivado() {
        return activadoDesactivado;
    }

    public void setActivadoDesactivado(Integer activadoDesactivado) {
        this.activadoDesactivado = activadoDesactivado;
    }

    public Integer getEstadoBotonManual() {
        return estadoBotonManual;
    }

    public void setEstadoBotonManual(Integer estadoBotonManual) {
        this.estadoBotonManual = estadoBotonManual;
    }

    public String getCodigoNivelAlarma() {
        return codigoNivelAlarma;
    }

    public void setCodigoNivelAlarma(String codigoNivelAlarma) {
        this.codigoNivelAlarma = codigoNivelAlarma;
    }

    public Integer getUsoBotonManual() {
        return usoBotonManual;
    }

    public void setUsoBotonManual(Integer usoBotonManual) {
        this.usoBotonManual = usoBotonManual;
    }

    public String getFechaHoraInicioReproducción() {
        return fechaHoraInicioReproducción;
    }

    public void setFechaHoraInicioReproducción(String fechaHoraInicioReproducción) {
        this.fechaHoraInicioReproducción = fechaHoraInicioReproducción;
    }

    public String getFechaHoraFinReproducción() {
        return fechaHoraFinReproducción;
    }

    public void setFechaHoraFinReproducción(String fechaHoraFinReproducción) {
        this.fechaHoraFinReproducción = fechaHoraFinReproducción;
    }

    public Integer getCantidadReproduccion() {
        return cantidadReproduccion;
    }

    public void setCantidadReproduccion(Integer cantidadReproduccion) {
        this.cantidadReproduccion = cantidadReproduccion;
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

    public Integer getTiempoActivado() {
        return tiempoActivado;
    }

    public void setTiempoActivado(Integer tiempoActivado) {
        this.tiempoActivado = tiempoActivado;
    }

    public Integer getCodigoMensajeReproducir() {
        return codigoMensajeReproducir;
    }

    public void setCodigoMensajeReproducir(Integer codigoMensajeReproducir) {
        this.codigoMensajeReproducir = codigoMensajeReproducir;
    }

    public Boolean getProcesarReproduccion() {
        return procesarReproduccion;
    }

    public void setProcesarReproduccion(Boolean procesarReproduccion) {
        this.procesarReproduccion = procesarReproduccion;
    }

    public Integer getTiempoReproduccion() {
        return tiempoReproduccion;
    }

    public void setTiempoReproduccion(Integer tiempoReproduccion) {
        this.tiempoReproduccion = tiempoReproduccion;
    }

    public Integer getTiempoReproduccionT() {
        return tiempoReproduccionT;
    }

    public void setTiempoReproduccionT(Integer tiempoReproduccionT) {
        this.tiempoReproduccionT = tiempoReproduccionT;
    }

    public Integer getCantidadReproduccionT() {
        return cantidadReproduccionT;
    }

    public void setCantidadReproduccionT(Integer cantidadReproduccionT) {
        this.cantidadReproduccionT = cantidadReproduccionT;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    
}
