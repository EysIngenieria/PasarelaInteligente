/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import com.eysingenieria.pi.entities.ComandoInterfazVisual;
import com.eysingenieria.pi.entities.OP_RegistroCrudo;
import com.eysingenieria.pi.entities.Puerta;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author DesarrolloJR
 */
public class AuxService {
    
    public JSONObject comandoCDEGMTE(String datoCDEGString, String idEstacion, SimpleDateFormat formatoFecha ) {
        JSONObject envioManatee = new JSONObject();
        envioManatee.put("trama", datoCDEGString);
        envioManatee.put("estadoEnvioManatee", true);
        Long idF = Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        String id = idEstacion + idF;
        envioManatee.put("IDManatee", id);
        envioManatee.put("fechaHoraEnvio", formatoFecha.format(new Date()));
        return envioManatee;
    }
    
    //Actualización de hora del mvc
    public JSONObject wr_date( ) {
        JSONObject wr_date = new JSONObject();
        wr_date.put("origen", "PI");
        wr_date.put("funcion", "WR_DATE");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        wr_date.put("trama", format.format(calendar.getTime()));
        return wr_date;
    }
    
    //Json Comando de apertura generado por el CDEG
    
    public JSONObject ComandoAperturaPuertaCDEG(OP_RegistroCrudo comandoCrudo, Puerta temp, String idVagon) {
        comandoCrudo.setFuncion("CMD");
        String trama = "00";
        trama += String.format("%02x", (Integer.parseInt(temp.getIdPuerta()) & 0xFF));
        String consecutivo = "01";
        trama += consecutivo + "7101";
        trama += "0402";
        trama += "00";
        comandoCrudo.setIdVagon(idVagon);
        comandoCrudo.setTrama(trama);
        JSONObject envio = new JSONObject();
        envio.put("origen", comandoCrudo.getOrigen());
        envio.put("funcion", comandoCrudo.getFuncion());
        envio.put("idVagon", comandoCrudo.getIdVagon());
        envio.put("canal", comandoCrudo.getCanal());
        envio.put("idPuerta", temp.getIdPuerta());
        envio.put("trama", comandoCrudo.getTrama());
        System.out.println(envio + "APERTURA CDG");
        return envio;
    }
    
    public JSONObject JsonProcesarComandoIV(ComandoInterfazVisual comandoInterfazVisual,String puerta, Puerta puertaTemp ) {
        
        OP_RegistroCrudo comandoCrudo = new OP_RegistroCrudo();
        comandoCrudo.setOrigen("InterfazVisual");
        comandoCrudo.setIdVagon(String.format("%01d", Integer.parseInt(puertaTemp.getVagon())));
        comandoCrudo.setIdPuerta(puerta);
        String trama = "00";
        trama += String.format("%02x", (Integer.parseInt(puertaTemp.getIdPuerta()) & 0xFF));
        comandoCrudo.setCanal(puertaTemp.getCanal());
        String consecutivo = "01";
        trama += consecutivo + "7101";
        if (comandoInterfazVisual.getComando().replaceAll(" ", "").equalsIgnoreCase("activarBluetooth")) {
            trama += "08";
            trama += String.format("%04X", (comandoInterfazVisual.getActivationTime() & 0xFFFF) * 60);
            trama += comandoInterfazVisual.getDireccionMac();
            trama += comandoInterfazVisual.getDireccionMac();
        } else if (comandoInterfazVisual.getComando().replaceAll(" ", "").equalsIgnoreCase("desactivarBluetooth")) {
            trama += "08";
            int tiempo = 0;
            trama += String.format("%04X", (tiempo & 0xFFFF) * 60);
            trama += "000000000000";
            trama += "000000000000";
        } else if (comandoInterfazVisual.getComando().replaceAll(" ", "").equalsIgnoreCase("aperturaDePuerta")) {
            trama += "0402";
        } else if (comandoInterfazVisual.getComando().replaceAll(" ", "").equalsIgnoreCase("cierreDePuerta")) {
            trama += "0404";
        } else if (comandoInterfazVisual.getComando().replaceAll(" ", "").equalsIgnoreCase("cicloDeApertura")) {
            trama += "05";
        } else if (comandoInterfazVisual.getComando().replaceAll(" ", "").equalsIgnoreCase("activarBotonDeUsuario")) {
            trama += "0701";
        } else if (comandoInterfazVisual.getComando().replaceAll(" ", "").equalsIgnoreCase("desactivarBotonDeUsuario")) {
            trama += "0700";
        } else if (comandoInterfazVisual.getComando().replaceAll(" ", "").equalsIgnoreCase("activarModoManual")) {
            trama += "0401";
        }
        trama += "00";
        comandoCrudo.setTrama(trama);
        comandoCrudo.setFuncion("CMD");

        //JSONObject dato = new JSONObject(trama);
        JSONObject envio = new JSONObject();
        envio.put("origen", comandoCrudo.getOrigen());
        envio.put("funcion", comandoCrudo.getFuncion());
        envio.put("idVagon", comandoCrudo.getIdVagon());
        envio.put("canal", comandoCrudo.getCanal());
        envio.put("idPuerta", puertaTemp.getIdPuerta());
        envio.put("trama", comandoCrudo.getTrama());
        return envio;
    }
}
