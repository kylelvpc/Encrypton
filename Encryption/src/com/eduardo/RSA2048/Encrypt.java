package com.eduardo.RSA2048;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;

public class Encrypt {
    private static String src ="这是一段需要被加密的内容。比如银行卡号等";
    public static void main(String[] args) {
        jdkRSA();
    }

    public static void jdkRSA(){

        //1.初始化秘钥
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();  //形成密钥对,每次的密钥对都是不一样的。

            RSAPublicKey rSAPublicKey = (RSAPublicKey)keyPair.getPublic(); //得到公钥
            RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();   //得到私钥
            
           // System.out.println(rSAPublicKey);
            //System.out.println("公钥\n"+new BASE64Encoder().encodeBuffer(rSAPublicKey.getEncoded()));
            System.out.println("公钥\n"+Base64.encodeBase64String(rSAPublicKey.getEncoded()));
            
            String priStr= new BASE64Encoder().encodeBuffer(privateKey.getEncoded());
            System.out.print("私钥 :\n"+ priStr);
            //System.out.println("私钥 :\n"+Base64.encodeBase64String(private1.getEncoded()));

            
            
            

            System.out.print("以下是私钥加密，公钥解密过程\n");
            //2.私钥加密,公钥解密--加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            KeyFactory instance = KeyFactory.getInstance("RSA");
            KeyFactory keyFactory = instance;

            PrivateKey privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey1);

            byte[] result = cipher.doFinal(src.getBytes());
            System.out.print("明文如下：\n"+src);
            System.out.println("加密后的密文如下\n:"+Base64.encodeBase64String(result));

            
            //3.私钥加密,公钥解密---解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rSAPublicKey.getEncoded());
            keyFactory=KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result=cipher.doFinal(result);
            
            System.out.println("\n\n\n\n\n");
            System.out.println("=======================================================");
            
            System.out.println("解密后的结果如下:\n"+new String(result));


      
            
            System.out.print("以下是公钥加密，私钥解密过程\n");
            
            //4.公钥加密,私密解密--加密
            x509EncodedKeySpec =new X509EncodedKeySpec(rSAPublicKey.getEncoded());
            keyFactory=KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result=cipher.doFinal(src.getBytes());
            System.out.println("公钥加密,私密解密--加密"+Base64.encodeBase64String(result));

            //5公钥加密,私钥解密--解密
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            keyFactory=KeyFactory.getInstance("RSA");
            privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result=cipher.doFinal(result);
            System.out.println("公钥加密,私钥解密--解密"+ new String(result));
           
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
