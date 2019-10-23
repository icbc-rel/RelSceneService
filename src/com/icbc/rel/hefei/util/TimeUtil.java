package com.icbc.rel.hefei.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ʱ�䴦������
 */
public class TimeUtil {

	/**
	 * ת10λʱ�����,ʱ��Ϊ��ǰʱ��
	 *
	 * @return long ����10λ
	 */
	public static long getTime(){
		Date date=new Date();
		long time = date.getTime();
		System.out.println("date.getTime():"+time);
		/*System.out.println("System.currentTimeMillis():"+System.currentTimeMillis());
		SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("df.format(date):"+df.format(date));
		*/
		System.out.println("date.getTime()/1000:"+time/1000);
		//return time;
		return time/1000;	//��ȥ����
	}

	/**
	 * ���� yyyy-MM-dd HH:mm:ss ��ʽʱ��
	 * @return String
	 */
	public static String getAlipayTime(){
		SimpleDateFormat dateformat2 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat2.format(new Date());
	}

	/**
	 * ����2000-02-16��ʽʱ�䣬���10�����ַ���
	 * @param times ����2000-02-16��ʽ
	 * @return long ����10λ
	 */
	public static long getTime(String times){
		 SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
	     Date date;
		 long time = 0;
		try {
			date = simpleDateFormat .parse(times);
			time = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 return time/1000;
	}


	/**
	 * ����0���ʱ���
	 * @return 10λ
	 */
	public static long day0(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis()/1000;
	}


	public static String[] dayByNum(int num){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String[] time = new  String[num];
		for(int i=0;i<num;i++){
			time[i] = sdf.format(new Date(day0()*1000 - (num - i - 1) * 60 * 1000));
		}
        return time;
	}


	/**
	 * ��ȡ����
	 *
	 * @param times  ʱ���
	 * @param format �����ָ�ʽ�ַ���
	 * @return �����ַ���
	 */
	public static String getDate(long times, String format) {
		times *= 1000;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date(times));
	}


	/**
	 * ��ʱ���������ת��Ϊ����ʹ���
	 * @return
	 */
	public static Map timr2data(int starttime,String tpye){
		Map map = new HashMap();
		if("day".equals(tpye) ){
			map.put("time_interval",3600);
			map.put("time_num",24);
		}else if("week".equals(tpye)){
			map.put("time_interval",86400);
			map.put("time_num",7);
		}else if("month".equals(tpye)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sd = sdf.format(new Date(Long.parseLong(starttime+"000"))); // ʱ���ת������
			int year = Integer.parseInt(sd.substring(0,4));
			int month = Integer.parseInt(sd.substring(5,7));
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR,year);
			cal.set(Calendar.MONTH,month-1);//7��
			int maxDate = cal.getActualMaximum(Calendar.DATE);
			map.put("time_interval",86400);
			map.put("time_num",maxDate);
		}else if("year".equals(tpye)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sd = sdf.format(new Date(Long.parseLong(starttime+"000"))); // ʱ���ת������
			int year = Integer.parseInt(sd.substring(0,4));
			int month = Integer.parseInt(sd.substring(5,7));
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR,year);
			List list = new ArrayList();
			for(int i=0;i<12;i++){
				cal.set(Calendar.MONTH,i);//��
				list.add(cal.getActualMaximum(Calendar.DAY_OF_MONTH)*86400);
			}
			map.put("time_interval",list);
			map.put("time_num",12);
		}
		return map;
	}


	public static void main(String[] arg ){
		 //day0();
	}



}
