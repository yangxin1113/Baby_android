package com.zlzkj.app.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zlzkj.app.mapper.TagsuserMapper;
import com.zlzkj.app.model.*;
import com.zlzkj.app.utils.AjaxResult.AjaxResult;
import com.zlzkj.app.utils.AjaxResult.ResultCode;
import com.zlzkj.app.utils.common.TimeUtils;
import com.zlzkj.core.mybatis.SqlRunner;
import com.zlzkj.core.sql.Row;
import com.zlzkj.core.sql.SQLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class TagsService {

    @Autowired
    private TagsuserMapper tagsuserMapper;

    @Autowired
    private SqlRunner sqlRunner;


    public Integer delete(Integer id){
        return tagsuserMapper.deleteByPrimaryKey(id);
    }

    public Integer update(Tagsuser tagsuser){
        return tagsuserMapper.updateByPrimaryKey(tagsuser);
    }

    public void save(Tagsuser tagsuser) {
        tagsuserMapper.insert(tagsuser);
    }

    public List<Tagsuser> findAll() {
        return tagsuserMapper.selectAll();
    }

    public Tagsuser findById(Integer id){
        return tagsuserMapper.selectByPrimaryKey(id);
    }

    public List<Row> findBySQL(){
        //userMapper.selectByPrimaryKey(1);
        //System.out.print(userMapper.selectByPrimaryKey(1).toString());
        String sql = SQLBuilder.getSQLBuilder(User.class).fields("id,username").selectSql();
        System.out.print(sqlRunner.select(sql,1).get(0)+"zzzz");
        return sqlRunner.select(sql,1);
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
        System.out.println(listTagsByUser.get(0));
        System.out.println(tags);
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


    public AjaxResult updateTags(String userid, String tags){
        JSONArray jsonArray = JSON.parseArray(tags);
        StringBuffer  sb = new StringBuffer ();
        for(int i=0; i<jsonArray.size(); i++){
            JSONObject jsonObject = (JSONObject) JSON.parse(jsonArray.getString(i));
            String id = jsonObject.get("id").toString();
            String tag = jsonObject.get("tag").toString();
            if(i == jsonArray.size()-1){
                sb.append(id+"#"+tag);
            }else {
                sb.append(id+"#"+tag+"@");
            }
        }
        System.out.println("tags:"+sb);

        Tagsuser tagsuser = new Tagsuser();
        tagsuser.setUserId(Integer.valueOf(userid));
        System.out.println("tags:"+sb.toString());
        tagsuser.setTags(sb.toString());
        tagsuser.setCreatetime(Long.valueOf(TimeUtils.date2TimeStamp(new Date(),"yyyyMMddHHmmss")));
        int flag = update(tagsuser);
        if(flag ==1){
            AjaxResult.getOK(ResultCode.SUCCESS, "获取数据成功","");
        }else {
            return  AjaxResult.getError(ResultCode.SystemException,"服务器异常", "");
        }
        return  AjaxResult.getError(ResultCode.SystemException,"服务器异常", "");
    }
}
