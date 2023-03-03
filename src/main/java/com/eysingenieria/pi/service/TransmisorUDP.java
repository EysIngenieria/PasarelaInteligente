/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import java.io.IOException;
import java.net.*;
import java.util.logging.*;

/**
 *
 * @author DesarrolloJC
 */
public class TransmisorUDP {

    private final String direccionIP;
    private final int puertoPublicar;

    public TransmisorUDP(String direccionIP, int puertoPublicar) {
        this.direccionIP = direccionIP;
        this.puertoPublicar = puertoPublicar;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public int getPuertoPublicar() {
        return puertoPublicar;
    }

    public void EnviarDato(byte[] data) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(direccionIP);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, puertoPublicar);
            socket.send(packet);
        } catch (IOException io) {
            System.out.println(io+ "ERROR 255");
        }
    }
}
