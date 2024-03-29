/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author DesarrolloJC
 */
public class PublicadorMANATEEMQTT1 {
    private MqttClient mqttClient;
    private MqttConnectOptions mqttConnectOptions;
    private String clave;
    public PublicadorMANATEEMQTT1( String broker, String clave, String clienteManatee) {
        try {
            this.clave = clave;
            MemoryPersistence memoryPersistence = new MemoryPersistence();
            mqttClient = new MqttClient(broker, MqttClient.generateClientId(), memoryPersistence);
            mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setPassword(clave.toCharArray());
            mqttConnectOptions.setUserName(clienteManatee);
            mqttClient.connect(mqttConnectOptions);
        } catch (MqttException ex) {
           
        }
    }
    
    public boolean isConected(){
        return mqttClient.isConnected();
    }
    
    public boolean Publisher(byte[] data, String topic) {
        
        try {
            
            
            
            MqttMessage mqttMessage = new MqttMessage(data);
            mqttMessage.setQos(1);
            mqttClient.publish(topic, mqttMessage);
            return true;
            //System.out.println("Publicado: " + new String(data, StandardCharsets.UTF_8));
            //mqttClient.disconnect();
        } catch (MqttException me) {
            try {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
                System.out.println(me + " ERROR 8");
                mqttClient.connect(mqttConnectOptions);
                return false;
            } catch (MqttException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
        return false;
    }

}
