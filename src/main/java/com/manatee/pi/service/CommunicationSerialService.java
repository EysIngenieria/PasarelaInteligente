/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.service;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isMessageArrived() {
        return messageArrived;
    }

    public void setMessageArrived(boolean messageArrived) {
        this.messageArrived = messageArrived;
    }

    public CommunicationSerialService(String port, Baud baud) {
        serial = SerialFactory.createInstance();
        SerialConfig serialConfig = new SerialConfig();
        serialConfig.device(port).baud(baud).dataBits(DataBits._8).parity(Parity.NONE).stopBits(StopBits._1).flowControl(FlowControl.NONE);
        try {
            serial.open(serialConfig);
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());;
        }
    }

    public void SendData(String data) {
        try {
            serial.write(data);
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void ReceivedData() {
        try {
            serial.addListener(event -> {
                try {
                    messageArrived = true;
                    data = event.getAsciiString();
                } catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            });
        } catch (IllegalStateException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
