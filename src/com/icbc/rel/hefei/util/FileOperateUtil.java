package com.icbc.rel.hefei.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/*
 * �ļ���������������д�ļ�
 * save()	д�ļ�
 */

public class FileOperateUtil {

	/**
	 * �������ݵ��ļ�
	 * @param path  	�����ļ���·��
	 * @param content   �����ļ�������
	 * @param isAppend  �Ƿ���׷�ӵȷ�ʽ����
	 * @return
	 */
	public static boolean save(String path, String content, boolean isAppend) {
        File file=new File(path);
        try {
        	if(!file.exists())
                file.createNewFile();
            FileOutputStream out=new FileOutputStream(file, isAppend); //���׷�ӷ�ʽ��true
            StringBuffer sb=new StringBuffer();
            sb.append(content);
            out.write(sb.toString().getBytes("utf-8"));//ע����Ҫת����Ӧ���ַ���
            out.close();
        } catch (Exception e) {
        	return false;
        }
		return true;
	}

	public static String read(String path) {
		File file=new File(path);
		String content = "";
        try {
        	if(!file.exists())
                return content;
        	BufferedReader reader = null;
        	reader = new BufferedReader(new FileReader(file));
        	content = reader.readLine();
            reader.close();
        } catch (Exception e) {
        	return content;
        }
		return content;
	}
}
