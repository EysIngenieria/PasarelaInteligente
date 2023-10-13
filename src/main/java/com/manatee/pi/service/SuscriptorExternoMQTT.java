/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author DesarrolloJC
 */
public class SuscriptorExternoMQTT implements MqttCallback {

    private String dato;
    private String topicoEntro;
    private String clave;
    private boolean entroDato;
    private boolean conectado;

    String proyecto;
    String region;
    String registro;
    String dispositivo;
    String mqttServerAddress;
    String mqttClientId;

    MqttClient mqttClient;
    MemoryPersistence memoryPersistence;

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getTopicoEntro() {
        return topicoEntro;
    }

    public void setTopicoEntro(String topicoEntro) {
        this.topicoEntro = topicoEntro;
    }

    public boolean isEntroDato() {
        return entroDato;
    }

    public void setEntroDato(boolean entroDato) {
        this.entroDato = entroDato;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public void Suscribir(String clave,String dispositivo, String servidorMQTT, String proyecto, String region, String registro) {
        this.clave = clave;

        this.proyecto = proyecto;
        this.region = region;
        this.registro = registro;
        this.dispositivo = dispositivo;
        this.mqttServerAddress = servidorMQTT;
        String[] topico = {String.format("/devices/%s/config", dispositivo), String.format("/devices/%s/commands/#", dispositivo)};
        mqttClientId = String.format("projects/%s/locations/%s/registries/%s/devices/%s", proyecto, region, registro, dispositivo);
        memoryPersistence = new MemoryPersistence();

        try {
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            Properties sslClientProperties = new Properties();
            sslClientProperties.setProperty("com.ibm.ssl.protocol", "TLSv1.2");
            mqttConnectOptions.setSSLProperties(sslClientProperties);
            mqttConnectOptions.setUserName("unused");
            mqttConnectOptions.setPassword(clave.toCharArray());
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(2000);
            mqttConnectOptions.setKeepAliveInterval(60);
            mqttClient = new MqttClient(mqttServerAddress, mqttClientId, memoryPersistence);
            mqttClient.connect(mqttConnectOptions);
            mqttClient.setCallback(this);
            mqttClient.subscribe(topico);
            conectado = true;
            System.out.println("Conexion correcta" + " - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()));
        } catch (MqttException ex) {
            ex.printStackTrace();
            conectado = false;
            /*
            if (!cerrar) {
                Reconectar(clave);
            } else {
                try {
                    mqttClient.close(true);
                } catch (MqttException ex1) {
                    Logger.getLogger(SuscriptorExternoMQTT.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
             */

        }

    }

    @Override
    public void connectionLost(Throwable arg0) {
        System.out.println("Conexion Perdida" + " - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()));
        conectado = false;
       

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        //System.out.println("Entregado");
    }

    @Override
    public void messageArrived(String topico, MqttMessage message) {
        dato = message.toString();
        topicoEntro = topico;
        entroDato = true;
    }

    private void Reconectar(String clave) {
        try {
            Thread.sleep(10);
            Suscribir(clave,dispositivo, mqttServerAddress, proyecto, region, registro);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

}
