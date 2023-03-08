/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author DesarrolloJC
 */
public class PublicadorMANATEEMQTT1Service {
    private MqttClient mqttClient;
    private MqttConnectOptions mqttConnectOptions;
    private String clave;
    public PublicadorMANATEEMQTT1Service( String broker, String clave, String clienteManatee) {
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
        } catch (MqttException me) {
            try {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PublicadorMANATEEMQTT1Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(me + " ERROR 8");
                mqttClient.connect(mqttConnectOptions);
                return false;
            } catch (MqttException ex) {
                Logger.getLogger(PublicadorMANATEEMQTT1Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
