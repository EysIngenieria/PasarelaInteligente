/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author DesarrolloJC
 */
public class GenerarClave {

    public String Generar(String estacion) {
        try {
            String clavePrivada =  "./Certificado/"+estacion + ".rsa.der";
            
            File doc = new File(clavePrivada);
            String proyecto = "smart-helios-cgtm-qa";
            DateTime now = new DateTime();
            JwtBuilder jwtBuilder = Jwts.builder().setIssuedAt(now.toDate()).setExpiration(now.plusDays(1).toDate()).setAudience(proyecto);
            byte[] keyBytes = Files.readAllBytes(Paths.get(doc.getCanonicalPath()));
            
            
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            
            KeyFactory kf = KeyFactory.getInstance("RSA");
            
            String token = jwtBuilder.signWith(SignatureAlgorithm.RS256, kf.generatePrivate(spec)).compact();
            
            
            return token;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.toString();
            Logger.getLogger(GenerarClave.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
