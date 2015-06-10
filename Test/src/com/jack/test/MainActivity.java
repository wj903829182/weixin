package com.jack.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;

public class MainActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener{

	private ViewPager viewPager;
	private List<Fragment> listFragments=new ArrayList<Fragment>();
	private String [] titles=new String[]{
			"First Fragment!","Second Fragment!","Third Fragment!"
			,"Fourth Fragment!"
	};
	private FragmentPagerAdapter fpagerAdapter;
	
	private List<ChangeColorIconWithText> tabIndicators=new ArrayList<ChangeColorIconWithText>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setOverflowButtonAlways();
		//setDisplayShowHomeEnabled设置左上角图标是否显示，如果设成false，
		//则没有程序图标，仅仅就个标题，否则，显示应用程序图标
		getActionBar().setDisplayShowHomeEnabled(false);
		//初始view
		initView();
		//初始化数据
		initDatas();
		//设置适配器
		viewPager.setAdapter(fpagerAdapter);
		initEvent();
	}

	
	/*
	 * 初始所有事件
	 * */
	private void initEvent() {
		// TODO Auto-generated method stub
		viewPager.setOnPageChangeListener(this);
	}

	private void initDatas() {
		// TODO Auto-generated method stub
		//初始化fragment
		for(String title : titles){
			TabFragment tabFragment=new TabFragment();
			Bundle bundle=new Bundle();
			bundle.putString(TabFragment.TITLE, title);
			tabFragment.setArguments(bundle);
			listFragments.add(tabFragment);
		}
		//初始adapter
		fpagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return listFragments.size();
			}
			
			@Override
			public Fragment getItem(int position) {
				// TODO Auto-generated method stub
				return listFragments.get(position);
			}
		};
	}

	private void initView() {
		// TODO Auto-generated method stub
		viewPager=(ViewPager) findViewById(R.id.id_viewpager);
		
		ChangeColorIconWithText one=(ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
		ChangeColorIconWithText two=(ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
		ChangeColorIconWithText three=(ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
		ChangeColorIconWithText four=(ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
		tabIndicators.add(one);
		tabIndicators.add(two);
		tabIndicators.add(three);
		tabIndicators.add(four);
		
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		one.setIconAlpha(1.0f);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 * 使隐藏的图标显示
	 * */
	private void setOverflowButtonAlways(){
		try {
			ViewConfiguration viewconfig=ViewConfiguration.get(this);
			//利用反射
			Field menuKey=ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKey.setAccessible(true);
			menuKey.setBoolean(viewconfig, false);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 设置overflow里面的菜单显示图标
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		if(featureId == Window.FEATURE_ACTION_BAR && menu != null){
			if (menu.getClass().getSimpleName().equals("MenuBuilder")){
				try {
					//利用反射
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible",Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		onClickTab(view);
	}

	/***
	 * 点击Tab按钮
	 * @param view
	 */
	private void onClickTab(View view){
		resetOthersTab();
		switch(view.getId()){
		case R.id.id_indicator_one:
			tabIndicators.get(0).setIconAlpha(1.0f);
			viewPager.setCurrentItem(0, false);
			break;
		case R.id.id_indicator_two:
			tabIndicators.get(1).setIconAlpha(1.0f);
			viewPager.setCurrentItem(1, false);
			break;
		case R.id.id_indicator_three:
			tabIndicators.get(2).setIconAlpha(1.0f);
			viewPager.setCurrentItem(2, false);
			break;
		case R.id.id_indicator_four:
			tabIndicators.get(3).setIconAlpha(1.0f);
			viewPager.setCurrentItem(3, false);
			break;
		}
	}
	
	/*
	 * 重置其他的TabIndicator的颜色
	 * */
	private void resetOthersTab() {
		// TODO Auto-generated method stub
		for(int i=0;i<tabIndicators.size();i++){
			tabIndicators.get(i).setIconAlpha(0.0f);
		}
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub
		// Log.e("TAG", "position = " + position + " ,positionOffset =  "
				// + positionOffset);
		if(positionOffset>0){
			ChangeColorIconWithText left=tabIndicators.get(position);
			ChangeColorIconWithText right=tabIndicators.get(position+1);
			left.setIconAlpha(1-positionOffset);
			right.setIconAlpha(positionOffset);
		}
	}


	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
