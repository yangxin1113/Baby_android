package com.zlzkj.app.mapper;

import com.zlzkj.app.model.BabyInfo;

public interface BabyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BabyInfo record);

    int insertSelective(BabyInfo record);

    BabyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BabyInfo record);

    int updateByPrimaryKey(BabyInfo record);
}