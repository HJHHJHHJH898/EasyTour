package com.example.easytour.allluggage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easytour.R;
import com.example.easytour.createluggage.selectyourluggage.luggagesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家慧 on 2018/11/9.
 */

public class alltravelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<PlanInfo> planInfos = new ArrayList<PlanInfo>();
    private Context context = null;
    private LayoutInflater inflater = null;
    private final int ONE_ITEM = 0;
    private final int TWO_ITEM = 1;

    public alltravelAdapter(Context context,List<PlanInfo> list){
        this.planInfos = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    //子项item的数量
    @Override
    public int getItemCount() {
        return planInfos.size()+1;
    }
    //设置子项item的布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ONE_ITEM:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.allluggages_item_alltravel,parent,false);
                alltravelAdapter.OneHolder oneHolder = new alltravelAdapter.OneHolder(view1);
                return oneHolder;
            case TWO_ITEM:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.allluggages_item_share,parent,false);
                alltravelAdapter.TwoHolder twoHolder = new alltravelAdapter.TwoHolder(view2);
                return twoHolder;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position != planInfos.size()) {
            return ONE_ITEM;
        } else {
            return TWO_ITEM;
        }
    }

    //给控件设置数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OneHolder){
            PlanInfo planInfo = planInfos.get(position);    //放外部错误
            OneHolder oneHolder = (OneHolder) holder;
            oneHolder.tv_dest.setText("去到"+planInfo.getDest().toString());
            oneHolder.tv_time.setText("时间："+planInfo.getStarttime().toString());
        }
        if (holder instanceof TwoHolder){
            TwoHolder twoHolder = (TwoHolder) holder;
        }
    }

    class OneHolder extends RecyclerView.ViewHolder{
        private TextView tv_dest;
        private TextView tv_time;
        public OneHolder(View view){
            super(view);
            tv_dest = (TextView)view.findViewById(R.id.gotodest);
            tv_time = (TextView)view.findViewById(R.id.tv_item_allluggages_time);
//            tv_dest.setText("去到"+planInfo.getDest().toString());
//            tv_time.setText("时间："+planInfo.getStarttime().toString());
        }
    }

    class TwoHolder extends RecyclerView.ViewHolder{
        public TwoHolder(View view){
            super(view);
        }
    }

}