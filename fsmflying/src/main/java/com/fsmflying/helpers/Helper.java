package com.fsmflying.helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Helper {

	public static void writeLog(String msg) {
		System.out.println(msg);
	}

	public static void printIntBinaryString(int value, boolean showTitle,
			boolean showHexTitle) {
		StringBuilder builder = new StringBuilder(Integer.toBinaryString(value));
		if (builder.length() < 32) {
			int insertLen = 32 - builder.length();
			for (int i = 0; i < insertLen; i++)
				builder.insert(0, '0');
		}
		for (int i = 0; i < 7; i++)
			builder.insert(28 - 4 * i, ',');
		String binaryString = builder.toString();
		String title = null;
		if (showTitle) {
			if (showHexTitle) {
				builder.replace(0, builder.length(), "");
				builder.append(Integer.toHexString(value));
				if (builder.length() < 8) {
					int insertLen = 8 - builder.length();
					for (int i = 0; i < insertLen; i++)
						builder.insert(0, '0');
				}
				title = "0x" + builder.toString();
			} else
				title = value + "";
		}
		if (showTitle)
			writeLog(title + ":" + binaryString);
		else
			writeLog(binaryString);
	}

	private static HashMap<String, SimpleDateFormat> dataFormatMap = null;

	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	public static String formatDate(Date date, String formatStr) {
		if (date == null || formatStr == null)
			return "null";
		if (dataFormatMap == null)
			dataFormatMap = new HashMap<String, SimpleDateFormat>();
		if (!dataFormatMap.containsKey(formatStr)) {
			dataFormatMap.put(formatStr, new SimpleDateFormat(formatStr));
		}
		return dataFormatMap.get(formatStr).format(date);
	}
	
	public static String formatDateNow(String formatStr)
	{
		return formatDate(java.util.GregorianCalendar.getInstance().getTime(),formatStr);
	}

	// private static SimpleDateFormat dateFormat = null;
	public static Date parseDate(String source) throws ParseException {
		SimpleDateFormat format = null;
		Date value = null;
		if (source == null)
			return null;
		if (dataFormatMap == null)
			dataFormatMap = new HashMap<String, SimpleDateFormat>();
		String key = null;
		source = source.trim();
		char seperator_ch = '\0';
		char[] chars = new char[] { '-', '/', '.', ' ', '*' };
		if (source.length() < 8)
			return null;
		for (int i = 0; i < chars.length; i++) {
			if (source.charAt(2) == source.charAt(5)
					&& source.charAt(2) == chars[i]) {
				seperator_ch = source.charAt(2);
				break;
			} else if (source.charAt(4) == source.charAt(7)
					&& source.charAt(4) == chars[i]) {
				seperator_ch = source.charAt(4);
				break;
			}
		}
		
		if (seperator_ch == '\0') {
			char c = 0;
			if (source.charAt(2) == source.charAt(5))	
				c = source.charAt(2);
			else if (source.charAt(4) == source.charAt(7))
				c = source.charAt(4);
			if(c!=0 && (c<'0'||c>'9')) seperator_ch=c;
		}
		if (seperator_ch != '\0')
			chars[0] = seperator_ch;
		String suffix = null;
		int dateLen = 10;
		// System.out.println(source.matches("^\\d{8}.*"));
		if (source.matches("^\\d{8}.*"))
			dateLen = 8;
		if (source.length() == dateLen)
			suffix = "";
		else if (source.length() == dateLen + 3)
			suffix = " HH";
		else if (source.length() == dateLen + 6)
			suffix = " HH:mm";
		else if (source.length() == dateLen + 9)
			suffix = " HH:mm:ss";
		else if (source.length() == dateLen + 13)
			suffix = " HH:mm:ss.SSS";
		// System.out.println("suffix:"+suffix);
		if (dateLen == 8) {
			// 19840923
			if (Integer.parseInt(source.substring(4, 6)) <= 12
					&& Integer.parseInt(source.length() == 8 ? source
							.substring(6) : source.substring(6, 8)) <= 31) {
				key = "yyyyMMdd" + suffix;
			}
			// 23091984
			else if (Integer.parseInt(source.substring(2, 4)) <= 12
					&& Integer.parseInt(source.substring(0, 2)) <= 31) {
				key = "ddMMyyyy" + suffix;
			}
			// 09231984
			else if (Integer.parseInt(source.substring(2, 4)) <= 31
					&& Integer.parseInt(source.substring(0, 2)) <= 12) {
				key = "MMddyyyy" + suffix;
			}

		} else {
			for (int i = 0; i < chars.length; i++) {
//				System.out.println(seperator_ch);
				if (seperator_ch != '\0' && seperator_ch != chars[i])
					continue;
				char ch = chars[i];
				if (source.indexOf(ch) > -1 && source.indexOf(ch) < 8) {
					if (source.charAt(4) == ch && source.charAt(7) == ch) {
						key = "yyyy" + ch + "MM" + ch + "dd" + suffix;
						break;
					} else if (source.charAt(2) == chars[i]
							&& source.charAt(5) == ch) {
						key = "dd" + ch + "MM" + ch + "yyyy" + suffix;
						break;
					}
				}
			}
		}

//		System.out.println(key);
		if (key != null && !dataFormatMap.containsKey(key)) {
			dataFormatMap.put(key, new SimpleDateFormat(key));
		}

		if (key != null)
			format = dataFormatMap.get(key);
		if (format != null)
			return format.parse(source);
		return value;
	}

	/**
	 * 返回指定整数mod的指定二进制位的值为1(返回true)，还是0(返回false)
	 * 
	 * @param mod
	 * @param index
	 * @return
	 */
	public static boolean getFlagBit(int mod, int index) {
		if (index < 0 || index > 31)
			throw new IllegalArgumentException();
		int getBitValue = 0x01 << index;
		return (mod & getBitValue) != 0;
	}

	/**
	 * 设置指定整数mod的二进制标志，注意只有返回值是修改过的，
	 * 
	 * @param mod
	 *            　指定要修改的整数值，调用后，此值不会发生变化，返回值就是修改后的值
	 * @param index
	 *            指定要修改哪一位二进制标志
	 * @param trueOrFalse
	 *            　将指定的二进制位修改为此0或1，则指定此参数true，如果要修改为0，则指定此参数false
	 * @return 修改过后的值
	 */
	public static int setFlagBit(int mod, int index, boolean trueOrFalse) {
		if (index < 0 || index > 31)
			throw new IllegalArgumentException();
		int bitValue = 0x01 << index;
		if (trueOrFalse/* && !getFlagBit(mod, index) */) {
			mod = mod | bitValue;
		} else if (!trueOrFalse/* && getFlagBit(mod, index) */) {
			// mod = mod & (0xffffffff ^ bitValue);
			mod = mod & (~(0x01 << index));
		}
		return mod;
	}

	/**
	 * 根据指定整数的32位二进制位,返回一个boolean数组，值为0的二进制位则返回false，值为1的位则返回true
	 * 
	 * @param mod
	 *            指定整数
	 * @return boolean[32]
	 */
	public static boolean[] getFlagBits(int mod) {
		// boolean[] flags = new boolean[32];
		// for (int i = 0; i < 32; i++) {
		// flags[i] = (mod & (0x01 << i)) != 0;
		// }
		// return flags;
		return getFlagBits(mod, 0, 31);
	}

	public static boolean[] getFlagBits(int mod, int startBit, int endBit) {
		boolean[] flags = new boolean[endBit - startBit + 1];
		for (int i = startBit; i <= endBit; i++) {
			flags[i] = (mod & (0x01 << i)) != 0;
		}
		return flags;
	}

	public static int getFlagValue(int mod, int startBit, int endBit) {
		if (startBit < 0 || startBit > 31 || endBit < 0 || endBit > 31
				|| endBit < startBit)
			throw new IllegalArgumentException();
		int bitValue = 0;
		for (int i = startBit; i <= endBit; i++)
			bitValue = bitValue | (0x01 << i);
		return (bitValue & mod) >> startBit;
	}

	/**
	 * 
	 * @param mod
	 * @param startBit
	 * @param endBit
	 * @param value
	 * @return
	 */
	public static int setFlagBits(int mod, int startBit, int endBit, int value) {
		int newValue = mod;
		if (startBit < 0 || startBit > 31 || endBit < 0 || endBit > 31
				|| endBit < startBit
				|| (value >= (0x01) << (endBit - startBit + 1)) || value < 0)
			throw new IllegalArgumentException();
		for (int i = startBit; i <= endBit; i++)
			newValue = setFlagBit(newValue, i, getFlagBit(value, i - startBit));
		return newValue;
	}

	/**
	 * 
	 * @param mod
	 * @param startBit
	 * @param endBit
	 * @param value
	 * @return
	 */
	public static int setFlagBits(int mod, int startBit, int endBit,
			boolean[] values) {

		if (startBit < 0 || startBit > 31 || endBit < 0 || endBit > 31
				|| endBit <= startBit)
			throw new IllegalArgumentException(
					"the startBit and endBit must be between 0 and 31, and endBit >= startBit");
		if (endBit - startBit + 1 > values.length)
			throw new IllegalArgumentException(
					"The length of values must be greater than (endBit-startBit+1),not match!");
		int newValue = mod;
		for (int i = startBit; i <= endBit; i++)
			newValue = setFlagBit(newValue, i, values[i - startBit]);
		return newValue;
	}

	/**
	 * 打印出指定整数的所有二进制标志位
	 * 
	 * @param mod
	 *            　一个整数
	 * @param allowln
	 *            　是否允许换行
	 */
	public static void printFlag(int mod, boolean allowln, boolean onlyTrueBit) {
		boolean[] flags = getFlagBits(mod);
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < 32; i++) {
			flags[i] = (mod & (0x01 << i)) != 0;
			if (allowln && (i % 5 == 0))
				builder.append("\n ");
			if (onlyTrueBit && !flags[i])
				continue;
			else
				builder.append(i + ":" + flags[i] + (i < 31 ? "," : ""));
		}
		builder.append((allowln ? "\n" : "") + "]");
		writeLog(builder.toString());
	}

	/**
	 * 对字符串md5加密
	 *
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
		String result = null;
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			result = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// throw new SpeedException("MD5加密出现错误");
			e.printStackTrace();
		}
		return result;
	}

}
