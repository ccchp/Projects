package com.lex.accounting.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.lex.accounting.R;
import com.lex.accounting.adapter.MenuAdapter;
import com.lex.accounting.adapter.MenuAdapter.MenuListener;
import com.lex.accounting.entity.MenuItem;
import com.lex.accounting.slidemenu.MenuDrawer;

public class MainActivity extends Activity implements MenuListener{
	
	private MenuDrawer mDrawer;
    private MenuAdapter mAdapter;
    private ListView mList;
    private List<Object> items;
    
    private int mCurrentIndex = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY);

        items = new ArrayList<Object>();
        Resources res = getResources();
        items.add(new MenuItem(res.getString(R.string.title_expenditure), R.drawable.ic_launcher));
        items.add(new MenuItem(res.getString(R.string.title_income), R.drawable.ic_launcher));
//        items.add(new Category("Cat 1"));
        items.add(new MenuItem(res.getString(R.string.title_accout), R.drawable.ic_launcher));
        items.add(new MenuItem(res.getString(R.string.title_account_tranfer), R.drawable.ic_launcher));
//        items.add(new Category("Cat 2"));
        items.add(new MenuItem(res.getString(R.string.title_income_category), R.drawable.ic_launcher));
        items.add(new MenuItem(res.getString(R.string.title_expenditure_category), R.drawable.ic_launcher));
//        items.add(new Category("Cat 3"));
        items.add(new MenuItem(res.getString(R.string.title_borrowing_management), R.drawable.ic_launcher));
        items.add(new MenuItem(res.getString(R.string.title_loan_management), R.drawable.ic_launcher));
//        items.add(new Category("Cat 4"));
        items.add(new MenuItem(res.getString(R.string.title_flow_statement), R.drawable.ic_launcher));
        items.add(new MenuItem(res.getString(R.string.title_classification_statement), R.drawable.ic_launcher));
        items.add(new MenuItem(res.getString(R.string.title_personal_statement), R.drawable.ic_launcher));
        items.add(new MenuItem(res.getString(R.string.title_budgit_management), R.drawable.ic_launcher));
        items.add(new MenuItem(res.getString(R.string.title_system_setting), R.drawable.ic_launcher));

        LayoutInflater inflater = LayoutInflater.from(this);
        View menuView = inflater.inflate(R.layout.menu_layout, /*mDrawer*/null);
        mList = (ListView) menuView.findViewById(R.id.menu_list);
//        mList = new ListView(this);
        mAdapter = new MenuAdapter(this, items);
        mAdapter.setListener(this);
//        mAdapter.setActivePosition(mActivePosition);
        mList.setAdapter(mAdapter);
        mList.setSelection(mCurrentIndex);
        mDrawer.setMenuView(/*mList*/menuView);
        
        final TextView content = new TextView(this);
        final String str = "This is a sample of an overlayed left drawer.";
        content.setText(str);
        content.setGravity(Gravity.CENTER);
        mDrawer.setContentView(content);
//        mDrawer.setSlideDrawable(R.drawable.ic_drawer);
        mDrawer.setDrawerIndicatorEnabled(true);
        mDrawer.peekDrawer(1000, 0);
//        mDrawer.setActiveView(menuView, 0);
        mList.setOnItemClickListener(onItemClickListener);
        
	}
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			Log.i("lex", "onItemClick  position = " + position + "  id = " + id);
//			mActivePosition = position;
//	        mDrawer.setActiveView(view, position);
//			mAdapter.setActivePosition(position);
//			content.setText(str + "  " + position + "  " + id);
			if(mCurrentIndex != position){
				MenuItem obj = (MenuItem) items.get(position);
				setTitle(obj.getmTitle());
				mCurrentIndex = position;
				BaseView baseView = null;
				switch (position) {
				case 0:
				case 1:
					baseView = new ViewIncomeExpenditureManagement(MainActivity.this, position);
					break;
				case 2:
					baseView = new ViewAccount(MainActivity.this);
					break;
				case 4:
				case 5:
					baseView = new ViewIncomeExpenditureCategory(MainActivity.this, position);
					break;
				case 6:
				case 7:
					baseView = new ViewBorrowingLendingManagement(MainActivity.this);
					break;
				default:
					break;
				}
				if(baseView != null){
					mDrawer.setContentView(baseView.getView());
				}
			}
			mDrawer.closeMenu();
		}
	};

	@Override
	public void onBackPressed() {
		final int drawerState = mDrawer.getDrawerState();
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
        	mDrawer.closeMenu();
            return;
        }
		super.onBackPressed();
	}

	@Override
	public void onActiveViewChanged(View v) {
		// TODO Auto-generated method stub
//		mDrawer.setActiveView(v, mActivePosition);
	}
}
