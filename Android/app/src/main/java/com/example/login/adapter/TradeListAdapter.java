package com.example.login.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login.Data.ListItem;
import com.example.login.R;

import java.util.ArrayList;

public class TradeListAdapter extends BaseAdapter {
    public ArrayList<ListItem> listviewitem = new ArrayList<ListItem>();
    private final ArrayList<ListItem> arrayList = listviewitem;

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position){
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_theme,parent,false);

            TextView tv_title = (TextView)convertView.findViewById(R.id.tv_title);
            TextView tv_buyer_seller_id = (TextView)convertView.findViewById(R.id.tv_buyer_seller_id);
            TextView tv_tradeTime = (TextView)convertView.findViewById(R.id.tv_tradeTime);

            holder.title = tv_title;
            holder.userID = tv_buyer_seller_id;
            holder.tradeTime = tv_tradeTime;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        ListItem tradelist = arrayList.get(position);
        holder.title.setText(tradelist.getTitle());
        holder.userID.setText(tradelist.getUserID());
        holder.tradeTime.setText(tradelist.getTradeTime());

        return convertView;
    }

    public void addItem(String title, String userID, String tradeTime){
        ListItem listitem = new ListItem();

        listitem.setTitle(title);
        listitem.setUserID(userID);
        listitem.setTradeTime(tradeTime);

        arrayList.add(listitem);
    }

    static class ViewHolder{
        TextView title, userID, tradeTime;
    }
}
// arraydapter와 listview 사용하기 : https://armful-log.tistory.com/26