/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DesarrolloJC
 */
public class ReceptorUDP {
    
    private String dato;
    private boolean entroDato;
    private final String direccionIP;
    private final int puertoRecibir;    
    private DatagramSocket socket;
    private String ip;
    
    public ReceptorUDP(String direccionIP, int puertoRecibir) {
        this.direccionIP = direccionIP;
        this.puertoRecibir = puertoRecibir;
        Inicializar();
    }
    
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public boolean isEntroDato() {
        return entroDato;
    }

    public void setEntroDato(boolean entroDato) {
        this.entroDato = entroDato;
    }

    private void Inicializar() {
        try {
            System.out.println("Puerto a recibir "+puertoRecibir);
            socket = new DatagramSocket(puertoRecibir);
            
        } catch (SocketException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void RecibirDato() {
        try {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            InetAddress address = packet.getAddress();
            ip = packet.getAddress().toString();
            ip = ip.replace("/", "");
            packet = new DatagramPacket(buf, buf.length, address, puertoRecibir);
            dato = new String(packet.getData(), 0, packet.getLength());
            entroDato = true;
            System.out.println("ESCUCHANDO: "+ dato + " IP: " + ip);
            if(dato.contains(">cerrarpuerto<")){
                socket.close();
                System.out.println("SOCKET CERRADO: "+ dato);
            }
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    public boolean isClosed(){
        return socket.isClosed();
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void CloseSocket() {
        socket.close();
    }
    
}
