package com.eduardo.sha256;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	/**
	 * * 利用java原生的类实现SHA256加密 这是一种hash算法。 不可以解密
   * @param str 加密后的报文
   * @return
   */
	public static String getSHA256(String str){
		MessageDigest messageDigest;
		String encodestr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes("UTF-8"));
			encodestr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodestr;
	}
	
	private static String byte2Hex(byte[] bytes){
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i=0;i<bytes.length;i++){
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length()==1){
				stringBuffer.append("0");
			}
		stringBuffer.append(temp);
	}
	return stringBuffer.toString();
	}
	
	public static void main(String[] args) {
		String SourceString=getSHA256("Hello there I loveyou");
		System.out.print(SourceString+"\n");
		String SourceStrin1g=getSHA256("Hello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffuHello there I lovsfsfsdfasdfeyo dsdffu");
		System.out.print(SourceStrin1g+"\n");
		
	}
}