package com.javaguides.javaswing.login;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class Hasher {
    public static String getHash(byte[] inputBytes,String algorithm){
        String hashValue ="";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        }catch (Exception e){

        }
        return hashValue;
    }
}
