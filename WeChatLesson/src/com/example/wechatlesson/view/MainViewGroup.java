package com.example.wechatlesson.view;

import com.example.wechatlesson.ViewGroupActivity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MainViewGroup extends ViewGroup {
	private View currentView;
	private Scroller scroller;// ����
	private int duration = 500;
	private int currentViewIndex = 1;// Ĭ�ϵ�һ��
	
	public MainViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		scroller = new Scroller(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		if (changed) {
			currentView = getChildAt(0);
			currentView.measure(0, 0);
			currentView.layout(0, 0, getWidth(), getHeight());
			View secondView = getChildAt(1);
			secondView.measure(0, 0);
			secondView.layout(getWidth(), 0, (getWidth()*2), getHeight());
			View thirdView = getChildAt(2);
			thirdView.measure(0, 0);
			thirdView.layout((getWidth()*2), 0, (getWidth()*3), getHeight());
		}
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return super.dispatchKeyEvent(event);
	}

	/**
	 * [45��,135��][225��,315��]�綨Ϊ���»���
	 * ��������»����򽻸���ҳ�洦����������һ�����ҳ
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		boolean returnBl = false;// ��Ϊtrueʱ������ҳ���onInterceptTouchEvent�޷����մ����¼�
//				super.onInterceptTouchEvent(ev);
		Log.v("MainViewGroup", "onInterceptTouchEvent��return "+returnBl);
		return returnBl;
	}
	
	

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.v("MainViewGroup", "dispatchTouchEvent ");
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
	    switch(ev.getAction()) {  
	    case MotionEvent.ACTION_DOWN:  
	        Log.d("MainViewGroup", "MainViewGroup onTouchEvent ACTION_DOWN");  
	        break;  
	    case MotionEvent.ACTION_MOVE:  
	        Log.d("MainViewGroup", "MainViewGroup onTouchEvent ACTION_MOVE");  
	        break;  
	    case MotionEvent.ACTION_CANCEL:  
	        Log.d("MainViewGroup", "MainViewGroup onTouchEvent ACTION_CANCEL");  
	        break;  
	    case MotionEvent.ACTION_UP:  
	    	ViewGroupActivity.scrollDirection=0;// ����״ֵ̬
	        Log.d("MainViewGroup", "MainViewGroup onTouchEvent ACTION_UP");  
	        break;  
	    }  
	    boolean result;
	    if(ViewGroupActivity.scrollDirection==2 && ev.getAction()==MotionEvent.ACTION_MOVE){// ��������򻬶�ʱ������Ӧ�¼�
	    	result = false;
	    }else{
	    	result = super.onTouchEvent(ev);
	    }
	    Log.d("MainViewGroup", "MainViewGroup onTouchEvent return "+result);  
	    return result;
	}

	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		if (scroller.computeScrollOffset()) {
			scrollTo(scroller.getCurrX(), scroller.getCurrY());
			postInvalidate();// ˢ��
		}
	}
	
	public void onButtonChange(HeadLineViewGroup headLineViewGroup, Handler viewHandler, int targetIdx){
		Log.v("ViewGroup","onButtonChange:["+currentViewIndex+"]["+targetIdx+"]");
		
		Message msg = new Message();
		msg.what = targetIdx;
		msg.arg1 = currentViewIndex;
		
		if(currentViewIndex == targetIdx){
			// ��ҳ�滬��
		}else{
			if(targetIdx==1){// ��������һҳ
				if(currentViewIndex == 2){
					scroller.startScroll(getWidth(), 0, -getWidth(), 0, duration);
					headLineViewGroup.onButtonChange(1);
				}else if(currentViewIndex == 3){
					scroller.startScroll((getWidth()*2), 0, -(getWidth()*2), 0, duration);
					headLineViewGroup.onButtonChange(2);
				}
				currentViewIndex = 1;
			}else if(targetIdx==2){
				if(currentViewIndex == 1){
					scroller.startScroll(0, 0, getWidth(), 0, duration);
					headLineViewGroup.onButtonChange(3);
				}else if(currentViewIndex == 3){
					scroller.startScroll((getWidth()*2), 0, (-getWidth()), 0, duration);
					headLineViewGroup.onButtonChange(4);
				}
				currentViewIndex = 2;
			}else if(targetIdx==3){
				if(currentViewIndex == 1){
					scroller.startScroll(0, 0, (getWidth()*2), 0, duration);
					headLineViewGroup.onButtonChange(5);
				}else if(currentViewIndex == 2){
					scroller.startScroll(getWidth(), 0, getWidth(), 0, duration);
					headLineViewGroup.onButtonChange(6);
				}
				currentViewIndex = 3;
			}
			viewHandler.sendMessage(msg);
		}
	}

	public void scrollOver(Handler viewHandler, HeadLineViewGroup headLineViewGroup){
		Message msg = new Message();
		msg.arg1 = currentViewIndex;
//		System.out.println("getScrollX():::::::::::::"+getScrollX());
		if(getScrollX()>=getWidth()/2 && getScrollX()<=getWidth()){//��λ�Ƴ�������,��ʾ�ڶ���
//			System.out.println("��λ�Ƴ���������"+getScrollX()+">="+(getWidth()/2));
//			System.out.println("("+getScrollX()+", 0, "+(getWidth()-getScrollX())+", 0, "+duration+")");
			scroller.startScroll(getScrollX(), 0, (getWidth()-getScrollX()), 0, duration);
			headLineViewGroup.scrollOver(1);
			// ����ҳ�淢����Ϣ������ҳ��ͷ����TextView����ʾ��ɫ
			msg.what = 2;
			viewHandler.sendMessage(msg);
			//
			currentViewIndex = 2;
		}else if(getScrollX()>=(getWidth()+getWidth()/2)){// ��λ�ƴ��ڵڶ����İ���,��ʾ������
//			System.out.println("��λ�ƴ��ڵڶ����İ�����"+getScrollX()+">="+(getWidth()+getWidth()/2));
			scroller.startScroll(getScrollX(), 0, ((getWidth()*2)-getScrollX()), 0, duration);
//			System.out.println("("+getScrollX()+", 0, "+ ((getWidth()*2)-getScrollX())+", 0, "+duration+")");
			headLineViewGroup.scrollOver(2);
			// ����ҳ�淢����Ϣ������ҳ��ͷ����TextView����ʾ��ɫ
			msg.what = 3;
			viewHandler.sendMessage(msg);
			//
			currentViewIndex = 3;
		}else if(0<=getScrollX() && getScrollX()<(getWidth()/2) ){// ��λ��С�ڵ�һ��������ͣ���ڵ�һ��
//			System.out.println("��λ��С�ڵ�һ��������"+getScrollX());
//			System.out.println("("+getScrollX()+", 0, "+(-getScrollX())+", 0, "+duration+")");
			scroller.startScroll(getScrollX(), 0, -getScrollX(), 0, duration);
			headLineViewGroup.scrollOver(3);
			// ����ҳ�淢����Ϣ������ҳ��ͷ����TextView����ʾ��ɫ
			msg.what = 1;
			viewHandler.sendMessage(msg);
			//
			currentViewIndex = 1;
		}else if(getWidth()<=getScrollX() && getScrollX()<(getWidth()+getWidth()/2) ){// ��λ��С�ڵڶ���������ͣ���ڵڶ���
//			System.out.println("��λ��С�ڵڶ���������"+getScrollX());
//			System.out.println("("+getScrollX()+", 0, "+(getWidth()-getScrollX())+", 0, "+duration+")");
			scroller.startScroll(getScrollX(), 0, (getWidth()-getScrollX()), 0, duration);
			headLineViewGroup.scrollOver(4);
			// ����ҳ�淢����Ϣ������ҳ��ͷ����TextView����ʾ��ɫ
			msg.what = 2;
			viewHandler.sendMessage(msg);
			//
			currentViewIndex = 2;
		}else{// ��λ��
//			System.out.println("��λ�ƣ�"+getScrollX());
//			scroller.startScroll(getScrollX(), 0, getScrollX(), 0, duration);
		}
		invalidate();
	}

	public int getCurrentViewIndex() {
		return currentViewIndex;
	}

	public void setCurrentViewIndex(int currentViewIndex) {
		this.currentViewIndex = currentViewIndex;
	}

}
