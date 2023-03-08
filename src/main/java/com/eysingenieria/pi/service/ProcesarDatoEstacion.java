/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import com.eysingenieria.pi.casting.Cast;
import com.eysingenieria.pi.data.model.DatoCDEG;
import com.eysingenieria.pi.data.model.HubData;

/**
 *
 * @author DesarrolloJC
 */
public class ProcesarDatoEstacion {

    Cast cast;
    HubData hubData;

    public ProcesarDatoEstacion() {
        cast = new Cast();
    }

    public DatoCDEG ProcessData(String data, DatoCDEG datoCDEG) {
        byte[] tramaBytes = hexStringToByteArray(data);
        try {
            datoCDEG.setInfo("DESCONOCIDO");
            if (tramaBytes[3] == 112) {
                switch (tramaBytes[5]) {
                    case Constantes.Eventos.ABRIENDO:
                        datoCDEG.setEstadoAperturaCierrePuertas((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setEntradasApertura((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setTiempoApertura((tramaBytes[9] & 0xFF) << 8 | (tramaBytes[10] & 0xFF));
                        datoCDEG.setPorcentajeApertura((tramaBytes[11] & 0xFF) << 8 | (tramaBytes[12] & 0xFF));
                        if (datoCDEG.getEntradasApertura() == 8) {
                            datoCDEG.setUsoBotonManual(1);
                            datoCDEG.setCodigoEvento("EVP13");
                        } else {
                            datoCDEG.setUsoBotonManual(0);
                        }
                        datoCDEG.setInfo("ABRIENDO");
                        break;
                    case Constantes.Eventos.ABIERTA:
                        datoCDEG.setCodigoEvento("EVP1");
                        datoCDEG.setEstadoAperturaCierrePuertas((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setEntradasApertura((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setTiempoApertura((tramaBytes[9] & 0xFF) << 8 | (tramaBytes[10] & 0xFF));
                        datoCDEG.setPorcentajeApertura((tramaBytes[11] & 0xFF) << 8 | (tramaBytes[12] & 0xFF));
                        
                        datoCDEG.setInfo("ABIERTA");
                        break;
                    case Constantes.Eventos.CERRANDO:
                        datoCDEG.setEstadoAperturaCierrePuertas((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setEntradasApertura((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setTiempoApertura((tramaBytes[9] & 0xFF) << 8 | (tramaBytes[10] & 0xFF));
                        datoCDEG.setPorcentajeApertura((tramaBytes[11] & 0xFF) << 8 | (tramaBytes[12] & 0xFF));
                        if (datoCDEG.getEntradasApertura() == 8) {
                            datoCDEG.setUsoBotonManual(1);
                        } else {
                            datoCDEG.setUsoBotonManual(0);
                        }
                        datoCDEG.setInfo("CERRANDO");
                        break;
                    case Constantes.Eventos.CERRADA:
                        datoCDEG.setCodigoEvento("EVP1");
                        datoCDEG.setEstadoAperturaCierrePuertas((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setEntradasApertura((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setTiempoApertura((tramaBytes[9] & 0xFF) << 8 | (tramaBytes[10] & 0xFF));
                        datoCDEG.setPorcentajeApertura((tramaBytes[11] & 0xFF) << 8 | (tramaBytes[12] & 0xFF));
                        
                        datoCDEG.setInfo("CERRADA");
                        break;
                    case Constantes.Eventos.OBSTACULO_ABRIENDO:
                        datoCDEG.setCodigoEvento("EVP3");
                        datoCDEG.setEstadoAperturaCierrePuertas((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setEntradasApertura((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setTiempoApertura((tramaBytes[9] & 0xFF) << 8 | (tramaBytes[10] & 0xFF));
                        datoCDEG.setPorcentajeApertura((tramaBytes[11] & 0xFF) << 8 | (tramaBytes[12] & 0xFF));
                        datoCDEG.setInfo("OBSTACULO_ABRIENDO");
                        break;
                    case Constantes.Eventos.OBSTACULO_CERRANDO:
                        datoCDEG.setCodigoEvento("EVP4");
                        datoCDEG.setEstadoAperturaCierrePuertas((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setEntradasApertura((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setTiempoApertura((tramaBytes[9] & 0xFF) << 8 | (tramaBytes[10] & 0xFF));
                        datoCDEG.setPorcentajeApertura((tramaBytes[11] & 0xFF) << 8 | (tramaBytes[12] & 0xFF));
                        datoCDEG.setInfo("OBSTACULO_CERRANDO");
                        break;
                    case Constantes.Eventos.APERTURA_FORZADA:
                        datoCDEG.setCodigoEvento("EVP2");
                        datoCDEG.setEstadoAperturaCierrePuertas((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setPorcentajeApertura((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setInfo("APERTURA_FORZADA");
                        break;
                    case Constantes.Eventos.CIERRE_FORZADO:
                        datoCDEG.setEstadoAperturaCierrePuertas((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setPorcentajeApertura((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setInfo("CIERRE_FORZADO");
                        break;
                    case Constantes.Eventos.CALIBRACION:
                        datoCDEG.setEstadoCalibracion((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setContadorEncoder((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setInfo("CALIBRACION");
                        break;
                    case Constantes.Eventos.CAMBIO_FUENTE_ALIMENTACION:
                        datoCDEG.setEstadoPoder(tramaBytes[6]);

                        if ((tramaBytes[6] & 0x01) == 0x1) {
                            datoCDEG.setCodigoEvento("EVP6");
                        }
                        datoCDEG.setVoltajeBateria((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setPorcentajeCargaBaterias((tramaBytes[9] & 0xFF) / 2);
                        datoCDEG.setVoltajeVI((tramaBytes[10] & 0xFF) << 8 | (tramaBytes[11] & 0xFF));
                        datoCDEG.setInfo("CAMBIO_FUENTE_ALIMENTACION");
                        break;
                    case Constantes.Eventos.BATERIA_BAJA:
                        datoCDEG.setEstadoPoder(tramaBytes[6] & 0xFF);
                        datoCDEG.setTipoEnergizacion(datoCDEG.getEstadoPoder() & 0x01);
                        datoCDEG.setVoltajeBateria((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setPorcentajeCargaBaterias((tramaBytes[9] & 0xFF) / 2);
                        datoCDEG.setVoltajeVI((tramaBytes[10] & 0xFF) << 8 | (tramaBytes[11] & 0xFF));
                        datoCDEG.setInfo("BATERIA_BAJA");
                        break;
                    case Constantes.Eventos.BATERIA_NO_CARGA:
                        datoCDEG.setEstadoPoder(tramaBytes[6] & 0xFF);
                        datoCDEG.setTipoEnergizacion(datoCDEG.getEstadoPoder() & 0x01);
                        datoCDEG.setVoltajeBateria((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setPorcentajeCargaBaterias((tramaBytes[9] & 0xFF) / 2);
                        datoCDEG.setVoltajeVI((tramaBytes[10] & 0xFF) << 8 | (tramaBytes[11] & 0xFF));
                        datoCDEG.setInfo("BATERIA_NO_CARGA");
                        break;
                    case Constantes.Eventos.PERIODICO_1:
                        datoCDEG.setEstadoAperturaCierrePuertas((tramaBytes[6] & 0x01) == 1);
                        datoCDEG.setEntradasApertura((tramaBytes[7] & 0xFF) << 8 | (tramaBytes[8] & 0xFF));
                        datoCDEG.setTiempoApertura(((tramaBytes[9] & 0xFF) << 8 | (tramaBytes[10] & 0xFF)));
                        datoCDEG.setPorcentajeApertura((tramaBytes[11] & 0xFF) << 8 | (tramaBytes[12] & 0xFF));
                        //SE NECESITA
                        datoCDEG.setEstadoPoder(tramaBytes[13] & 0xFF);
                        datoCDEG.setTipoEnergizacion(datoCDEG.getEstadoPoder() & 0x01);
                        datoCDEG.setVoltajeBateria((tramaBytes[14] & 0xFF) << 8 | (tramaBytes[15] & 0xFF));
                        datoCDEG.setPorcentajeCargaBaterias((tramaBytes[16] & 0xFF) / 2);
                        datoCDEG.setVoltajeVI((tramaBytes[17] & 0xFF) << 8 | (tramaBytes[18] & 0xFF));
                        datoCDEG.setInfo("PERIODICO_1");
                        break;

                    case Constantes.Eventos.PERIODICO_2:
                        datoCDEG.setInfo("PERIODICO_2");
                        datoCDEG.setCiclosApertura(0);
                        datoCDEG.setTiempoOperacionMotor("");
                        int temporal=0;
                        for (int i = 0; i < 4; i++) {
                            datoCDEG.setCiclosApertura(Integer.parseInt(String.format("%02X", datoCDEG.getCiclosApertura()) + String.format("%02X", (tramaBytes[i + 6] & 0xFF)), 16));
                            temporal=Integer.parseInt(String.format("%02X", temporal) + String.format("%02X", (tramaBytes[i + 10] & 0xFF)), 16);
                        }
                        datoCDEG.setHorasServicio((long)(temporal/ 3600));
                        if((tramaBytes[14] & 0xFF)==1){
                            datoCDEG.setModoOperacion(tramaBytes[14] & 0xFF);
                        }else{
                            datoCDEG.setModoOperacion(0);
                        }
                        

                        datoCDEG.setVelocidadCierre((tramaBytes[15] & 0xFF));
                        datoCDEG.setVelocidadApertura((tramaBytes[16] & 0xFF));

                        switch ((tramaBytes[16] & 0xFF)) {
                            case 1:
                                datoCDEG.setVelocidaMotor((double) 0.07);
                                datoCDEG.setFuerzaMotor(55.0);
                                break;
                            case 2:
                                datoCDEG.setVelocidaMotor((double) 0.15);
                                datoCDEG.setFuerzaMotor(88.0);
                                break;
                            case 3:
                                datoCDEG.setVelocidaMotor((double) 0.22);
                                datoCDEG.setFuerzaMotor(122.0);
                                break;
                            case 4:
                                datoCDEG.setVelocidaMotor((double) 0.29);
                                datoCDEG.setFuerzaMotor(164.0);
                                break;
                            case 5:
                                datoCDEG.setVelocidaMotor((double) 0.36);
                                datoCDEG.setFuerzaMotor(199.0);
                                break;
                            case 6:
                                datoCDEG.setVelocidaMotor((double) 0.43);
                                datoCDEG.setFuerzaMotor(232.0);
                                break;
                            case 7:
                                datoCDEG.setVelocidaMotor((double) 0.50);
                                datoCDEG.setFuerzaMotor(275.0);
                                break;
                            case 8:
                                datoCDEG.setVelocidaMotor((double) 0.58);
                                datoCDEG.setFuerzaMotor(312.0);
                                break;
                            case 9:
                                datoCDEG.setVelocidaMotor((double) 0.65);
                                datoCDEG.setFuerzaMotor(352.0);
                                break;
                            case 10:
                                datoCDEG.setVelocidaMotor((double) 0.65);
                                datoCDEG.setFuerzaMotor(352.0);
                                break;
                            default:
                                datoCDEG.setVelocidaMotor((double) 0.65);
                                datoCDEG.setFuerzaMotor(352.0);
                                break;
                        }
                        datoCDEG.setTiempoPausa((tramaBytes[17] & 0xFF));
                        datoCDEG.setEstadoBluetooth((tramaBytes[18] & 0x01));
                        int estado = tramaBytes[18] & 0x02;
                        if(estado == 0){
                            datoCDEG.setEstadoBotonManual(2);
                        }else{
                            datoCDEG.setEstadoBotonManual(1);
                        }
                        
                        
                        if ((tramaBytes[19] & 0x01) == 0x01) {
                            if (datoCDEG.getEstadoErrorCritico() == null || !datoCDEG.getEstadoErrorCritico()) {
                                datoCDEG.setEstadoErrorCritico(true);

                            }
                        } else {
                            datoCDEG.setEstadoErrorCritico(false);
                        }
                        break;
                    case Constantes.Eventos.EMERGENCIA:
                        datoCDEG.setInfo("EMERGENCIA");
                        datoCDEG.setCodigoEvento("EVP7");
                        break;
                    case Constantes.Eventos.CAMBIO_MODO:
                        if ((tramaBytes[6] & 0xFF) == 1) {
                            datoCDEG.setModoOperacion(tramaBytes[6] & 0xFF);
                        } else {
                            datoCDEG.setModoOperacion(0);
                        }
                        
                        datoCDEG.setInfo("CAMBIO_MODO");
                        break;

                }
            }else if((tramaBytes[3]&0xff)==241){
                //System.out.println("confirmacion comando "+ (tramaBytes[6]&0xff)  + "  consecutivo " + (tramaBytes[5]&0xff));
                
            
            }

        } catch (NumberFormatException ex) {

        }

        return datoCDEG;
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
