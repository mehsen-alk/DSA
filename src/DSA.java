import java.security.*;

public class DSA {
    private final KeyPair keyPair;
    private final Signature signature;

    DSA() throws NoSuchAlgorithmException{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        // initialize key size
        keyPairGenerator.initialize(2048);

        // generate the key pair
        keyPair = keyPairGenerator.generateKeyPair();

        signature = Signature.getInstance("SHA256withDSA");
    }

    public byte[] privateKey() {return keyPair.getPrivate().getEncoded();}
    public byte[] publicKey() {return keyPair.getPublic().getEncoded();}

    public byte[] sign(byte[] message) throws SignatureException, InvalidKeyException {
        signature.initSign(keyPair.getPrivate());
        signature.update(message);
        return signature.sign();
    }

    public boolean verify(byte[] message ,byte[] messageSignature) throws SignatureException, InvalidKeyException {
        signature.initVerify(keyPair.getPublic());
        signature.update(message);
        return signature.verify(messageSignature);
    }
}
