/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import com.eysingenieria.pi.datamanager.DataManager;
import com.eysingenieria.pi.data.model.Comando;
import com.eysingenieria.pi.data.entities.Puerta;

/**
 *
 * @author DesarrolloJC
 */
public class ProcesarDatoCDEG {

    String trama = "";

    public String ProcesarComando(Comando comando, DataManager dm) {
        //trama += "7E";
        trama += "00";
        Puerta tem = dm.GetPuerta(comando.getIdPuerta());
        trama += "0"+ tem.getIdPuerta();
//        switch (comando.getIdPuerta().split("-")[3]) {
//            case "1":
//                trama += "01";
//                break;
//            case "2":
//                trama += "02";
//                break;
//        }
        trama += "007101";
        switch (comando.getCodigoMensaje()) {
            case Constantes.Comandos.RESET:
                //trama += "01";
                break;
            case Constantes.Comandos.AUTODIAGNOSTICO:

                break;
            case Constantes.Comandos.APERTURA:
                trama += "0402";
                break;
            case Constantes.Comandos.CIERRE:
                trama += "0404";
                break;
            case Constantes.Comandos.REPRODUCCION:
                trama += "0601";
                break;
        }
        trama += "00";
        //trama += "7E";
        return trama;
    }

}
