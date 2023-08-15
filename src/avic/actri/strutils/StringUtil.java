/**
 * @copyright actri.avic
 */
package avic.actri.strutils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * �ַ���������
 * 
 * @author tdan 2010-11-06
 * 
 */
public class StringUtil {

	/**
	 * ��\n֮��ĵ������ַ���
	 */
	public final static String RE_NO_LF = ".*";

	/**
	 * �����ַ���
	 */
	public final static String RE_ANY = "[\\s\\S]*";

	/**
	 * �����ɴ�Сд��ĸ�����֡�_��ɡ�
	 */
	public final static String RE_NAME = "^\\w+$";

	/**
	 * ·���ɴ�Сд��ĸ�����֡�_������\\��ɡ�
	 */
	public final static String RE_PATH = "([A-z]:)?(\\\\[A-z0-9_.-]+)+[A-z]$";

	/**
	 * ��������ɴ�Сд��ĸ�����֣��м���,��ɡ�
	 */

	public final static String RE_NAMES = "^[A-Za-z0-9,]+$";

	/**
	 * ����ֵ��ʮ�����ַ������� -9527������ʮ�������ַ�������0xffff9527
	 */
	public final static String RE_INT = "[+-]{0,1}[0-9]+|[+-]{0,1}0x[0-9a-fA-F]+";

	/**
	 * ȫ�����ַ���
	 */
	public final static String RE_NUMBER = "^[0-9]*$";

	/**
	 * ����Nλ����
	 */
	public final static String RE_N_NUMBER = "^\\d{n,}";

	/**
	 * M��Nλ����
	 */
	public final static String RE_MN_NUMBER = "^\\d{n,m}";

	/**
	 * ������
	 */
	public final static String RE_BINARY = "^[\\-]?[01]+||[\\-]?[01]+[\\.][01]+$";

	/**
	 * �˽���
	 */
	public final static String RE_OCTONARY = "^[\\-]?[0-7]+||[\\-]?[0-7]+[\\.][0-7]+$";

	/**
	 * ʮ����
	 */
	public final static String RE_DECIMAL = "^[\\-]?[1-9]\\d*||[\\-]?\\d+[\\.]\\d+||[0]$";

	/**
	 * ʮ�����ƣ���0xͷ��
	 */
	public final static String RE_HEX = "^[\\-]?[0-9a-fA-F]+||[\\-]?[0-9a-fA-F]+[\\.][0-9a-fA-F]+$";

	/**
	 * ���ֺ�Ӣ�Ĵ�Сд
	 */
	public final static String RE_EN_NUMBER = "^[A-Za-z0-9]+$";// ����"^[A-Za-z0-9]{4-40}$"

	/**
	 * Ӣ�Ĵ�Сд
	 */
	public final static String RE_EN = "^[A-Za-z]+$";

	/**
	 * �»��ߡ����ֺ�Ӣ�Ĵ�Сд
	 */
	public final static String RE_EN_NUMBER_UNDERLINE = "^\\w+$";// ����"^\\w{3,20}"

	/**
	 * ����
	 */
	public final static String RE_CR = "\\n\\s*\\r";

	/**
	 * ����
	 */
	public final static String RE_CN = "^[\\u4e00-\\u9fa5]{0,}$";

	/**
	 * ������ֵĺϷ��ԡ�
	 * 
	 * @param name
	 * @return
	 */
	public static boolean validateName(String name) {
		// ���ȼ��
		if (name == null || name.trim().length() == 0) {
			return false;
		}

		return name.matches(RE_NAME);
	}

	/**
	 * ������ֵĳ����Ƿ񳬹�32λ��
	 * 
	 * @param name
	 * @return
	 */
	public static boolean validateNameLength(String name) {
		// ���ȼ��
		if (name.trim().length() > 32) {
			return false;
		}

		return true;
	}

	/**
	 * ���·���ĺϷ��ԡ�
	 * 
	 * @param path
	 * @return
	 */
	public static boolean validatePath(String path) {
		// ���ȼ��
		if (path == null || path.trim().length() == 0) {
			return false;
		}
		return path.matches(RE_PATH);
	}

	/**
	 * ��֤�����ַ����Ƿ�Ϸ�������ֵ
	 * 
	 * @param value
	 *            ʮ�����ַ������� -9527������ʮ�������ַ�������0xffff9527
	 * @return true��false
	 */
	public static boolean validateInteger(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		return value.matches(RE_INT);
	}

	/**
	 * ��֤ȫ�����ַ���
	 * 
	 * @param value
	 *            ����֤�ַ���
	 * @return true��false
	 */
	public static boolean isNumber(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		return value.matches(RE_NUMBER);
	}

	/**
	 * ��֤ʮ��������
	 * 
	 * @param value
	 *            ����֤�ַ���
	 * @return true��false
	 */
	public static boolean isDecimal(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		return value.matches(RE_DECIMAL);
	}

	/**
	 * ��֤ʮ����������
	 * 
	 * @param value
	 *            ����֤�ַ���
	 * @return true��false
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
	 * �滻�ַ���
	 * 
	 * @param src
	 *            ĸ��
	 * @param find
	 *            ģʽ��
	 * @param replacement
	 *            �滻��
	 * @return �滻����ַ���
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
	 * ��ָ���ַ�������ָ���ļ���
	 * <p>
	 * �����{@code RandomAccessFile#writeUTF(String)}����д�볬������65535�ַ��������⡣
	 * </p>
	 * 
	 * @param targetFile
	 *            ��д����ļ�
	 * @param str
	 *            �ַ��������ȿ��Դ���65535
	 * @throws IOException
	 *             ��дĿ���ļ��쳣
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
	 * ���ַ�����ϳ��ַ���
	 * 
	 * @param list
	 *            �ַ�����
	 * @param spr
	 *            �ָ���
	 * @return �ϳ��ַ���
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
	 * ���ַ�����ϳ��ַ���
	 * 
	 * @param list
	 *            �ַ�����
	 * @param spr
	 *            �ָ���
	 * @return �ϳ��ַ���
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
	 * �ж��ַ������Ƿ���ȡ�
	 * 
	 * @param list1
	 *            ������1
	 * @param list2
	 *            ������2
	 * @return ��ȷ���true������ȷ���false
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
