package com.icbc.rel.hefei.dao;

import com.icbc.rel.hefei.entity.SysMessageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysMessageInfoDao {

    List<SysMessageInfo> queryInfo(@Param("activityUid") String activityUid, @Param("start") int start, @Param("limit") int limit);

    void insert(SysMessageInfo info);

    List<Integer> queryInfoNum(String activityUid);
}
