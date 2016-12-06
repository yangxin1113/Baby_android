package com.zlzkj.app.service;

import java.util.List;

import com.zlzkj.app.mapper.UserMapper;
import com.zlzkj.app.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zlzkj.core.mybatis.SqlRunner;
import com.zlzkj.core.sql.Row;
import com.zlzkj.core.sql.SQLBuilder;
import com.zlzkj.core.util.Fn;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlRunner sqlRunner;

    public Integer delete(Integer id){
        return userMapper.deleteByPrimaryKey(id);
    }

    public Integer update(User user){
        return userMapper.updateByPrimaryKey(user);
    }

    public void save(User user) {

        /*ntity.setLoginPass(DigestUtils.md5Hex(entity.getLoginPass()));
        entity.setAddTime(Fn.time());
        entity.setLoginCount(0);
        entity.setLastLoginIp("127.0.0.1");
        entity.setLastLoginTime(0);
        entity.setIsDisabled((byte) 0);*/

        userMapper.insert(user);
    }

    public List<User> findAll() {
        return userMapper.selectAll();
    }

    public User findById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    public List<Row> findBySQL(){
        //userMapper.selectByPrimaryKey(1);
        //System.out.print(userMapper.selectByPrimaryKey(1).toString());
        String sql = SQLBuilder.getSQLBuilder(User.class).fields("id,username").selectSql();
        System.out.print(sqlRunner.select(sql,1).get(0)+"zzzz");
        return sqlRunner.select(sql,1);
    }
}
