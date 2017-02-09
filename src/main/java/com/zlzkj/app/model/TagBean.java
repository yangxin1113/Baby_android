package com.zlzkj.app.model;

import com.zlzkj.core.sql.Row;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */
public class TagBean {
    List<Row> listAllTags;
    List<Row> listTagsByUser;
    List<Tag> listTagsByUser1;

    public List<Tag> getListTagsByUser1() {
        return listTagsByUser1;
    }

    public void setListTagsByUser1(List<Tag> listTagsByUser1) {
        this.listTagsByUser1 = listTagsByUser1;
    }

    public List<Row> getListAllTags() {
        return listAllTags;
    }

    public void setListAllTags(List<Row> listAllTags) {
        this.listAllTags = listAllTags;
    }

    public List<Row> getListTagsByUser() {
        return listTagsByUser;
    }

    public void setListTagsByUser(List<Row> listTagsByUser) {
        this.listTagsByUser = listTagsByUser;
    }
}
