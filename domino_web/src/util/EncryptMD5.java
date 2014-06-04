package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptMD5 {
	
	public String md5(String s){  
        MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        BigInteger hash = new BigInteger(1, md.digest(s.getBytes()));  
        return hash.toString(16);  
    }  
	
//	public static void main(String[] args) {  
//        
//        String senha = "teste";  
//        String saida = "Entrada: " + senha + "\nSenha com MD5: " + md5(senha);  
//        System.out.println(saida);
//    }


}
