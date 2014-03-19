package com.example.wechatlesson.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.example.wechatlesson.R;
import com.example.wechatlesson.items.ChatItem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatListAdapter extends BaseAdapter {
	private Vector<ChatItem> dataList = new Vector<ChatItem>();
	private Context context;
	private LayoutInflater inflater;
	
	public ChatListAdapter(Context context){
		this.context = context;
		inflater = LayoutInflater.from(context);
		initData();
	}
	
	public void removeAll(){
		dataList.removeAllElements();
		this.notifyDataSetChanged();
	}
	
	//��ʼ������
	private void initData(){
		ChatItem item = new ChatItem();
		item.itemId = 1;
		item.nResourceId = R.drawable.head_icon_1;
		item.title = "׷��������Ӫ���߻�";
		item.date = "10:33";
		item.describ = "�ݶ�3��15�պ��ϵ�һ�컥����������̳���С�";
		dataList.add(item);
		
		item = new ChatItem();
		item.itemId = 2;
		item.nResourceId = R.drawable.head_icon_2;
		item.title = "��������ѧ";
		item.date = "����";
		item.describ = "��Ҫѡ��";
		dataList.add(item);
		
		item = new ChatItem();
		item.itemId = 3;
		item.nResourceId = R.drawable.head_icon_3;
		item.title = "С��";
		item.date = "������";
		item.describ = "���Ѿ������С������������...";
		dataList.add(item);
		
		item = new ChatItem();
		item.itemId = 4;
		item.nResourceId = R.drawable.head_icon_4;
		item.title = "�纸һ��";
		item.date = "������";
		item.describ = "Mason��׼�����Ҵ��������";
		dataList.add(item);
		
		item = new ChatItem();
		item.itemId = 5;
		item.nResourceId = R.drawable.head_icon_1;
		item.title = "׷��������Ӫ���߻�׷��������Ӫ���߻�׷��������Ӫ���߻�";
		item.date = "10:33";
		item.describ = "�ݶ�3��15�պ��ϵ�һ�컥����������̳���С�";
		dataList.add(item);
		
		item = new ChatItem();
		item.itemId = 6;
		item.nResourceId = R.drawable.head_icon_2;
		item.title = "��������ѧ";
		item.date = "����";
		item.describ = "��Ҫѡ��";
		dataList.add(item);
		
		item = new ChatItem();
		item.itemId = 7;
		item.nResourceId = R.drawable.head_icon_3;
		item.title = "С��";
		item.date = "������";
		item.describ = "���Ѿ������С�����������ڿ��ԶԻ��ˡ�";
		dataList.add(item);
		
		item = new ChatItem();
		item.itemId = 8;
		item.nResourceId = R.drawable.head_icon_4;
		item.title = "�纸һ��";
		item.date = "������";
		item.describ = "Mason��׼�����Ҵ��������";
		dataList.add(item);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public Object getItem(int nIdx) {
		// TODO Auto-generated method stub
		return dataList.get(nIdx);
	}

	@Override
	public long getItemId(int nIdx) {
		// TODO Auto-generated method stub
		return dataList.get(nIdx).itemId;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parentGroup) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(contentView==null){
			contentView = inflater.inflate(R.layout.chat_item_view, null);
			viewHolder = new ViewHolder();
			viewHolder.chatIv = (ImageView)contentView.findViewById(R.id.chat_item_iv);
			viewHolder.chatTitle = (TextView)contentView.findViewById(R.id.chat_item_title);
			viewHolder.chatDescrib = (TextView)contentView.findViewById(R.id.chat_item_describ);
			viewHolder.chatDate = (TextView)contentView.findViewById(R.id.chat_item_date);
			
			contentView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)contentView.getTag();
		}
		ChatItem temp = dataList.get(position);
		viewHolder.chatIv.setBackgroundResource(temp.nResourceId);
		viewHolder.chatTitle.setText(temp.title);
		viewHolder.chatDescrib.setText(temp.describ);
		viewHolder.chatDate.setText(temp.date);
		
		return contentView;
	}
	
	public class ViewHolder{
		public ImageView chatIv;
		public TextView chatTitle;
		public TextView chatDescrib;
		public TextView chatDate;
	}

}
