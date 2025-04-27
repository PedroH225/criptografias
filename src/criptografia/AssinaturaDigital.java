package criptografia;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Base64;

public class AssinaturaDigital {
	public static void main(String[] args) throws Exception {
        String mensagem = "Mensagem a ser criptografada";

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair parChaves = keyGen.generateKeyPair();

        Signature assinatura = Signature.getInstance("SHA256withRSA");
        assinatura.initSign(parChaves.getPrivate());
        assinatura.update(mensagem.getBytes());
        byte[] assinaturaBytes = assinatura.sign();

        System.out.println("Assinatura: " + Base64.getEncoder().encodeToString(assinaturaBytes));

        Signature verificador = Signature.getInstance("SHA256withRSA");
        verificador.initVerify(parChaves.getPublic());
        verificador.update(mensagem.getBytes());
        boolean valido = verificador.verify(assinaturaBytes);

        System.out.println("Assinatura válida? " + valido);
    }
}
