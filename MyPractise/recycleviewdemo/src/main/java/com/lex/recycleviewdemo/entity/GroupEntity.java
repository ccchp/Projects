package com.lex.recycleviewdemo.entity;

import java.util.List;

/**
 * Created by Lex lex on 2017/4/5.
 */

public class GroupEntity {

    private String groupname;
    private String groupChildName;
    private List<ItemEntity> itemList;

    public List<ItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemEntity> itemList) {
        this.itemList = itemList;
    }

    public String getGroupChildName() {
        return groupChildName;
    }

    public void setGroupChildName(String groupChildName) {
        this.groupChildName = groupChildName;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
}
