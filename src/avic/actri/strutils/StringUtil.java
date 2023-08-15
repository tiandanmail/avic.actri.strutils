/**
 * @copyright actri.avic
 */
package avic.actri.strutils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * 字符串功能类
 * 
 * @author tdan 2010-11-06
 * 
 */
public class StringUtil {

	/**
	 * 除\n之外的的任意字符。
	 */
	public final static String RE_NO_LF = ".*";

	/**
	 * 任意字符。
	 */
	public final static String RE_ANY = "[\\s\\S]*";

	/**
	 * 名字由大小写字母、数字、_组成。
	 */
	public final static String RE_NAME = "^\\w+$";

	/**
	 * 路径由大小写字母、数字、_、。、\\组成。
	 */
	public final static String RE_PATH = "([A-z]:)?(\\\\[A-z0-9_.-]+)+[A-z]$";

	/**
	 * 多个名字由大小写字母、数字，中间间隔,组成。
	 */

	public final static String RE_NAMES = "^[A-Za-z0-9,]+$";

	/**
	 * 整数值。十进制字符串，如 -9527；或者十六进制字符串，如0xffff9527
	 */
	public final static String RE_INT = "[+-]{0,1}[0-9]+|[+-]{0,1}0x[0-9a-fA-F]+";

	/**
	 * 全数字字符串
	 */
	public final static String RE_NUMBER = "^[0-9]*$";

	/**
	 * 至少N位数字
	 */
	public final static String RE_N_NUMBER = "^\\d{n,}";

	/**
	 * M到N位数字
	 */
	public final static String RE_MN_NUMBER = "^\\d{n,m}";

	/**
	 * 二进制
	 */
	public final static String RE_BINARY = "^[\\-]?[01]+||[\\-]?[01]+[\\.][01]+$";

	/**
	 * 八进制
	 */
	public final static String RE_OCTONARY = "^[\\-]?[0-7]+||[\\-]?[0-7]+[\\.][0-7]+$";

	/**
	 * 十进制
	 */
	public final static String RE_DECIMAL = "^[\\-]?[1-9]\\d*||[\\-]?\\d+[\\.]\\d+||[0]$";

	/**
	 * 十六进制，无0x头。
	 */
	public final static String RE_HEX = "^[\\-]?[0-9a-fA-F]+||[\\-]?[0-9a-fA-F]+[\\.][0-9a-fA-F]+$";

	/**
	 * 数字和英文大小写
	 */
	public final static String RE_EN_NUMBER = "^[A-Za-z0-9]+$";// 或者"^[A-Za-z0-9]{4-40}$"

	/**
	 * 英文大小写
	 */
	public final static String RE_EN = "^[A-Za-z]+$";

	/**
	 * 下划线、数字和英文大小写
	 */
	public final static String RE_EN_NUMBER_UNDERLINE = "^\\w+$";// 或者"^\\w{3,20}"

	/**
	 * 空行
	 */
	public final static String RE_CR = "\\n\\s*\\r";

	/**
	 * 汉字
	 */
	public final static String RE_CN = "^[\\u4e00-\\u9fa5]{0,}$";

	/**
	 * 检查名字的合法性。
	 * 
	 * @param name
	 * @return
	 */
	public static boolean validateName(String name) {
		// 长度检查
		if (name == null || name.trim().length() == 0) {
			return false;
		}

		return name.matches(RE_NAME);
	}

	/**
	 * 检查名字的长度是否超过32位。
	 * 
	 * @param name
	 * @return
	 */
	public static boolean validateNameLength(String name) {
		// 长度检查
		if (name.trim().length() > 32) {
			return false;
		}

		return true;
	}

	/**
	 * 检查路径的合法性。
	 * 
	 * @param path
	 * @return
	 */
	public static boolean validatePath(String path) {
		// 长度检查
		if (path == null || path.trim().length() == 0) {
			return false;
		}
		return path.matches(RE_PATH);
	}

	/**
	 * 验证给出字符串是否合法的整数值
	 * 
	 * @param value
	 *            十进制字符串，如 -9527；或者十六进制字符串，如0xffff9527
	 * @return true或false
	 */
	public static boolean validateInteger(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		return value.matches(RE_INT);
	}

	/**
	 * 验证全数字字符串
	 * 
	 * @param value
	 *            待验证字符串
	 * @return true或false
	 */
	public static boolean isNumber(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		return value.matches(RE_NUMBER);
	}

	/**
	 * 验证十进制数字
	 * 
	 * @param value
	 *            待验证字符串
	 * @return true或false
	 */
	public static boolean isDecimal(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		return value.matches(RE_DECIMAL);
	}

	/**
	 * 验证十六进制数字
	 * 
	 * @param value
	 *            待验证字符串
	 * @return true或false
	 */
	public static boolean isHex(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		return value.matches(RE_HEX);
	}

	public static void main(String[] args) {
		String s1 = "testaaabbb\n";
		boolean b = s1.matches(RE_ANY);
		System.out.println(b);
	}

	/**
	 * 替换字符串
	 * 
	 * @param src
	 *            母串
	 * @param find
	 *            模式串
	 * @param replacement
	 *            替换串
	 * @return 替换后的字符串
	 */
	public static final String replaceAll(String src, String find,
			String replacement) {
		assert src != null;
		assert find != null;
		assert replacement != null;
		final int len = src.length();
		final int findLen = find.length();

		int idx = src.indexOf(find);
		if (idx < 0) {
			return src;
		}

		StringBuffer buf = new StringBuffer();
		int beginIndex = 0;
		while (idx != -1 && idx < len) {
			buf.append(src.substring(beginIndex, idx));
			buf.append(replacement);

			beginIndex = idx + findLen;
			if (beginIndex < len) {
				idx = src.indexOf(find, beginIndex);
			} else {
				idx = -1;
			}
		}
		if (beginIndex < len) {
			buf.append(src.substring(beginIndex, (idx == -1 ? len : idx)));
		}
		return buf.toString();
	}

	/**
	 * 把指定字符串加入指定文件。
	 * <p>
	 * 解决了{@code RandomAccessFile#writeUTF(String)}不能写入超过长度65535字符串的问题。
	 * </p>
	 * 
	 * @param targetFile
	 *            待写入的文件
	 * @param str
	 *            字符串，长度可以大于65535
	 * @throws IOException
	 *             读写目标文件异常
	 */
	public static final void writeUTF(File targetFile, String str)
			throws IOException {
		int MAX = 21845;
		int index = 0;
		int len = str.length();
		RandomAccessFile raf = new RandomAccessFile(targetFile, "rwd");
		try {
			long seek = raf.length();
			raf.seek(seek);
			while (len > 0) {
				if (len > MAX) {
					String s = str.substring(index, index + MAX);
					raf.writeUTF(s);
					index += MAX;
					len -= MAX;
				} else {
					String s = str.substring(index);
					raf.writeUTF(s);
					len = 0;
				}
			}
		} finally {
			raf.close();
		}
	}

	/**
	 * 从字符串表合成字符串
	 * 
	 * @param list
	 *            字符串表
	 * @param spr
	 *            分隔符
	 * @return 合成字符串
	 */
	public static String toString(List<String> list, String spr) {
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			if (sb.length() != 0) {
				sb.append(spr);
			}
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 从字符串表合成字符串
	 * 
	 * @param list
	 *            字符串表
	 * @param spr
	 *            分隔符
	 * @return 合成字符串
	 */
	public static String toString(String[] list, String spr) {
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			if (sb.length() != 0) {
				sb.append(spr);
			}
			sb.append(s);
		}
		return sb.toString();
	}

	public static String toLinuxPath(String path) {
		return path.replace("\\", "/");
	}

	/**
	 * 判断字符串表是否相等。
	 * 
	 * @param list1
	 *            符串表1
	 * @param list2
	 *            符串表2
	 * @return 相等返回true，不相等返回false
	 */
	public boolean compare(List<String> list1, List<String> list2) {
		if (list1 == null && list2 == null) {
			return true;
		}
		if (list1 == null || list2 == null) {
			return false;
		}
		if (list1.size() != list2.size()) {
			return false;
		}
		for (String s : list1) {
			if (!list2.contains(s)) {
				return false;
			}
		}
		return true;
	}

	public static final String toHexString(String decimal) {
		long l = Long.parseLong(decimal);
		return "0x" + Long.toHexString(l);
	}
}
