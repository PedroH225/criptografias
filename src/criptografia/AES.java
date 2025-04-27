package criptografia;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AES {	
	public static void main(String[] args) throws Exception {
		 String mensagem = "Olá, boa tarde";

	        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
	        keyGen.init(128);
	        SecretKey chave = keyGen.generateKey();

	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, chave);
	        byte[] criptografado = cipher.doFinal(mensagem.getBytes());

	        System.out.println("Mensagem Criptografada: " + Base64.getEncoder().encodeToString(criptografado));

	        cipher.init(Cipher.DECRYPT_MODE, chave);
	        byte[] descriptografado = cipher.doFinal(criptografado);

	        System.out.println("Mensagem Original: " + new String(descriptografado));
	}
}
