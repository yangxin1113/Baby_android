package com.zlzkj.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.zlzkj.app.mapper.UserMapper;
import com.zlzkj.app.model.*;
import com.zlzkj.app.utils.AjaxResult.AjaxResult;
import com.zlzkj.app.utils.AjaxResult.ResultCode;
import com.zlzkj.app.utils.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zlzkj.core.mybatis.SqlRunner;
import com.zlzkj.core.sql.Row;
import com.zlzkj.core.sql.SQLBuilder;

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


    public AjaxResult login(String username, String password){
        //判断用户是否存在
        if (isExit(username)){
            List<Row> userList = new ArrayList<Row>();
            String sql = "";
            //手机用户
            if(Validator.isMobile(username)){
                sql = SQLBuilder.getSQLBuilder(User.class).fields("username,phone,sex,pwd").join(Userinfo.class,"User.id=Userinfo.user_id").where("phone="+username).selectSql();
            }else { //用户名用户
                sql = SQLBuilder.getSQLBuilder(User.class).fields("username,phone,sex,pwd").join(Userinfo.class,"User.id=Userinfo.user_id").where("username="+username).selectSql();
            }
            userList = sqlRunner.select(sql);
            if(userList.size()>0 && userList.get(0).get("pwd").equals(password)){
                //登录成功
                return AjaxResult.getOK(ResultCode.SUCCESS, "登陆成功", userList.get(0));
            }else {
                //密码错误
                return  AjaxResult.getError(ResultCode.InfoException,"密码错误", "");
            }

        }else {
            return AjaxResult.getError(ResultCode.InfoException,"用户不存在", "");
        }
    }


    public AjaxResult getTags(String userid){
        List<Row> listAllTags = new ArrayList<Row>();
        List<Row> listTagsByUser = new ArrayList<Row>();
        List< Tag> listTagsByUser1 = new ArrayList< Tag>();
        TagBean tagBean = new TagBean();
        String sqlAllTags = SQLBuilder.getSQLBuilder(Tag.class).fields("id,tag").selectSql();
        String sqlTagsByUser = SQLBuilder.getSQLBuilder(Tagsuser.class).fields("tags").where("user_id ="+userid).selectSql();
        listAllTags = sqlRunner.select(sqlAllTags);
        listTagsByUser = sqlRunner.select(sqlTagsByUser);
        String[] tags = listTagsByUser.get(0).get("tags").toString().split("@");
        System.out.print(listTagsByUser.get(0));
        System.out.print(tags);
        HashMap<String,Object> values = new HashMap<String,Object>();
        for(int i =0; i< tags.length; i++){
            String[] value = tags[i].split("#");
            Tag tag= new Tag();
            tag.setId(Integer.valueOf(value[0]));
            tag.setTag(value[1]);
            listTagsByUser1.add(tag);
        }
        tagBean.setListAllTags(listAllTags);
        tagBean.setListTagsByUser1(listTagsByUser1);
        return AjaxResult.getOK(ResultCode.SUCCESS, "获取数据成功", tagBean);

    }


    public AjaxResult updateTags(String userid, String[] tags){
        Row row = new Row();
        row.put("user_id", userid);
        row.put("tags", tags.toString());
        String updatetags = SQLBuilder.getSQLBuilder(Tagsuser.class).fields("tags").where("user_id ="+userid).updateSql(row);
        return AjaxResult.getOK(ResultCode.SUCCESS, "获取数据成功", updatetags);

    }

    public boolean isExit(String username) {
        boolean flag = false;
        String sql = "";
        if(Validator.isMobile(username)){
            sql = SQLBuilder.getSQLBuilder(User.class).fields("username,phone,sex,pwd").join(Userinfo.class,"User.id=Userinfo.user_id").where("phone="+username).selectSql();
        }else {
            sql = SQLBuilder.getSQLBuilder(User.class).fields("username,phone,sex,pwd").join(Userinfo.class,"User.id=Userinfo.user_id").where("username="+username).selectSql();
        }
        List<Row> userList = sqlRunner.select(sql);
        if(userList.size()>0){
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }
}
