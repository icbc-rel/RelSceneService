package com.icbc.rel.hefei.util;


public class EnumUtil {

	public enum sceneType{
		lottery("ת�̳齱"),order("���ϵ��"),asks("���ڹ���"),meeting("��������");
		private String sceneName;

		sceneType(String sceneName){
			this.sceneName=sceneName;
		}
		/*
		 * ��ȡ��������
		 */
		public String getSceneName() {
			 return sceneName;
		}
	}

	/*
	 * ���ݳ������ƻ�ȡ��������
	 */
	public static String getSceneType(String sceneName) {
		for(sceneType item:sceneType.values()) {
			if(sceneName.equals(item.getSceneName())) {
				return item.name();
			}
		}
		return "";
	}
	/*
	 * ���ݳ���ֵ��ȡ��������
	 */
	public static String getSceneName(String value) {
		for(sceneType item:sceneType.values()) {
			if(value.equals(item.name())) {
				return item.getSceneName();
			}
		}
		return "";
	}

}
