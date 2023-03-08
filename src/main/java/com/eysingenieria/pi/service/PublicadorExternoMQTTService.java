/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
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
import org.joda.time.DateTime;

/**
 *
 * @author DesarrolloJC
 */
public class PublicadorExternoMQTTService implements MqttCallback{
    private String servidorMQTT;
    private String proyecto;
    private String region;
    private String registro;
    private String dispositivo;
    private String topico;
    private String mqttClientId;
    private String clavePrivada;
    private boolean conectado;
     private String dato;
    private String topicoEntro;
    private boolean entroDato;
    private MqttConnectOptions mqttConnectOptions;
    private Properties sslClientProperties;
    private MqttClient mqttClient;

    public PublicadorExternoMQTTService(String clave, String dispositivo, String servidorMQTT, String proyecto, String region, String registro) {
        
        try {
            MemoryPersistence memoryPersistence = new MemoryPersistence();
            this.servidorMQTT = servidorMQTT;
            this.proyecto = proyecto;
            this.region = region;
            this.registro = registro;
            this.dispositivo = dispositivo;
            

            topico = "/devices/"+dispositivo+"/events";
            mqttClientId = String.format("projects/%s/locations/%s/registries/%s/devices/%s", proyecto, region, registro, dispositivo);
            
            mqttConnectOptions = new MqttConnectOptions();
            sslClientProperties = new Properties();
            sslClientProperties.setProperty("com.ibm.ssl.protocol", "TLSv1.2");
            mqttConnectOptions.setSSLProperties(sslClientProperties);
            mqttConnectOptions.setUserName("unused");
            mqttConnectOptions.setPassword(clave.toCharArray());
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(1000);
            mqttConnectOptions.setKeepAliveInterval(60);
            mqttClient = new MqttClient(servidorMQTT, mqttClientId, memoryPersistence);
            mqttClient.connect(mqttConnectOptions);
            
            
        } catch (MqttException ex) {
            Logger.getLogger(PublicadorExternoMQTTService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean conect(){
        return mqttClient.isConnected();
    }
    
    public boolean Publicar(byte[] contenido, String tipoTrama) {
        
        try {
            
            MqttMessage mqttMessage = new MqttMessage(contenido);
            mqttMessage.setQos(1);
            mqttClient.publish(topico, mqttMessage);


            return true;

        } catch (MqttException ex) {
            try {
                try {

                    Thread.sleep(5000);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(PublicadorExternoMQTTService.class.getName()).log(Level.SEVERE, null, ex1);
                }
                mqttClient.connect(mqttConnectOptions);
            } catch (MqttException ex1) {
                Logger.getLogger(PublicadorExternoMQTTService.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
    }
/*
    private String createJwtRsa(String projectId, String clavePrivada) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        DateTime now = new DateTime();
        JwtBuilder jwtBuilder = Jwts.builder().setIssuedAt(now.toDate()).setExpiration(now.plusMinutes(20).toDate()).setAudience(projectId);
        byte[] keyBytes = Files.readAllBytes(Paths.get(clavePrivada));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return jwtBuilder.signWith(SignatureAlgorithm.RS256, kf.generatePrivate(spec)).compact();
    }*/

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
        

        this.proyecto = proyecto;
        this.region = region;
        this.registro = registro;
        this.dispositivo = dispositivo;
        String[] topico = {String.format("/devices/%s/config", dispositivo), String.format("/devices/%s/commands/#", dispositivo)};
       

        try {
            
            mqttClient.setCallback(this);
            mqttClient.subscribe(topico);
            conectado = true;
            System.out.println("Conexion correcta" + " - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()));
        } catch (MqttException ex) {
            ex.printStackTrace();
            conectado = false;
        }

    }

    @Override
    public void connectionLost(Throwable arg0) {
        System.out.println("Conexion Perdida" + " - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()));
        conectado = false;
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        System.out.println("Entregado");
    }

    @Override
    public void messageArrived(String topico, MqttMessage message) {
        dato = message.toString();
        topicoEntro = topico;
        entroDato = true;
    }

    
}
