package sf.security;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * 
 * RSA 암호화
 * 
 * @author kangki
 * @since 2013. 4. 17.
 * @version 1.0
 * @see RSACryptor
 * @history
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *  수정일                  수정자              수정내용
 *  -------       --------    ---------------------------
 *  2013. 4. 17.  kangki  내용쓰기
 * 
 * </pre>
 * 
 */
public class RSACryptor {

	public KeyPair generateKeyPair() throws Exception {
		KeyPair keyPair = RSAFactory.getKeyPair();
		return keyPair;
	}

	public PublicKey getPublicKey(String keyhexcode) throws Exception {
		byte[] 		keycode = DataConvertor.hexStringToByteArray(keyhexcode);
		PublicKey 	pk 		= getPublicKeyToByte(keycode);
		return pk;
	}

	public String getPublicKeyhexcode(PublicKey key) {
		byte[] pk 	= key.getEncoded();
		String hex 	= DataConvertor.byteArrayToHex(pk);
		return hex;
	}

	public String encode(PublicKey key, String text) throws Exception {
		byte[] data		= text.getBytes();
		byte[] encode 	= encodeToByte(key, data);
		String hex 		= DataConvertor.byteArrayToHex(encode);
		return hex;
	}

	public String decode(PrivateKey key, String hex) throws Exception {
		byte[] decode 	= DataConvertor.hexStringToByteArray(hex);
		byte[] data		= decodeToByte(key, decode);
		String text		= new String(data);
		return text;
	}

	private PublicKey getPublicKeyToByte(byte[] keycode) throws Exception {
		X509EncodedKeySpec 	x509 	= new X509EncodedKeySpec(keycode);
		KeyFactory 			kf 		= RSAFactory.getKeyFactory();
		PublicKey 			pk 		= kf.generatePublic(x509);
		return pk;
	}

	private byte[] encodeToByte(PublicKey key, byte[] src) throws Exception {
		return ciphering(Cipher.ENCRYPT_MODE, key, src);
	}

	private byte[] decodeToByte(PrivateKey key, byte[] src) throws Exception {
		return ciphering(Cipher.DECRYPT_MODE, key, src);
	}

	private byte[] ciphering(int mode, Key key, byte[] szData) throws Exception {
		Cipher cipher = RSAFactory.getCipher();
		cipher.init(mode, key);
		return cipher.doFinal(szData);
	}

	static final class RSAFactory {
		static final String SECURITY_FORMAT = "RSA";

		static final KeyPair getKeyPair() throws Exception {
			return KeyPairGenerator.getInstance(SECURITY_FORMAT).generateKeyPair();
		}

		static final KeyFactory getKeyFactory() throws Exception {
			return KeyFactory.getInstance(SECURITY_FORMAT);
		}

		static final Cipher getCipher() throws Exception {
			return Cipher.getInstance(SECURITY_FORMAT);
		}
	}

	public static void main(String[] args) {
		String text = "강환기#@#서울특별시 성북구 화랑로48길16(석관동10) 두산아파트 102동 1002호#@#11290-124334";
		System.out.println("text : " + text);

		try {
			RSACryptor ecmSecurity = new RSACryptor();
			KeyPair 	keyPair 	= ecmSecurity.generateKeyPair(); 	// 1. keyPair 생성
			PublicKey 	publicKey 	= keyPair.getPublic();				// 2. public key , public keycode생성
			PrivateKey 	privateKey 	= keyPair.getPrivate();				// 3. private key

			String 		pkhexcode 	= ecmSecurity.getPublicKeyhexcode(publicKey);	// 4. public keycode 발급
			PublicKey 	pk 			= ecmSecurity.getPublicKey(pkhexcode);			// 5. public key 복원

			System.out.println("pkhexcode : " + pkhexcode);
			
			// 6. encoding...
			String encodeText = ecmSecurity.encode(publicKey, text);
			System.out.println("encode(1) : " + encodeText);
			
			String encodeText2 = ecmSecurity.encode(pk, text);
			System.out.println("encode(2) : " + encodeText2);

			// 9. decoding...
			String decodeText = ecmSecurity.decode(privateKey, encodeText);
			System.out.println("decode(1) : " + decodeText);
			
			String decodeText2 = ecmSecurity.decode(privateKey, encodeText2);
			System.out.println("decode(2) : " + decodeText2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
