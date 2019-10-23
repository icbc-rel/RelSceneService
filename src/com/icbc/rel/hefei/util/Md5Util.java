package com.icbc.rel.hefei.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;


public class Md5Util {
	private static final Logger logger = Logger.getLogger(Md5Util.class);

	/**
	 * ����MD5���м���
	 *
	 * @param str
	 *            �����ܵ��ַ���
	 * @return ���ܺ���ַ���
	 */

	public static String EncoderByMd5(String password) {

		try {
			// �õ�һ����ϢժҪ��
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// ��ÿһ��byte ��һ�������� 0xff;
			for (byte b : result) {
				// ������
				int number = b & 0xff;// ����
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}

			// ��׼��md5���ܺ�Ľ��
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("MD5���ܱ���",e);
			return "";
		}

	}
}
