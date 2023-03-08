/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.constants;

/**
 *
 * @author DesarrolloJC
 */
public class Constantes {

    public static class Eventos {

        public static final byte ABRIENDO = 1;
        public static final byte ABIERTA = 2;
        public static final byte CERRANDO = 3;
        public static final byte CERRADA = 4;
        public static final byte OBSTACULO_ABRIENDO = 5;
        public static final byte OBSTACULO_CERRANDO = 6;
        public static final byte APERTURA_FORZADA = 7;        
        public static final byte CIERRE_FORZADO = 10;
        public static final byte CALIBRACION = 12;
        public static final byte CAMBIO_FUENTE_ALIMENTACION = 33;
        public static final byte BATERIA_BAJA = 34;
        public static final byte BATERIA_NO_CARGA = 35;
        public static final byte PERIODICO_1 = 36;
        public static final byte PERIODICO_2 = 37;
        public static final byte EMERGENCIA = 40;
        public static final byte CAMBIO_MODO = 38;
    }
    
    public static class Comandos{
        public static final int RESET = 101;
        public static final int AUTODIAGNOSTICO = 102;
        public static final int APERTURA = 103;
        public static final int CIERRE = 104;
        public static final int REPRODUCCION = 105;
        public static final int BOTON_MANUAL = 106;
    }
}
