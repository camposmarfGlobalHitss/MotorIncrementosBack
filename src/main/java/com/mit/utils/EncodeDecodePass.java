package com.mit.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



@Component
public class EncodeDecodePass {
	
	private String LLAVE = "MotorIncrementos2021";
	
	

	
	 // Clave de encriptación / desencriptación
    public SecretKeySpec CrearClave(String llave) {
        try {
            byte[] cadena = llave.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena, 16);
            return new SecretKeySpec(cadena, "AES");
        } catch (Exception e) {
            return null;
        }

    }

    // Encriptar
    public String Encriptar(String encriptar) {
     
        try {
        SecretKeySpec secretKeySpec = CrearClave(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            
            byte [] cadena = encriptar.getBytes(StandardCharsets.UTF_8);
            byte [] encriptada = cipher.doFinal(cadena);
            return Base64.encodeBase64String(encriptada);
            
            
            
        } catch (Exception e) {
            return "";
        }
    }

    // Des-encriptación
     public String desencriptar(String desencriptar) {
     
        try {
            SecretKeySpec secretKeySpec = CrearClave(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            
            byte [] cadena = Base64.decodeBase64(desencriptar);
            byte [] desencriptacioon = cipher.doFinal(cadena);
            return new String(desencriptacioon);
            
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
