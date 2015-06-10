package com.jack.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabFragment extends Fragment {

	private String title="default";
	public static final String TITLE="title";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(getArguments()!=null){//判断是否为空
			title=getArguments().getString(TITLE);//得到从Activity传入的值
		}
		
		TextView tv=new TextView(getActivity());//创建TextView
		tv.setTextSize(20);//设置文本字体大小
		tv.setBackgroundColor(Color.parseColor("#ffffffff"));//设置背景色
		tv.setText(title);//设置文本
		tv.setGravity(Gravity.CENTER);//设置文本居中
		return tv;//返回tv
		//return super.onCreateView(inflater, container, savedInstanceState);
	}
}
