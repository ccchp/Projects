package com.lex.recycleviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lex.recycleviewdemo.entity.GroupEntity;
import com.lex.recycleviewdemo.entity.ItemEntity;

import java.util.List;

/**
 * Created by Lex lex on 2017/4/5.
 */

public class MyExpandListAdapter extends BaseExpandableListAdapter {

    List<GroupEntity> mList = null;
    LayoutInflater mLayoutInflater = null;

    public MyExpandListAdapter(Context context, List<GroupEntity> grouplist) {
        this.mList = grouplist;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public List<GroupEntity> getmList() {
        return mList;
    }

    public void setmList(List<GroupEntity> mList) {
        this.mList = mList;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).getItemList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getItemList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder mGroupHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.expandlist_group, null);
            mGroupHolder = new GroupHolder();
            mGroupHolder.mGroupName = (TextView) convertView.findViewById(R.id.expandlist_group_tv);
            mGroupHolder.mGroupChildName = (TextView) convertView.findViewById(R.id.expandlist_group_name_tv);
            convertView.setTag(mGroupHolder);
        }else{
            mGroupHolder = (GroupHolder) convertView.getTag();
        }

        GroupEntity entity = mList.get(groupPosition);
        mGroupHolder.mGroupName.setText(entity.getGroupname());
        mGroupHolder.mGroupChildName.setText(entity.getGroupChildName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder mItemHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.expandlist_item, null);
            mItemHolder = new ItemHolder();
            mItemHolder.mItemName = (TextView) convertView.findViewById(R.id.expandlist_item_tv);
            mItemHolder.mItemCompleteName = (TextView) convertView.findViewById(R.id.expandlist_item_complete_tv);
            mItemHolder.mItemUnCompleteName = (TextView) convertView.findViewById(R.id.expandlist_item_uncomplete_tv);
            convertView.setTag(mItemHolder);
        }else {
            mItemHolder = (ItemHolder) convertView.getTag();
        }

        ItemEntity entity = mList.get(groupPosition).getItemList().get(childPosition);
        mItemHolder.mItemName.setText(entity.getItemname());
        mItemHolder.mItemCompleteName.setText(3 + "完成");
        mItemHolder.mItemUnCompleteName.setText(2 + "未完成");

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static class GroupHolder{
        public TextView mGroupName;
        public TextView mGroupChildName;

    }

    public static class ItemHolder{
        public TextView mItemName;
        public TextView mItemCompleteName;
        public TextView mItemUnCompleteName;
    }
}
