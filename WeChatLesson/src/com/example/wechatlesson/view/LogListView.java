package com.example.wechatlesson.view;

import com.example.wechatlesson.ViewGroupActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Scroller;

public class LogListView extends ListView {
	private Scroller scroller;// 滑动
	private int duration = 500;
	

	public LogListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		scroller = new Scroller(context);
		Log.v("LogListView", "new LogListView");
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return super.dispatchKeyEvent(event);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.v("LogListView", "dispatchTouchEvent ");
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		boolean returnBl = false;
//				super.onInterceptTouchEvent(ev);
		Log.v("LogListView", "onInterceptTouchEvent：return "+returnBl);
		return returnBl;
	}
	
	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		if (scroller.computeScrollOffset()) {
			scrollTo(scroller.getCurrX(), scroller.getCurrY());
			postInvalidate();// 刷新
		}
	}
	
	
	public void scrollOverY(int getY, int distance){
		Log.d("LogListView", "scroller.startScroll(0, "+getY+", 0, "+distance+");");  
		scroller.startScroll(0, getY, 0, distance);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
	    switch(ev.getAction()) {  
	    case MotionEvent.ACTION_DOWN:  
	        Log.d("LogListView", "LogListView onTouchEvent ACTION_DOWN");  
	        break;  
	    case MotionEvent.ACTION_MOVE:  
	        Log.d("LogListView", "LogListView onTouchEvent ACTION_MOVE");  
	        break;  
	    case MotionEvent.ACTION_CANCEL:  
	        Log.d("LogListView", "LogListView onTouchEvent ACTION_CANCEL");  
	        break;  
	    case MotionEvent.ACTION_UP: 
	    	ViewGroupActivity.scrollDirection=0;// 重置状态值
	        Log.d("LogListView", "LogListView onTouchEvent ACTION_UP");  
	        break;  
	    }  
	    boolean result;
	    if(ViewGroupActivity.scrollDirection==2 && ev.getAction()==MotionEvent.ACTION_MOVE){// 如果是横向滑动的时候，不响应事件
	    	result = false;
	    }else{
	    	result = super.onTouchEvent(ev);  
	    }
	    Log.d("LogListView", "LogListView onTouchEvent return "+result);
	    return result;
	}

}
