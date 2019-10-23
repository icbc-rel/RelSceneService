package com.icbc.rel.hefei.util.RSA;

import org.apache.log4j.Logger;

/**
 * ������ȡsha1
 *	���� plainField������
 * 	��� cipherField:����
 *	���أ�������0���쳣��-1
 */
public class EncodeSHA1 {
	private static final Logger logger = Logger.getLogger(EncodeSHA1.class);
   public static String getAbstract(String plainField) {

		try {
			logger.info("SHA1 before encrypt:"+plainField);

			String cipherField = ThreeDESClass.shaHex(plainField.getBytes("GBK"));

			logger.info("SHA1 after encrypt:"+cipherField);
			if(cipherField == null||cipherField.equalsIgnoreCase(""))
				throw new Exception();
			return cipherField;
		} catch (Exception e) {
			return "";
		}
   }
}
