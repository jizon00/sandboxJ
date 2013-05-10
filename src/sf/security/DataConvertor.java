package sf.security;

final public class DataConvertor {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static byte[] hexStringToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}

		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	public static String byteArrayToHex(byte[] ba) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < ba.length; i++) {
			sb.append(byteToHex(ba[i]));
		}
		return sb.toString();
	}

	public static String byteToHex(byte b) {
		char[] buf = { HEX_DIGITS[(b >>> 4) & 0x0F], HEX_DIGITS[b & 0x0F] };
		return new String(buf);
	}
}
