package com.icbc.rel.hefei.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * ����û�ip������
 * ���ü��������������������5�����ڣ��ӿ�/ҳ��/��̬��Դ�ķ��ʴ���������20�򶪵���������Ӧ������Ҫ��ʱ�Ƴ�5����ļ�¼��
 */
public class  IplimitUtil {


	private static Map<String,List<Long>> accessDatas=new ConcurrentHashMap<>();
	private static long timeWindowMillis=5*1000;//���ü��5���ڵķ��ʴ���
	private static int permits=20;//���ʴ�������
	public static void start() {
		Runnable runnable=new Runnable() {
	        @Override
	        public void run() {
	          long currentTime = System.currentTimeMillis();
              List < Long > accessPoints = null;
	          Iterator<String> idsIterator = accessDatas.keySet().iterator();
	          //���ÿ����ֵ�ԣ��Ƴ����ڵ�����
	          while (idsIterator.hasNext()) {
	        	String key=idsIterator.next();
	            accessPoints = accessDatas.get(key);
	            removeExpirePoints(accessPoints, currentTime);
	            List < Long > points = accessDatas.get(key);
                if (points.isEmpty()) {
                    points = null;
                    accessDatas.remove(key);
                }
	        }
	    }

	};
	    //������ʱ����1���Ƶ�ʣ�
		ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable,1000, 1000, TimeUnit.MILLISECONDS);
	}
	//�Ƴ����ڵļ�¼
	private  static void removeExpirePoints(List<Long> ponits,long currentTimeMillis){
        if(ponits == null || ponits.isEmpty()){
            return ;
        }
        Iterator<Long> pointsIterator = ponits.iterator();
        //�Ƴ�ʱ�䳬����Χ�ļ�¼
        while (pointsIterator.hasNext()) {
            if(pointsIterator.next().compareTo(currentTimeMillis - timeWindowMillis) <= 0){
                pointsIterator.remove();
            }else{
                break;
            }
        }
   }

	/**
	 * ����Ƶ�ʼ��
	 * @param identification �û���ʶ(ip or sessionId)
	 * @param uri
	 * @return
	*/
	public  static boolean requestFrequencyCheck(String ip, String uri) {
	    boolean result = false;
	    String identification=ip+"_"+uri;
	    long currentTime = System.currentTimeMillis();
	    List < Long > accessPoints = accessDatas.get(identification);
	    try {
	    //���Ƴ�ʧЧ�ļ�¼
	    removeExpirePoints(accessPoints, currentTime);
	    //���жϵ�ǰ��¼�����Ƿ���
	    accessPoints = accessDatas.get(identification);
	    if (accessPoints == null) {
	            accessPoints = new Vector<>(permits);
	            accessDatas.put(identification, accessPoints);
	    }
	    if (accessPoints.size() < permits) {
	            result = true;
	    } else {
	           result=false;
	    }
	    return result;
	    } finally {
	        accessPoints.add(currentTime);
	    }
	}

}
