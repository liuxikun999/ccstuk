package com.example.wechatlesson;

import com.example.wechatlesson.adapter.ChatListAdapter;
import com.example.wechatlesson.view.LogListView;

import android.app.Activity;
import android.os.Bundle;

public class ShowChatActivity extends Activity {
	
	private LogListView chatList;
	private ChatListAdapter chatListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.chat_screen_view);
		chatList = (LogListView)findViewById(R.id.chat_list);
		chatListAdapter = new ChatListAdapter(this);
		chatList.setAdapter(chatListAdapter);
	}
}
