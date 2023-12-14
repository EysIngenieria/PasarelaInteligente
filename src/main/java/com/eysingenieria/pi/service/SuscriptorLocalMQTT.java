/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author DesarrolloJC
 */
public class SuscriptorLocalMQTT implements MqttCallback {

    private String data;
    private Boolean connected;
    private boolean messageArrived;
    private String messageTopicArrived;
    private String[] topic;
    private String broker;
    private String clientId;
    private MqttClient mqttClient;
    private MqttConnectOptions mqttConnectOptions;

    public SuscriptorLocalMQTT(String[] topic, String broker, String clientId) {
        this.topic = topic;
        this.broker = broker;
        this.clientId = clientId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isMessageArrived() {
        return messageArrived;
    }

    public void setMessageArrived(boolean messageArrived) {
        this.messageArrived = messageArrived;
    }

    public String getMessageTopicArrived() {
        return messageTopicArrived;
    }

    public void Subscribe() {
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        try {
            mqttClient = new MqttClient(broker, MqttClient.generateClientId(), memoryPersistence);
            mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);
            mqttClient.connect(mqttConnectOptions);
            mqttClient.setCallback(this);
            mqttClient.subscribe(topic);
            connected = true;
        } catch (MqttException me) {
            System.err.println(me+" Error 9");
            
        }
    }

    @Override
    public void connectionLost(Throwable arg0) {
        System.out.println("Conexion Perdida");
        connected = false;
        try {
            mqttClient.connect(mqttConnectOptions);
            mqttClient.subscribe(topic);
        } catch (MqttException ex) {
            Logger.getLogger(SuscriptorLocalMQTT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        System.out.println("Entregado");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        data = message.toString();
        messageTopicArrived = topic;
        messageArrived = true;
    }
}