package com.zlzkj.app.mapper;

import com.zlzkj.app.model.Tagsuser;
import com.zlzkj.app.model.User;

import java.util.List;

public interface TagsuserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Tagsuser record);

    int insertSelective(Tagsuser record);

    Tagsuser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Tagsuser record);

    int updateByPrimaryKey(Tagsuser record);

    List<Tagsuser> selectAll();

}