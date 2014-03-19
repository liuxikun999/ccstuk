package com.example.wechatlesson;


import java.util.*;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.wechatlesson.adapter.*;
import com.example.wechatlesson.view.*;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;
public class ViewGroupActivity extends SherlockActivity implements OnClickListener, GestureDetector.OnGestureListener {
//	,GestureDetector.OnGestureListener
	private static int CHAT_BUTTON_INDEX = 1;
	private static int FOUND_BUTTON_INDEX = 2;
	private static int CONTACT_BUTTON_INDEX = 3;
	
	public static int scrollDirection = 0;// 0 ��ʼֵ     1 ���򻬶�   2 ���򻬶�
	private LinearLayout headLinearLayout;
	private MainViewGroup mainViewGroup;
	private HeadLineViewGroup headLineViewGroup;
	private GestureDetector gestureDetector;
//	private ViewTreeObserver viewTreeObserver;
	private TextView chatButton;
	private TextView foundButton;
	private TextView contactButton;
	private LinearLayout chatLine;
	private LayoutInflater inflater;
	
	private View chatScreenView;
	private LogListView chatList;
	private ChatListAdapter chatListAdapter;
	
	private View foundView;
	private LogListView findList;
	private FindListAdapter findListAdapter;
	
	private ListView contactListView;
	
//	private Button button1;
//	private Button button2;
//	private Button button3;
//	private boolean isSetParamed = false;
	private int nScreenWidth = 0;
	private int headHeight = 0;
	private int statusBarHeight = 0;
//	private ViewTreeObserver viewTreeObserver;
//	private boolean hasMeasured = false;
	private int mainViewGroupLocationY=0;
	private boolean ifSetListHeight = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_view_group);
		inflater = LayoutInflater.from(this);
		headLinearLayout = (LinearLayout)this.findViewById(R.id.head_card_group_ly);
		headHeight = getHeight(headLinearLayout);
		Log.v("ViewGroupActivity", "headLinearLayout.height:"+headHeight);
		mainViewGroup = (MainViewGroup)this.findViewById(R.id.main_view_group);
		headLineViewGroup = (HeadLineViewGroup)findViewById(R.id.head_line_view_group);
		chatButton = (TextView)findViewById(R.id.head_chat_me);
		foundButton = (TextView)findViewById(R.id.head_found);
		contactButton = (TextView)findViewById(R.id.head_contacts);
		chatLine = new LinearLayout(this);
		chatLine.setBackgroundColor(getResources().getColor(R.color.bg_head_line));
		
		nScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
		statusBarHeight = Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
		Log.v("viewGroupActivity", "statusBarHeight:"+statusBarHeight);
		
		final int chatButtonWidth = nScreenWidth/3;
		LinearLayout.LayoutParams chatButtonLayoutParams = new LinearLayout.LayoutParams(chatButtonWidth, LinearLayout.LayoutParams.FILL_PARENT);
		final int foundButtonWidth = (nScreenWidth-chatButtonWidth)/2;
		LinearLayout.LayoutParams foundButtonLayoutParams = new LinearLayout.LayoutParams(foundButtonWidth, LinearLayout.LayoutParams.FILL_PARENT);
		final int contactButtonWidth = nScreenWidth - chatButtonWidth - foundButtonWidth;
		LinearLayout.LayoutParams contactButtonLayoutParams = new LinearLayout.LayoutParams(contactButtonWidth, LinearLayout.LayoutParams.FILL_PARENT);
		
		chatButton.setLayoutParams(chatButtonLayoutParams);
		chatLine.setLayoutParams(chatButtonLayoutParams);
		foundButton.setLayoutParams(foundButtonLayoutParams);
		contactButton.setLayoutParams(contactButtonLayoutParams);
		
		chatButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.v("viewGroupActivity", "chatButton.OnClickListener....");
				mainViewGroup.onButtonChange(headLineViewGroup, headViewHandler, CHAT_BUTTON_INDEX);
			}
		});
		foundButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.v("viewGroupActivity", "foundButton.OnClickListener....");
				mainViewGroup.onButtonChange(headLineViewGroup, headViewHandler, FOUND_BUTTON_INDEX);
			}
		});
		contactButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.v("viewGroupActivity", "contactButton.OnClickListener....");
				mainViewGroup.onButtonChange(headLineViewGroup, headViewHandler, CONTACT_BUTTON_INDEX);
			}
		});
		
		chatScreenView = (View)inflater.inflate(R.layout.chat_screen_view, null);
		chatList = (LogListView)chatScreenView.findViewById(R.id.chat_list);
//		chatList = new ListView(this);
		chatListAdapter = new ChatListAdapter(this);
		chatList.setAdapter(chatListAdapter);
		Log.v("ViewGroupActivity", "chatList ListView height:"+chatList.getLayoutParams().height);
		// ��������ListView�Ŀ�ȸ߶ȣ���Ȼ��ʾ��ȫ
		setListViewHeightBaseOnChildren(chatList,178);// ������ViewGroup.LayoutParams����LinearLayout.LayoutParams��ʾ��ȫ
		chatList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.v("ViewGroupActivity", "chatList Item Click "+arg2);
			}
		});
		
		foundView = inflater.inflate(R.layout.found_screen_view, null);
		findList = (LogListView )foundView.findViewById(R.id.find_list);
//		findList = new ListView(this);
		findList.setDividerHeight(0);
		findListAdapter = new FindListAdapter(this);
		findList.setAdapter(findListAdapter);
		Log.v("ViewGroupActivity", "findList ListView height:"+findList.getLayoutParams().height);
		
		setListViewHeightBaseOnChildren(findList,178);// ������ViewGroup.LayoutParams����LinearLayout.LayoutParams��ʾ��ȫ
		
		contactListView = new ListView(this);
		contactListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
		contactListView.setBackgroundColor(getResources().getColor(R.color.bg_white));

		mainViewGroup.addView(chatScreenView);
		mainViewGroup.addView(foundView);
//		mainViewGroup.addView(button3);
		mainViewGroup.addView(contactListView);
		
		headLineViewGroup.addView(chatLine);
		
		gestureDetector = new GestureDetector(this);
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		int[] mainViewGroupLocation = new int[2];
	    mainViewGroup.getLocationInWindow(mainViewGroupLocation);
	    Log.v("ViewGroupActivity", "getActionBar().getHeight():"+getActionBar().getHeight());
		Log.v("ViewGroupActivity", "mainViewGroupLocationX:"+mainViewGroupLocation[0]);
		Log.v("ViewGroupActivity", "mainViewGroupLocationY:"+mainViewGroupLocation[1]);
		Log.v("ViewGroupActivity", "mainViewGroup getX:"+mainViewGroup.getX());
		Log.v("ViewGroupActivity", "mainViewGroup getY:"+mainViewGroup.getY());
		Log.v("ViewGroupActivity", "mainViewGroup getTop:"+mainViewGroup.getTop());
		headHeight = getHeight(headLinearLayout);
	    Log.v("ViewGroupActivity", "headTextView  headHeight_2:"+headHeight);
	    mainViewGroupLocationY = mainViewGroupLocation[1];
	    Log.v("ViewGroupActivity", "mainViewGroupLocationY : "+mainViewGroupLocationY);
	    if(ifSetListHeight==false){
	    	setListViewHeightBaseOnChildren(chatList);
	    	setListViewHeightBaseOnChildren(findList);
	    	ifSetListHeight = true;
	    }
	}
	
	public void changeTextViewColor(final TextView setBlack, final TextView setGreen){
		setBlack.setTextColor(Color.BLACK);
		setGreen.setTextColor(getResources().getColor(R.color.bg_head_line));
	}
	
	public Handler headViewHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
//			Log.v("ViewGroupActivity", "headViewHandler��"+msg.what+"��"+msg.arg1+"��");
			// TODO Auto-generated method stub
			if(msg.what==1){// ��ʾ��һ��ʱ����ͷ��TextView��������ɫ
				if(msg.arg1==2){
					changeTextViewColor(foundButton, chatButton);
				}else if(msg.arg1 == 3){
					changeTextViewColor(contactButton, chatButton);
				}
			}else if(msg.what==2){// ��ʾ�ڶ���ʱ����ͷ��TextView��������ɫ
				if(msg.arg1 == 1){
					changeTextViewColor(chatButton, foundButton);
				}else if(msg.arg1 == 3){
					changeTextViewColor(contactButton, foundButton);
				}
			}else if(msg.what==3){// ��ʾ������ʱ����ͷ��TextView��������ɫ
				if(msg.arg1 == 2){
					changeTextViewColor(foundButton, contactButton);
				}else if(msg.arg1 == 1){
					changeTextViewColor(chatButton, contactButton);
				}
			}
			super.handleMessage(msg);
		}
	};
	
	/***
	 * ����������ListView��������ֻ��ʾһ������
	 * ��̬����listview�ĸ߶� ע����listview��scrollview��ͻ��ʱ�����ǿ������������������̬����listview�ĸ߶ȣ�
	 * ���������ﲻ�У�ԭ����ȷ�����ǻ���һ���취���ǰ�listview���ú���Ļһ���ĸ߶ȣ�
	 * ����������viewgroup��scrillview�϶�������.
	 * 
	 * @param listView
	 */
	public void setListViewHeightBaseOnChildren(ListView listView) {
		ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
		layoutParams.width = getWindowManager().getDefaultDisplay().getWidth();
		Log.v("ViewGroupActivity", getWindowManager().getDefaultDisplay().getHeight()+"-"+mainViewGroupLocationY+"-"+statusBarHeight);
		layoutParams.height = getWindowManager().getDefaultDisplay().getHeight()-mainViewGroupLocationY-statusBarHeight;
		listView.setLayoutParams(layoutParams);
		Log.v("ViewGroupActivity", "ListView height:"+listView.getLayoutParams().height);
		
	}
	
	public void setListViewHeightBaseOnChildren(ListView listView, int nDistance) {
		ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
		layoutParams.width = getWindowManager().getDefaultDisplay().getWidth();
		Log.v("ViewGroupActivity", getWindowManager().getDefaultDisplay().getHeight()+"-"+nDistance);
		layoutParams.height = getWindowManager().getDefaultDisplay().getHeight()-nDistance;
		listView.setLayoutParams(layoutParams);
		Log.v("ViewGroupActivity", "ListView height:"+listView.getLayoutParams().height);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.v("ViewGroupActivity", "onDown,getX:"+e.getX());
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		Log.v("ViewGroupActivity", "onFling..............");
		return false;
	}
	
	

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		Log.v("ViewGroupActivity", "dispatchKeyEvent.....");
		if(event.getAction()==KeyEvent.ACTION_UP){// ����
			scrollDirection = 0;// �ָ���ʼֵ
		}
		return super.dispatchKeyEvent(event);
	}
	
	

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		
		// TODO Auto-generated method stub
		if(ev.getAction()==MotionEvent.ACTION_UP){
			mainViewGroup.scrollOver(this.headViewHandler, headLineViewGroup);
		}
		gestureDetector.onTouchEvent(ev);
		boolean returnBl = super.dispatchTouchEvent(ev);// �ַ��¼�����ҳ�洦��
		Log.v("ViewGroupActivity", "dispatchTouchEvent:"+returnBl);
		return returnBl;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		boolean result = super.onTouchEvent(ev);  
	    switch(ev.getAction()) {  
	    case MotionEvent.ACTION_DOWN:  
	        Log.d("ViewGroupActivity", "ViewGroupActivity onTouchEvent ACTION_DOWN");  
	        break;  
	    case MotionEvent.ACTION_MOVE:  
	        Log.d("ViewGroupActivity", "ViewGroupActivity onTouchEvent ACTION_MOVE");  
	        break;  
	    case MotionEvent.ACTION_CANCEL:  
	        Log.d("ViewGroupActivity", "ViewGroupActivity onTouchEvent ACTION_CANCEL");  
	        break;  
	    case MotionEvent.ACTION_UP:  
	    	scrollDirection = 0;// �ָ���ʼֵ
	        Log.d("ViewGroupActivity", "ViewGroupActivity onTouchEvent ACTION_UP");  
	        break;  
	    }  
	  
	    Log.d("ViewGroupActivity", "ViewGroupActivity onTouchEvent return "+result);  
	    return result;
	}
	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		if(scrollDirection==0){// ��һ�μ�⻬���������û�������
			float subX = e1.getX()-e2.getX();
			float subY = e1.getY()-e2.getY();
			Log.v("ViewGroupActivity", "OnScroll:("+e1.getX()+"-"+e2.getX()+")="+subX+";("+e1.getY()+"-"+e2.getY()+")="+subY);
			if(Math.abs(subX)>=Math.abs(subY)){
				scrollDirection = 2;
				Log.v("ViewGroupActivity", "OnScroll:(���򻬶�)");
			}else{
				scrollDirection = 1;
				Log.v("ViewGroupActivity", "OnScroll:(���򻬶�)");
			}
		}
		if(scrollDirection==2){//���򻬶�
			/**
			 * һ����������Ĭ�ϵ�һ���������������ұ�˳�����У����� mainViewGroup.getScrollX()>0(����������),
			 * mainViewGroup.getScrollX()<(2*nScreenWidth)����������������
			 */
			if(mainViewGroup.getCurrentViewIndex()==CHAT_BUTTON_INDEX && distanceX<0){
				// ��ǰ��ʾ��һ��ʱ����ֹ���һ���
			}else if(mainViewGroup.getCurrentViewIndex()==CONTACT_BUTTON_INDEX && distanceX>0){
				// ��ǰ��ʾ������ʱ����ֹ���󻬶�
			}else{
				int lineDistanceX = -((int) distanceX)/3;
				mainViewGroup.scrollBy((int) distanceX, 0);
				headLineViewGroup.scrollBy(lineDistanceX, 0);
			}
		}else if(scrollDirection==1){//���򻬶�
			
		}else{
			Log.v("ViewGroupActivity", "û�л���״̬...");
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.v("ViewGroupActivity", "MenuItem.getItemId="+item.getItemId());
		Log.v("ViewGroupActivity", "MenuItem.getTitle="+item.getTitle());
		Log.v("ViewGroupActivity", "getActionBar().getHeight:"+getActionBar().getHeight());
		Log.v("ViewGroupActivity", "getActionBar().getNavigationItemCount:"+getActionBar().getNavigationItemCount());
		Log.v("ViewGroupActivity", "getActionBar().getTabCount:"+getActionBar().getTabCount());
		return super.onOptionsItemSelected(item);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.main_menu, menu);
//		super.onCreateOptionsMenu(menu);
//		MenuItem add = menu.add(0, 1, 0, "Save"); 
//		MenuItem open = menu.add(0, 2, 1, "Open"); 
//		MenuItem close = menu.add(0, 3, 2, "Close"); 
//		add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
//		open.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
//		close.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
//	    return true; 
//	}
	
	/**
     * ��ȡ����߶ȣ���ȣ�
     * 
     * @param view
     * @return
     */  
    public int getHeight(View view)
    {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredHeight();
    }

	private List<String> getData(){
        List<String> data = new ArrayList<String>();
        data.add("��������1");
        data.add("��������2");
        data.add("��������3");
        data.add("��������4");
        data.add("��������5");
        data.add("��������6");
        data.add("��������7");
        data.add("��������8");
        data.add("��������9");
        data.add("��������10");
        data.add("��������11");
        return data;
    }
}
