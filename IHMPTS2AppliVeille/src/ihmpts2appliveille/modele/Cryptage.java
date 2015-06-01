/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author x1QG1x
 */
public class Cryptage {
    public static String getEncodedPassword(String key) 
    { 
 
     byte[] uniqueKey = key.getBytes(); 
     byte[] hash = null; 
 
     try 
	 { 
        hash = MessageDigest.getInstance("MD5").digest(uniqueKey); //MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512
 
     } 
     catch (NoSuchAlgorithmException e) { 
        throw new Error("no MD5 support in this VM"); 
     }
     catch (Exception e) {
         System.err.println(e.getMessage());
     }
 
     StringBuilder hashString = new StringBuilder(); 
     for ( int i = 0; i < hash.length; ++i ) { 
        String hex = Integer.toHexString(hash[i]); 
        if ( hex.length() == 1 ) { 
         hashString.append('0'); 
         hashString.append(hex.charAt(hex.length()-1)); 
        } else { 
         hashString.append(hex.substring(hex.length()-2)); 
        } 
     } 
     return hashString.toString(); 
    }
}
