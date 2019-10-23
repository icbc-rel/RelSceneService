package com.icbc.rel.hefei.util.RSA;

import java.util.Date;

public class IEPA3DESUtil {

	/***
	 * ����ԭ��,��Կ�ļ���,����3des���ܺ��base64�ִ�
	 * �ڲ����ܷ�ʽ��1--ECB;2--CBC;3--CFB;4--OFB
	 ***/
	public static String encrypt3DES(String old, String keyfileName, int method)
			throws Exception {
		byte[] abc = old.getBytes("UTF-8");
		byte[] abd = new byte[(abc.length / 8) * 8 + 8];

		/*
		 * in - byte[] ��Ҫ���ܵ����Ļ���ܵ����� len - int ��Ҫ���ܻ��߽��ܵ��ַ����ȣ��ó��ȱ���С���������in���ֽ���
		 * out - byte[] �ӽ��ܺ���ַ��� module - int �ӽ��ܵķ�ʽ��0���ܣ�1���� method -
		 * �ڲ����ܷ�ʽ��1--ECB;2--CBC;3--CFB;4--OFB
		 *  destype - �ڲ��㷨��ʽ��0 �ӼӼӣ�1 �ӽ��
		 * keyFile - String�ӽ��ܵķ�ʽ����Կ�ļ���·��
		 */
		int ret = com.icbc.crypto.utils.TripleDesCryptFileInputKey
				.IcbcTripleDes(abc, abc.length, abd, 0, method, 1, keyfileName);
		if (ret <= 0) {
			throw new Exception(new StringBuffer(
					"�ӽ��ܳ���,����շ���ֵ����ԭ��,IEPA3DESTool.Encrypt3DES����ֵ").append(ret)
					.toString());
		}
		return com.icbc.crypto.utils.Base64.icbcbase64encode(abd).trim();
	}

	/***
	 * ����ԭ��,��Կ�ļ���,����3des���ܺ��base64�ִ�
	 * �ڲ����ܷ�ʽ��1--ECB;2--CBC;3--CFB;4--OFB
	 ***/
	public static String encrypt3DESWithout8Bytes(String old, String keyfileName, int method)
			throws Exception {
		byte[] abc = old.getBytes();
		byte[] abd = new byte[abc.length];
		/*
		 * in - byte[] ��Ҫ���ܵ����Ļ���ܵ����� len - int ��Ҫ���ܻ��߽��ܵ��ַ����ȣ��ó��ȱ���С���������in���ֽ���
		 * out - byte[] �ӽ��ܺ���ַ��� module - int �ӽ��ܵķ�ʽ��0���ܣ�1���� method -
		 * �ڲ����ܷ�ʽ��1--ECB;2--CBC;3--CFB;4--OFB
		 *  destype - �ڲ��㷨��ʽ��0 �ӼӼӣ�1 �ӽ��
		 * keyFile - String�ӽ��ܵķ�ʽ����Կ�ļ���·��
		 */
		int ret = com.icbc.crypto.utils.TripleDesCryptFileInputKey
				.IcbcTripleDes(abc, abc.length, abd, 0, method, 1, keyfileName);
		if (ret <= 0) {
			throw new Exception(new StringBuffer(
					"�ӽ��ܳ���,����շ���ֵ����ԭ��,IEPA3DESTool.Encrypt3DES����ֵ").append(ret)
					.toString());
		}
		return com.icbc.crypto.utils.Base64.icbcbase64encode(abd).trim();
	}


    /***
     * ����3des���ܺ��base64�ִ�,��Կ�ļ���,����ԭ��
     * �ڲ����ܷ�ʽ��1--ECB;2--CBC;3--CFB;4--OFB
     ***/
    public static String decrypt3DES(String base64old, String keyfileName, int method)  throws Exception
    {
    	byte[] abc = com.icbc.crypto.utils.Base64.icbcbase64decode(base64old);
        byte[] abd = new byte[abc.length];
        /*
        in - byte[] ��Ҫ���ܵ����Ļ���ܵ�����
		len - int ��Ҫ���ܻ��߽��ܵ��ַ����ȣ��ó��ȱ���С���������in���ֽ���
		out - byte[] �ӽ��ܺ���ַ���
		module - int �ӽ��ܵķ�ʽ��0���ܣ�1����
		method - �ڲ����ܷ�ʽ��1--ECB;2--CBC;3--CFB;4--OFB
		destype - �ڲ��㷨��ʽ��0 �ӼӼӣ�1 �ӽ��
		keyFile - String�ӽ��ܵķ�ʽ����Կ�ļ���·��

       */
        int ret = com.icbc.crypto.utils.TripleDesCryptFileInputKey.IcbcTripleDes(abc, abc.length, abd, 1,  method, 1,keyfileName);
        if (ret <= 0)
        {
        	//������־
        	System.err.println("�ӽ��ܳ���,����շ���ֵ����ԭ��,IEPA3DESTool.Decrypt3DES����ֵ: " + String.valueOf(ret));

            throw new Exception(new StringBuffer("�ӽ��ܳ���,����շ���ֵ����ԭ��,IEPA3DES.Decrypt3DES����ֵ").append(ret).toString());
        }
        return new String(abd,"utf-8").trim();

    }

    public static void main(String[] args) {
		String mpid="10003508";
		String timestamp=String.valueOf(new Date().getTime());//new Calendar().getTimeInMillis());

		String menuid="L0022";
		String TranData = "mpid="+mpid+"&timestamp="+timestamp+"&menuid="+menuid;

		String keyfile="D:/workspace/ICBCMPServer/CONFIG/ebank_app/icbcinbs/mims/server/security/ICBC_EMALL_1_00000_3DES_16";
		String encTranData ="";
		try {
			encTranData= encrypt3DES(TranData,keyfile,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(TranData);
		System.out.println(encTranData);
	}
}
