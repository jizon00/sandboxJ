package sf.security;

/**
 * 데이터 암호화
 * 
 * @author kangki
 * @since 2013. 4. 23.
 * @version 1.0
 * @see Cryptor
 * @history
 * 
 * <pre>
 */
public interface Cryptor {
	/**
	 * 암호화
	 * @author kangki
	 * @see Cryptor.encrypt
	 * @param src 데이터
	 * @return byte[] 암호화한 데이터
	 * @throws Exception 
	 */
	byte[] encrypt(byte[] src) throws Exception;
	
	/**
	 * 복호화
	 * @author kangki
	 * @see Cryptor.decrypt
	 * @param src 암호화된 데이터
	 * @return byte[] 복호화한 데이터
	 * @throws Exception 
	 */
	byte[] decrypt(byte[] src) throws Exception;

	/**
	 * 암호화
	 * @author kangki
	 * @see Cryptor.encrypt
	 * @param src 데이터
	 * @return String 암호화한 데이터
	 * @throws Exception 
	 */
	String encode(String src) throws Exception;
	
	/**
	 * 복호화
	 * @author kangki
	 * @see Cryptor.decrypt
	 * @param src 암호화된 데이터
	 * @return String 복호화한 데이터
	 * @throws Exception 
	 */
	String decode(String src) throws Exception;

}
