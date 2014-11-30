package src.john01dav.votifiertester.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class EncryptionHandler {
    private PublicKey publicKey;
    private Cipher cipher;

    public EncryptionHandler(String publicKeyString){
        try {
            //convert the Base64 encoded string into a usable PublicKey
            byte[] publicKey = DatatypeConverter.parseBase64Binary(publicKeyString);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKey);
            this.publicKey = keyFactory.generatePublic(publicKeySpec);

            //create a cipher object to handle encryption
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
        }catch(NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException e){
            e.printStackTrace();
        }
    }

    public byte[] encrypt(byte[] input){
        try {
            return cipher.doFinal(input);
        }catch(IllegalBlockSizeException | BadPaddingException e){
            e.printStackTrace();
            return new byte[0];
        }
    }

}
