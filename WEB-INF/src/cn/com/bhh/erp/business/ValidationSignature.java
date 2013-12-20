package cn.com.bhh.erp.business;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ValidationSignature {
	/***
	 * byte to hex string
	 * 
	 * @param ib
	 * @return
	 */
	public static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0f];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	/***
	 * md5 of string
	 * 
	 * @param data
	 * @return
	 */
	public static String MD5(String data) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(data.getBytes());
			byte[] bytes = md5.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes) {
				sb.append(byteToHexString(b));
			}
			return sb.toString();
			// return (new sun.misc.BASE64Encoder()).encode(bytes);
		} catch (Exception e) {
			return null;
		}

	}

	public static String HashUrl(String url) {
		long h = 0L;
		for (char ch : url.toCharArray()) {
			h = h * 127 + ch + 987654321;
			h = h & 0xffffffffffffL;
		}
		return String.valueOf(h);
	}

	public static String signatureHeader(String data, String secretkey) {
		try {
			byte[] keyBytes = secretkey.getBytes();
			SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			String base64 = (new sun.misc.BASE64Encoder()).encode(rawHmac);
			String sig = base64.substring(5, 15);
			return sig;
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (InvalidKeyException e) {
			return null;
		}
	}

	/***
	 * 加密
	 * 
	 * @return
	 */
	public static String Encryption(String data) {
		String code = signatureHeader(data, "desion@beijing2004");
		if(code == null){
			return null;
		}
		return code;
	}

	public static void main(String[] args) {
		String code = Encryption("102rootroot");
		System.out.println(code);

	}

}
