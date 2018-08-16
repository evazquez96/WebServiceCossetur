/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosettur.helpers;

/**
 *
 * @author evazquez
 */
public class Security {
    
    public static String encriptarMD5(String md5){
    
       String encriptada=null; 
       try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
           }
            System.out.println(sb.toString());
            encriptada=sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
            /**
             * Se Imprime el trazado de pila, en caso de que se 
             * lanze una exepción.
             */
        }finally{
            return encriptada;
       }
    
    }
    
}
