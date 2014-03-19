package com.example.wechatlesson.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HeadLineViewGroup extends ViewGroup{
	private Scroller scroller;// ����
	private int duration = 500;
	private int currentViewIndex = 1;// Ĭ�ϵ�һ��
	
	public HeadLineViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		scroller = new Scroller(context);
	}
	
	
	public Scroller getScroller(){
		return scroller;
	}
	
	public void scrollOver(int caseIndex){// ����mainViewGroup�Ļ������������Ӧ�Ļ���
//		Log.v("HeadLineViewGroup", "getWidth:"+getWidth());
		int length_1 = getWidth()/3;
		int length_2 = (getWidth()-length_1)/2;
		int length_3 = getWidth()-length_1-length_2;
		if(caseIndex==1){//��ӦmainViewGroup�еĵ�һ��if
//			System.out.println("("+getScrollX()+", 0, "+(-(length_1+getScrollX()))+", 0, "+duration+")");
			scroller.startScroll(getScrollX(), 0, (-(length_1+getScrollX())), 0, duration);// �»�����Ӧ�������ڶ���
		}else if(caseIndex==2){//��ӦmainViewGroup�еĵڶ���if
//			System.out.println("("+getScrollX()+", 0, "+(-(getWidth()-length_3)-getScrollX())+", 0, "+duration+")");
			scroller.startScroll(getScrollX(), 0, (-(getWidth()-length_3)-getScrollX()), 0, duration);
		}else if(caseIndex==3){//��ӦmainViewGroup�еĵ�����if
//			System.out.println("("+getScrollX()+", 0, "+(-getScrollX())+", 0, "+duration+")");
			scroller.startScroll(getScrollX(), 0, -getScrollX(), 0, duration);
		}else if(caseIndex==4){//��ӦmainViewGroup�еĵ��ĸ�if
//			System.out.println("("+getScrollX()+", 0, "+(-getScrollX()-length_1)+", 0, "+duration+")");
			scroller.startScroll(getScrollX(), 0, (-getScrollX()-length_1), 0, duration);
		}else{
			// ���ƶ�
		}
		invalidate();
	}
	
	public void onButtonChange(int caseIndex){// ����mainViewGroup��onButtonChange����������Ӧ�Ļ���
		int length_1 = getWidth()/3;
		int length_2 = (getWidth()-length_1)/2;
//		System.out.println("caseIndex:"+caseIndex);
		if(caseIndex==1){	//��ӦmainViewGroup�е�onButtonChange�ĵ�һ�����
//			System.out.println("("+(-length_1)+", 0, "+length_1+", 0, "+duration+")");
			scroller.startScroll((-length_1), 0, length_1, 0, duration);
		}else if(caseIndex==2){//��ӦmainViewGroup�е�onButtonChange�ĵ�2�����
//			System.out.println("("+(-length_1-length_2)+", 0, "+(length_1+length_2)+", 0, "+duration+")");
			scroller.startScroll((-length_1-length_2), 0, (length_1+length_2), 0, duration);
		}else if(caseIndex==3){
//			System.out.println("(0, 0, "+(-length_1)+", 0, "+duration+")");
			scroller.startScroll(0, 0, -length_1, 0, duration);
		}else if(caseIndex==4){
//			System.out.println("("+(-length_1-length_2)+", 0, "+length_2+", 0, "+duration+")");
			scroller.startScroll((-length_1-length_2), 0, length_2, 0, duration);
		}else if(caseIndex==5){
//			System.out.println("(0, 0, "+(-length_1-length_2)+", 0, "+duration+")");
			scroller.startScroll(0, 0, (-length_1-length_2), 0, duration);
		}else if(caseIndex==6){
//			System.out.println("("+(-length_1)+", 0, "+(-length_2)+", 0, "+duration+")");
			scroller.startScroll((-length_1), 0, (-length_2), 0, duration);
		}else{
		}
	}
	
	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		if (scroller.computeScrollOffset()) {
			scrollTo(scroller.getCurrX(), scroller.getCurrY());
			postInvalidate();// ˢ��
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int nWidth = getWidth()/3;
		if (changed) {
			View currentView = getChildAt(0);
			currentView.measure(0, 0);
			currentView.layout(0, 0, nWidth, getHeight());
		}
	}
	
}
