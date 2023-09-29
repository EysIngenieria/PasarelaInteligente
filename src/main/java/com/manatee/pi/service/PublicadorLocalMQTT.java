/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author DesarrolloJC
 */
public class PublicadorLocalMQTT {
    private MqttClient mqttClient;
    private MqttConnectOptions mqttConnectOptions;

    public PublicadorLocalMQTT( String broker) {
        try {
            MemoryPersistence memoryPersistence = new MemoryPersistence();
            mqttClient = new MqttClient(broker, MqttClient.generateClientId(), memoryPersistence);
            mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);
            mqttClient.connect(mqttConnectOptions);
        } catch (MqttException ex) {
            System.out.println(ex + " ERROR 8");
        }
    }
    
    public void Publisher(byte[] data, String topic) {
        
        try {
            
           
            MqttMessage mqttMessage = new MqttMessage(data);
            mqttMessage.setQos(2);
            mqttClient.publish(topic, mqttMessage);
            
        } catch (MqttException me) {
            try {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PublicadorLocalMQTT.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(me + " ERROR 8");
                mqttClient.connect(mqttConnectOptions);
            } catch (MqttException ex) {
                Logger.getLogger(PublicadorLocalMQTT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
