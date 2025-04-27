package criptografia;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

import javax.crypto.Cipher;

public class Comunicacao {
	public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair bobChaves = keyGen.generateKeyPair();

        KeyPair aliceChaves = keyGen.generateKeyPair();

        String mensagem = "Oi Bob! Aqui é a Alice.";

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, bobChaves.getPublic());
        byte[] criptografado = cipher.doFinal(mensagem.getBytes());

        Signature assinatura = Signature.getInstance("SHA256withRSA");
        assinatura.initSign(aliceChaves.getPrivate());
        assinatura.update(mensagem.getBytes());
        byte[] assinaturaBytes = assinatura.sign();

        cipher.init(Cipher.DECRYPT_MODE, bobChaves.getPrivate());
        byte[] descriptografado = cipher.doFinal(criptografado);
        String mensagemRecebida = new String(descriptografado);

        Signature verificador = Signature.getInstance("SHA256withRSA");
        verificador.initVerify(aliceChaves.getPublic());
        verificador.update(mensagemRecebida.getBytes());
        boolean assinaturaValida = verificador.verify(assinaturaBytes);

        System.out.println("Mensagem Recebida: " + mensagemRecebida);
        System.out.println("Assinatura válida? " + assinaturaValida);
    }
}
