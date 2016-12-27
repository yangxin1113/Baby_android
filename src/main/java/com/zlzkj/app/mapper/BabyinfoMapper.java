package com.zlzkj.app.mapper;

import com.zlzkj.app.model.Babyinfo;

public interface BabyinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Babyinfo record);

    int insertSelective(Babyinfo record);

    Babyinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Babyinfo record);

    int updateByPrimaryKey(Babyinfo record);
}