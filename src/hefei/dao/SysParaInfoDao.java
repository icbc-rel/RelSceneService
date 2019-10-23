package com.icbc.rel.hefei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icbc.rel.hefei.entity.SysParaInfo;

public interface SysParaInfoDao {
	/*
	 * ��ѯ���в�������
	 */
	List<SysParaInfo> GetAllSysParaInfos();

	/*
	 * ����key��ѯ��������
	 */
	List<SysParaInfo> GetSysParaInfoByKey(int key);
	/*
	 * ���²���ֵ
	 */
	void UpdateSystemPara(@Param("key") int key, @Param("name") String name, @Param("value") String value);

}
