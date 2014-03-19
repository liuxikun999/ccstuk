package com.example.wechatlesson.adapter;

import java.util.Vector;

import com.example.wechatlesson.R;
import com.example.wechatlesson.items.FindItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FindListAdapter extends BaseAdapter {
	private Vector<FindItem> dataList = new Vector<FindItem>();
	private Context context;
	private LayoutInflater inflater;
	
	public FindListAdapter(Context context){
		this.context = context;
		inflater = LayoutInflater.from(context);
		initData();
	}
	
	public void removeAll(){
		dataList.removeAllElements();
		this.notifyDataSetChanged();
	}
	
	//初始化数据
	private void initData(){
		FindItem item = new FindItem();
		item.findId = 1;
		item.findName = "朋友圈";
		item.iconResourceId = R.drawable.find_more_friend_photograph_icon;
		dataList.add(item);
		item = new FindItem();
		item.findId = 2;
		item.findName = "扫一扫";
		item.iconResourceId = R.drawable.find_more_friend_scan;
		dataList.add(item);
		item = new FindItem();
		item.findId = 3;
		item.findName = "摇一摇";
		item.iconResourceId = R.drawable.find_more_friend_shake;
		dataList.add(item);
		item = new FindItem();
		item.findId = 4;
		item.findName = "附近的人";
		item.iconResourceId = R.drawable.find_more_friend_near_icon;
		dataList.add(item);
		item = new FindItem();
		item.findId = 5;
		item.findName = "漂流瓶";
		item.iconResourceId = R.drawable.find_more_friend_bottle;
		dataList.add(item);
		item = new FindItem();
		item.findId = 6;
		item.findName = "游戏";
		item.iconResourceId = R.drawable.more_game;
		dataList.add(item);
		item = new FindItem();
		item.findId = 7;
		item.findName = "表情商店";
		item.iconResourceId = R.drawable.more_emoji_store;
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
		return dataList.get(nIdx).findId;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parentGroup) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(contentView==null){
			contentView = inflater.inflate(R.layout.found_item_view, null);
			viewHolder = new ViewHolder();
			viewHolder.findIv = (ImageView)contentView.findViewById(R.id.find_more_friend_iv);
			viewHolder.findNameTv = (TextView)contentView.findViewById(R.id.find_more_friend_tv);
			viewHolder.findDividerLy = (LinearLayout)contentView.findViewById(R.id.find_more_divide_line);
			
			contentView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)contentView.getTag();
		}
		FindItem temp = dataList.get(position);
		viewHolder.findIv.setBackgroundResource(temp.iconResourceId);
		viewHolder.findNameTv.setText(temp.findName);
		ViewGroup.LayoutParams layoutParams = viewHolder.findDividerLy.getLayoutParams();
		if(temp.findId==1 || temp.findId==5){// 朋友圈 或者 漂流瓶
			layoutParams.height = 4;
		}else{
			layoutParams.height = 1;
		}
		viewHolder.findDividerLy.setLayoutParams(layoutParams);
		
		return contentView;
	}
	
	public class ViewHolder{
		public ImageView findIv;
		public TextView findNameTv;
		public LinearLayout findDividerLy;
	}

}
