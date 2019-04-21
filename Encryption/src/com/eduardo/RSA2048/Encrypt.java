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
    private static String src ="����һ����Ҫ�����ܵ����ݡ��������п��ŵ�";
    public static void main(String[] args) {
        jdkRSA();
    }

    public static void jdkRSA(){

        //1.��ʼ����Կ
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();  //�γ���Կ��,ÿ�ε���Կ�Զ��ǲ�һ���ġ�

            RSAPublicKey rSAPublicKey = (RSAPublicKey)keyPair.getPublic(); //�õ���Կ
            RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();   //�õ�˽Կ
            
           // System.out.println(rSAPublicKey);
            //System.out.println("��Կ\n"+new BASE64Encoder().encodeBuffer(rSAPublicKey.getEncoded()));
            System.out.println("��Կ\n"+Base64.encodeBase64String(rSAPublicKey.getEncoded()));
            
            String priStr= new BASE64Encoder().encodeBuffer(privateKey.getEncoded());
            System.out.print("˽Կ :\n"+ priStr);
            //System.out.println("˽Կ :\n"+Base64.encodeBase64String(private1.getEncoded()));

            
            
            

            System.out.print("������˽Կ���ܣ���Կ���ܹ���\n");
            //2.˽Կ����,��Կ����--����
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            KeyFactory instance = KeyFactory.getInstance("RSA");
            KeyFactory keyFactory = instance;

            PrivateKey privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey1);

            byte[] result = cipher.doFinal(src.getBytes());
            System.out.print("�������£�\n"+src);
            System.out.println("���ܺ����������\n:"+Base64.encodeBase64String(result));

            
            //3.˽Կ����,��Կ����---����
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rSAPublicKey.getEncoded());
            keyFactory=KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result=cipher.doFinal(result);
            
            System.out.println("\n\n\n\n\n");
            System.out.println("=======================================================");
            
            System.out.println("���ܺ�Ľ������:\n"+new String(result));


      
            
            System.out.print("�����ǹ�Կ���ܣ�˽Կ���ܹ���\n");
            
            //4.��Կ����,˽�ܽ���--����
            x509EncodedKeySpec =new X509EncodedKeySpec(rSAPublicKey.getEncoded());
            keyFactory=KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result=cipher.doFinal(src.getBytes());
            System.out.println("��Կ����,˽�ܽ���--����"+Base64.encodeBase64String(result));

            //5��Կ����,˽Կ����--����
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            keyFactory=KeyFactory.getInstance("RSA");
            privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result=cipher.doFinal(result);
            System.out.println("��Կ����,˽Կ����--����"+ new String(result));
           
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
