package com.icbc.rel.hefei.util.RSA;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

import com.icbc.crypto.utils.SHA1;
import com.icbc.crypto.utils.TripleDesCryptVarKey2;


public class ThreeDESClass {

	//�ַ�����
		public final static String ENCODING = "GBK";

		/**
		 * �ַ�������
		 * @param plainText ����
		 * @return cipherText ����
		 * @throws UnsupportedEncodingException
		 */
		public static byte[] encrypt(byte[] plainText, byte[] cipherKey) throws UnsupportedEncodingException {
			byte[] cipherText = new byte[plainText.length];

			byte[] asciiCipherKey = TripleDesCryptVarKey2.Ascii2Text(cipherKey).getBytes(ENCODING);
			int ret = TripleDesCryptVarKey2.TripleDesCFB0(plainText, plainText.length, cipherText, 0, asciiCipherKey);
			return cipherText;
		}

		/**
		 * �ַ�������
		 * @param cipherText ����
		 * @param cipherKey ��Կ
		 * @return plainText ����
		 * @throws UnsupportedEncodingException
		 */
		public static byte[] decrypt(byte[] cipherText, byte[] cipherKey) throws UnsupportedEncodingException {
			byte[] out = new byte[cipherText.length];
			byte[] asciiCipherKey = TripleDesCryptVarKey2.Ascii2Text(cipherKey).getBytes(ENCODING);
			int ret = TripleDesCryptVarKey2.TripleDesCFB0(cipherText, cipherText.length, out, 1, asciiCipherKey);
			return out;
		}


		/**
		 * ������Կ
		 * @param size λ��
		 * @return bytes[] ��Կ
		 * @throws NoSuchAlgorithmException
		 */
		public static byte[] createCipher(int size) throws NoSuchAlgorithmException {
			KeyGenerator kg = KeyGenerator.getInstance("3DES");
			kg.init(size);
			return kg.generateKey().getEncoded();
		}

		/**
		 * ժҪ����
		 * @param data ��ժҪ����
		 * @return String ժҪ�ַ���
		 */
		public static String shaHex(byte[] data) {
			SHA1 sha = new SHA1();
			return sha.getDigestOfString(data);
		}

		/**
		 * ��֤
		 * @param data ��ժҪ����
		 * @param messageDigest ժҪ�ַ���
		 * @return  ��֤���
		 */
		public static boolean validata(byte[] data, String messageDigest) {
			SHA1 sha = new SHA1();
			return messageDigest.equals(sha.getDigestOfString(data));
		}

		public static String decode3Des(String str,String filePath) throws Exception{
			String decode3DesStr = "";
			//decode3DesStr = IcbcTripleDesDecrypt.TripleDesDecrypt(str, filePath);
			decode3DesStr =IEPA3DESUtil.decrypt3DES(str,filePath,1);
			return decode3DesStr;
		}


		public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

			String text = "mpid=10003508&timestamp=1431657053756&menuid=L0022";//"�����ַ���";
			String text2 = "�����ַ���1";

			byte[] cipherKey = createCipher(112);


		//	byte[] cipherKey =  new String("67890567890").getBytes();

			//String hexString = "oX"

			byte[] cipherText = encrypt(text.getBytes(), cipherKey);


			//String base64Text = Base64.icbcbase64encode(cipherText);

			//cipherText = Base64.icbcbase64decode(base64Text);


			System.out.println(new String(decrypt(cipherText, cipherKey)));

			SHA1 sha = new SHA1();

			String zhaiyao = shaHex(text.getBytes());
			System.out.println(zhaiyao);


			boolean ret = validata(text2.getBytes(), zhaiyao);

			//System.out.println(ret);
			//byte[] sha1 = sha.getDigestOfBytes(cipherText);
		}

}
