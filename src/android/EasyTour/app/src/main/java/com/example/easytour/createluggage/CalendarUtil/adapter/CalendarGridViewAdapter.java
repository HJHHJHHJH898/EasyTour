package com.example.easytour.createluggage.CalendarUtil.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.easytour.R;
import com.example.easytour.createluggage.CalendarUtil.bean.DateItem;
import com.example.easytour.createluggage.CalendarUtil.customview.CustomCalendarView;
import com.example.easytour.createluggage.CalendarUtil.util.ConfigUtils;

import java.util.Calendar;
import java.util.List;

/**
 * Created by 黄家慧 on 2018/12/16.
 */
public class CalendarGridViewAdapter extends BaseAdapter {

    private List<DateItem> list;

    private Context context;
    private CustomCalendarView customCalendarView;
    private int itembackgourd;
    public CalendarGridViewAdapter(CustomCalendarView customCalendarView, Context context, List<DateItem> list, int itembackgourd){
        this.context=context;
        this.list=list;
        this.itembackgourd=itembackgourd;
        this.customCalendarView=customCalendarView;

    }
    public void setDate(List<DateItem> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textView=null;
        if (convertView==null){
            textView=new TextView(context);
            convertView=textView;
        }
        textView= (TextView) convertView;
        textView.setGravity(Gravity.CENTER);

        if (list.get(position).isselect()){
            if (itembackgourd==-1) {
                textView.setBackgroundColor(context.getResources().getColor(R.color.cpb_blue));
            }else {
                textView.setBackgroundResource(itembackgourd);
            }
        }else {
            textView.setBackgroundColor(context.getResources().getColor(R.color.base_bg));
        }
        textView.setTextSize(22);



        if (list.get(position).getDateOfMonth()>0){
            final TextView finalTextView = textView;
            textView.setText(list.get(position).getDateOfMonth() + "");
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).setIsselect(!list.get(position).isselect());
                    if (list.get(position).isselect()){
                        if (itembackgourd==-1) {
                            finalTextView.setBackgroundColor(context.getResources().getColor(R.color.cpb_blue));
                        }else {
                            finalTextView.setBackgroundResource(itembackgourd);
                        }

                        Calendar calendar= Calendar.getInstance();
                        calendar.set(list.get(position).getYear(),list.get(position).getMonth(),list.get(position).getDateOfMonth());
                        customCalendarView.addSelectDate(ConfigUtils.simpleDate(calendar.getTime()));
                    }else {
                        finalTextView.setBackgroundColor(context.getResources().getColor(R.color.base_bg));


                        Calendar calendar= Calendar.getInstance();
                        calendar.set(list.get(position).getYear(),list.get(position).getMonth(),list.get(position).getDateOfMonth());
                        customCalendarView.removeSelect(ConfigUtils.simpleDate(calendar.getTime()));

                    }
                }
            });
        }else {
            textView.setBackgroundColor(context.getResources().getColor(R.color.base_bg));
            textView.setText("");
        }
        return convertView;
    }
}
