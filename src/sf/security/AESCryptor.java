package sf.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCryptor implements Cryptor {
	private final static String DEFAULT_CHARSET = "UTF-8";
	private String charSet;
	private String key;
	
	public AESCryptor(String key) {
		init(key, DEFAULT_CHARSET);
	}

	public AESCryptor(String key, String charSet) {
		init(key, charSet);
	}
	
	private void init(String key, String charSet) {
		if(charSet == null || charSet.equals("")) 
			this.charSet = DEFAULT_CHARSET;
		else 
			this.charSet = charSet;

		this.key = key;
	}

	@Override
	public byte[] encrypt(byte[] src) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(charSet), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] ba = cipher.doFinal(src);
		return ba;
	}

	@Override
	public byte[] decrypt(byte[] src) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(charSet), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] ba = cipher.doFinal(src);
		return ba;
	}
	

	/**
	 * AES 방식의 암호화
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@Override
	public String encode(String message) throws Exception {
		byte[] ba = message.getBytes(charSet);
		byte[] en = encrypt(ba);
		return DataConvertor.byteArrayToHex(en);
	}

	/**
	 * AES 방식의 복호화
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@Override
	public String decode(String encrypted) throws Exception {
		byte[] ba = DataConvertor.hexStringToByteArray(encrypted);
		byte[] de = decrypt(ba);
		String originalString = new String(de,charSet);
		return originalString;
	}

	public static void main(String[] args) {
		String text1 = "강환기";
		String text2 = "서울특별시 성북구 화랑로48길16(석관동10) 두산아파트 102동 1002호";
		String text3 = "11290-124334";

		try {
			Cryptor aes = new AESCryptor("6c70a0690dd7d923", "UTF-8");

			String encode1 = aes.encode(text1);
			String encode2 = aes.encode(text2);
			String encode3 = aes.encode(text3);
			System.out.println("text1   : " + text1);
			System.out.println("text2   : " + text2);
			System.out.println("text3   : " + text3);
			System.out.println("encode1 : " + encode1);
			System.out.println("encode2 : " + encode2);
			System.out.println("encode3 : " + encode3);
			System.out.println("size    : " + (encode1.length() + encode2.length() + encode3.length()));

			String decode1 = aes.decode(encode1);
			String decode2 = aes.decode(encode2);
			String decode3 = aes.decode(encode3);
			System.out.println("decode1 : " + decode1);
			System.out.println("decode2 : " + decode2);
			System.out.println("decode3 : " + decode3);
			System.out.println("size    : " + (decode1.length() + decode2.length() + decode3.length()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
