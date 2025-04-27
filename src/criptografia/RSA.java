package criptografia;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSA {
	public static void main(String[] args) throws Exception {
        String mensagem = "Mensagem confidencial";

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair parChaves = keyGen.generateKeyPair();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, parChaves.getPublic());
        byte[] criptografado = cipher.doFinal(mensagem.getBytes());

        System.out.println("Mensagem Criptografada: " + Base64.getEncoder().encodeToString(criptografado));

        cipher.init(Cipher.DECRYPT_MODE, parChaves.getPrivate());
        byte[] descriptografado = cipher.doFinal(criptografado);

        System.out.println("Mensagem Original: " + new String(descriptografado));
    }
}
