/**
 * @copyright actri.avic
 */
package avic.actri.strutils;

import java.nio.charset.Charset;

/**
 * Ascii和Unicode字符串的转换方法
 * 
 * @author tdan 2010-12-06
 */
public class AsciiAndUnicode {
	private AsciiAndUnicode() {
	}

	/**
	 * 从Ascii获得Unicode字符串
	 * 
	 * @param asciiStr
	 * @return
	 */
	public static String getUnicode(String asciiStr) {
		assert asciiStr != null;
		StringBuffer buffer = new StringBuffer();
		char[] chrs = asciiStr.toCharArray();
		for (int i = 0; i < chrs.length; i++) {
			byte[] byts = Character.toString(chrs[i]).getBytes(
					Charset.forName("UTF-16BE"));
			if (byts[0] != 0) {
				buffer.append("/u");
				String str1 = Integer.toHexString(byts[0]);
				if (str1.length() == 1) {
					buffer.append('0');
					buffer.append(str1);
				} else if (str1.length() == 2) {
					buffer.append(str1);
				} else {
					buffer.append(str1.substring(str1.length() - 2));
				}

				String str2 = Integer.toHexString(byts[1]);
				if (str2.length() == 1) {
					buffer.append('0');
					buffer.append(str2);
				} else if (str2.length() == 2) {
					buffer.append(str2);
				} else {
					buffer.append(str2.substring(str2.length() - 2));
				}
			} else {
				buffer.append(chrs[i]);
			}
		}
		return buffer.toString();
	}

	/**
	 * 从Unicode获得Ascii字符串
	 * 
	 * @param unicodeStr
	 * @return
	 */
	public static String getAscii(String unicodeStr) {
		assert unicodeStr != null;
		StringBuffer buffer = new StringBuffer();
		char[] chrs = unicodeStr.toCharArray();
		for (int i = 0; i < chrs.length; i++) {
			if (chrs[i] == '/' && i < (chrs.length - 1) && chrs[i + 1] == 'u') {
				String str = new String(chrs, i + 2, 4);
				int value = Integer.parseInt(str, 16);
				buffer.append((char) value);
				i += 5;
			} else {
				buffer.append(chrs[i]);
			}
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		String msg = "调试";//
		String unicode = getUnicode(msg);
		String ascii = getAscii(unicode);
		System.out.println(unicode);
		System.out.println(ascii);
	}
}