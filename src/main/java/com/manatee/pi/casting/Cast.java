/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.casting;

import com.manatee.pi.entities.Alarma;
import com.manatee.pi.entities.CFG_Alarma;
import com.manatee.pi.entities.CFG_Evento;
import com.manatee.pi.entities.CFG_NivelAlarma;
import com.manatee.pi.entities.Comando;
import com.manatee.pi.entities.ComandoInterfazVisual;
import com.manatee.pi.entities.Configuracion;
import com.manatee.pi.entities.DatoCDEG;
import com.manatee.pi.entities.Evento;
import com.manatee.pi.entities.HubData;
import com.manatee.pi.entities.NivelAlarma;
import com.manatee.pi.entities.OP_RegistroCrudo;
import com.manatee.pi.entities.Puerta;
import com.manatee.pi.entities.TipoDatoCDEG;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author DesarrolloJC
 */
public class Cast {

    public DatoCDEG JSONtoCDEGData(String data) {
        return new Gson().fromJson(data, DatoCDEG.class);
    }

    public HubData JSONtoHubData(String data) {
        return new Gson().fromJson(data, HubData.class);
    }

    public TipoDatoCDEG JSONtoTipoDatoCDEG(String data) {
        return new Gson().fromJson(data, TipoDatoCDEG.class);
    }

    public Configuracion JSONtoConfiguracion(String data) {
        return new Gson().fromJson(data, Configuracion.class);
    }

    public String ObjtoJSON(Object object) {
        return new Gson().toJson(object);
    }

    public OP_RegistroCrudo JSONtoRegistroEventoCrudo(String data) {
        OP_RegistroCrudo registroCrudo = new OP_RegistroCrudo();
        JSONObject dato = new JSONObject(data);

        //hay que cambiarlo
        registroCrudo.setOrigen("Estacion");
        if (!dato.isNull("idPuerta")) {
            registroCrudo.setIdPuerta(dato.getString("idPuerta"));
        }
        if (!dato.isNull("trama")) {
            registroCrudo.setTrama(dato.getString("trama"));
        }
        if(!dato.isNull("idVagon")){
        registroCrudo.setIdVagon(dato.getString("idVagon"));
        }
        if(!dato.isNull("funcion")){
        registroCrudo.setFuncion((dato.getString("funcion")));
        }
        if (!dato.isNull("canal")) {
            registroCrudo.setCanal(dato.getString("canal"));
        }

        return registroCrudo;
    }

    public CFG_Evento EventotoCFG_Evento(Evento dato) {
        CFG_Evento evento = new CFG_Evento();
        evento.setId(dato.getId());
        evento.setNombre(dato.getCodigoEvento());
        return evento;
    }

    public CFG_Alarma AlarmatoCFG_Alarma(Alarma dato) {
        CFG_Alarma alarma = new CFG_Alarma();
        alarma.setId(dato.getId());
        alarma.setNombre(dato.getCodigoAlarma());
        return alarma;
    }

    public CFG_NivelAlarma NivelAlarmatoCFG_NivelAlarma(NivelAlarma dato) {
        CFG_NivelAlarma nivelAlarma = new CFG_NivelAlarma();
        nivelAlarma.setSecuencia(Integer.parseInt(dato.getCodigoNivelAlarma().split("")[3]));
        nivelAlarma.setValor(dato.getCodigoNivelAlarma());
        nivelAlarma.setNivelInferior(dato.getValorNivelAlarmaInferior());
        nivelAlarma.setNivelSuperior(dato.getValorNivelAlarmaSuperior());
        return nivelAlarma;
    }

    public Comando JSONtoComando(String dato) {
        return new Gson().fromJson(dato, Comando.class);
    }

    public ComandoInterfazVisual JSONtoComandoInterfazVisual(String dato) {
        return new Gson().fromJson(dato, ComandoInterfazVisual.class);
    }

    public void datosPuerta(Puerta puerta, DatoCDEG dato) {
        if ( puerta.getEstadoErrorCritico() != null) {
            dato.setEstadoErrorCritico(puerta.getEstadoErrorCritico());
        }
        if (puerta.getCiclosApertura() != null) {
            dato.setCiclosApertura(puerta.getCiclosApertura());
        }
        if ( puerta.getHorasServicio() != null) {
            dato.setHorasServicio(puerta.getHorasServicio());
        }
        if (puerta.isEstadoAperturaCierre() != null) {
            dato.setEstadoAperturaCierrePuertas(puerta.isEstadoAperturaCierre());
        }
        if ( puerta.getVelocidadMotor() != null) {
            dato.setVelocidaMotor(puerta.getVelocidadMotor());
        }
        if (puerta.getFuerzaMotor() != null) {
            dato.setFuerzaMotor(puerta.getFuerzaMotor());
        }
        if (puerta.getTipoEnergizacion() != null) {
            dato.setTipoEnergizacion(puerta.getTipoEnergizacion());
        }
        if (puerta.getEntradasApertura() != null) {
            dato.setEntradasApertura(puerta.getEntradasApertura());
        }
        if ( puerta.getTiempoApertura() != null) {
            dato.setTiempoApertura(puerta.getTiempoApertura());
        }
        if ( puerta.getPorcentajeApertura() != null) {
            dato.setPorcentajeApertura(puerta.getPorcentajeApertura());
        }
        if ( puerta.getUsoBotonManual() != null) {
            dato.setUsoBotonManual(puerta.getUsoBotonManual());
        }
        if ( puerta.getFechaHoraInicioActivacionDesactivacion() != null) {
            dato.setFechaHoraInicioActivaciondesactivacion(puerta.getFechaHoraInicioActivacionDesactivacion());
        }
        if (puerta.getFechaHoraFinalActivacionDesactivacion() != null) {
            dato.setFechaHoraFinalActivaciondesactivacion(puerta.getFechaHoraFinalActivacionDesactivacion());
        }
        if ( puerta.getActivadoDesactivado() != null) {
            dato.setActivadoDesactivado(puerta.getActivadoDesactivado());
        }
        if ( puerta.getFechaHoraInicioReproduccion() != null) {
            dato.setFechaHoraInicioReproducción(puerta.getFechaHoraFinalReproduccion());
        }
        if ( puerta.getFechaHoraFinalReproduccion() != null) {
            dato.setFechaHoraFinReproducción(puerta.getFechaHoraFinalReproduccion());
        }
        if ( puerta.getCantidadReproduccion() != null) {
            dato.setCantidadReproduccion(puerta.getCantidadReproduccion());
        }
        if ( puerta.getCodigoMensajeReproduccion() != null) {
            dato.setCodigoMensajeReproducir(puerta.getCodigoMensajeReproduccion());
        }
        if ( puerta.isEstadoCalibracion() != null) {
            dato.setEstadoCalibracion(puerta.isEstadoCalibracion());
        }
        if ( puerta.getContadorEncoder() != null) {
            dato.setContadorEncoder(puerta.getContadorEncoder());
        }
        if ( puerta.getEstadoPoder() != null) {
            dato.setEstadoPoder(puerta.getEstadoPoder());
        }

        if ( puerta.getVoltajeBateria() != null) {
            dato.setVoltajeBateria(puerta.getVoltajeBateria());
        }
        if ( puerta.getPorcentajeCargaBaterias() != null) {
            dato.setPorcentajeCargaBaterias(puerta.getPorcentajeCargaBaterias());
        }
        if (puerta.getVoltajeVI() != null) {
            dato.setVoltajeVI(puerta.getVoltajeVI());
        }
        if ( puerta.getTiempoOperacionMotor() != null) {
            dato.setTiempoOperacionMotor(puerta.getTiempoOperacionMotor());
        }
        if ( puerta.getModoOperacion() != null) {
            dato.setModoOperacion(puerta.getModoOperacion());
        }
        if ( puerta.getVelocidadCierre() != null) {
            dato.setVelocidadCierre(puerta.getVelocidadCierre());
        }
        if ( puerta.getVelocidadApertura() != null) {
            dato.setVelocidadApertura(puerta.getVelocidadApertura());
        }
        if ( puerta.getTiempoPausa() != null) {
            dato.setTiempoPausa(puerta.getTiempoPausa());
        }
        if ( puerta.getEstadoErrorCritico() != null) {
            dato.setEstadoBluetooth(puerta.getEstadoBluetooth());
        }
        if(puerta.getEstadoBotonManual()!=null){
            dato.setEstadoBotonManual(puerta.getEstadoBotonManual());
        }

    }
}
