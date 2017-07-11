package com.lex.recycleviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.lex.recycleviewdemo.entity.GroupEntity;
import com.lex.recycleviewdemo.entity.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    NestedExpandListView mExpandableListView = null;
    MyExpandListAdapter mMyExpandListAdapter = null;
    private List<GroupEntity> grouplist;
    private int lastExpandPosition = 0;
    private View mCurrentGroupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mExpandableListView = (NestedExpandListView) findViewById(R.id.expandview);

        initData();
    }

    public void initData() {
        grouplist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GroupEntity g = new GroupEntity();
            List<ItemEntity> itemlist = new ArrayList<>();
            for (int j = 0; j < i+2; j++) {
                ItemEntity entity = new ItemEntity();
                entity.setItemname("第" + i + "级，第"+j+"项");
                itemlist.add(entity);
            }
            g.setGroupname("第"+i+"级");
            g.setGroupChildName("请选择");
            g.setItemList(itemlist);
            grouplist.add(g);
        }
        if(mMyExpandListAdapter == null){
            mMyExpandListAdapter = new MyExpandListAdapter(this, grouplist);
        }
        mMyExpandListAdapter.setmList(grouplist);
        mExpandableListView.setAdapter(mMyExpandListAdapter);
        mExpandableListView.expandGroup(0);

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                mCurrentGroupView = v;
                Snackbar.make(v, grouplist.get(groupPosition).getGroupname() + "  groupPosition = " + groupPosition,Snackbar.LENGTH_SHORT).show();
                return false;
            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandPosition != groupPosition) {
                    if(mExpandableListView.isGroupExpanded(lastExpandPosition)){
                        if(mExpandableListView.collapseGroup(lastExpandPosition)){
                            lastExpandPosition = groupPosition;
                        }
                    }
                }
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                GroupEntity groupEntity = grouplist.get(groupPosition);

                Snackbar.make(v, groupEntity.getItemList().get(childPosition).getItemname(), Snackbar.LENGTH_SHORT).show();

                View parentView = mMyExpandListAdapter.getGroupView(groupPosition, true, mCurrentGroupView, parent);
                MyExpandListAdapter.GroupHolder holder = (MyExpandListAdapter.GroupHolder) parentView.getTag();
                groupEntity.setGroupChildName(groupEntity.getItemList().get(childPosition).getItemname());
                holder.mGroupChildName.setText(groupEntity.getItemList().get(childPosition).getItemname());
                mMyExpandListAdapter.setmList(grouplist);
//                mMyExpandListAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
