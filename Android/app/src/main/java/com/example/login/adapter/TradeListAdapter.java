package com.example.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.login.Data.ListItem;
import com.example.login.R;

import java.util.ArrayList;

public class TradeListAdapter extends ArrayAdapter {
    public ArrayList<ListItem> listviewitem = new ArrayList<ListItem>();

    public TradeListAdapter(Context context, ArrayList tradelist){
        super(context,0,tradelist);
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

        ListItem tradelist = listviewitem.get(position);
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

        listviewitem.add(listitem);
    }

    static class ViewHolder{
        TextView title, userID, tradeTime;
    }
}
// arraydapter와 listview 사용하기 : https://armful-log.tistory.com/26