/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author Josue Rodriguez EyS
 */
public class JsonService {
    
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

}
