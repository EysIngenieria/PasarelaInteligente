/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import com.pi4j.io.serial.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DesarrolloJC
 */
public class CommunicationSerialService {

    private Serial serial;
    private String data;
    private boolean messageArrived;

    public String getDataCommunicationSerialService() {
        return data;
    }

    public void setDataCommunicationSerialService(String data) {
        this.data = data;
    }

    public boolean isMessageArrivedCommunicationSerialService() {
        return messageArrived;
    }

    public void setMessageArrivedCommunicationSerialService(boolean messageArrived) {
        this.messageArrived = messageArrived;
    }

    public CommunicationSerialService(String port, Baud baud) {
        serial = SerialFactory.createInstance();
        SerialConfig serialConfig = new SerialConfig();
        serialConfig.device(port).baud(baud).dataBits(DataBits._8).parity(Parity.NONE).stopBits(StopBits._1).flowControl(FlowControl.NONE);
        try {
            serial.open(serialConfig);
        } catch (IOException ex) {
            Logger.getLogger(CommunicationSerialService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SendDataCommunicationSerialService(String data) {
        try {
            serial.write(data);
        } catch (IOException ex) {
            Logger.getLogger(CommunicationSerialService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ReceivedDataCommunicationSerialService() {
        try {
            serial.addListener(event -> {
                try {
                    messageArrived = true;
                    data = event.getAsciiString();
                } catch (IOException ex) {
                    Logger.getLogger(CommunicationSerialService.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (IllegalStateException ex) {
            Logger.getLogger(CommunicationSerialService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
