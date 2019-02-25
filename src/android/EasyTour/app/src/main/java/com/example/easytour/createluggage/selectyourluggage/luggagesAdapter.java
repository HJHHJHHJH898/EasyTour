package com.example.easytour.createluggage.selectyourluggage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.easytour.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黄家慧 on 2018/11/8.
 */

public class luggagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context = null;
    private LayoutInflater inflater = null;
    private final int ONE_ITEM = 0;
    private final int TWO_ITEM = 1;
    private final int THREE_ITEM = 2;
    private final int FOUR_ITEM = 3;
    private final int FIVE_ITEM = 4;
    Map<String,String>[] judge = new Map[4];
    String Address;
    String startDay;
    String endDay;
    private OnItemClickLitener mOnItemClickLitener;

    public luggagesAdapter(Context context,String Address,String start,String end){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.Address = Address;
        this.startDay = start;
        this.endDay = end;
        for(int i=0;i<4;i++){
            judge[i] = new HashMap<String, String>();
        }
    }
    //设置子项item的布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ONE_ITEM:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stay,parent,false);
                OneHolder oneHolder = new OneHolder(view1);
                return oneHolder;
            case TWO_ITEM:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_traffic,parent,false);
                TwoHolder twoHolder = new TwoHolder(view2);
                return twoHolder;
            case THREE_ITEM:
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity,parent,false);
                ThreeHolder threeHolder = new ThreeHolder(view3);
                return threeHolder;
            case FOUR_ITEM:
                View view4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_etc,parent,false);
                FourHolder fourHolder = new FourHolder(view4);
                return fourHolder;
            case FIVE_ITEM:
                View view5 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_creat,parent,false);
                FiveHolder fiveHolder = new FiveHolder(view5);
                return fiveHolder;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == ONE_ITEM) {
            return ONE_ITEM;
        } else if(position == TWO_ITEM){
            return TWO_ITEM;
        }else if(position == THREE_ITEM){
            return THREE_ITEM;
        }else if(position == FOUR_ITEM){
            return FOUR_ITEM;
        }else {
            return FIVE_ITEM;
        }
    }
    //给控件设置数据
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //holder.tv_main.setText(mList.get(position));
        if (holder instanceof OneHolder){
            OneHolder oneHolder = (OneHolder) holder;
        }
        if (holder instanceof TwoHolder){
            TwoHolder twoHolder = (TwoHolder) holder;
        }
        if (holder instanceof ThreeHolder){
            ThreeHolder threeHolder = (ThreeHolder) holder;
        }
        if (holder instanceof FourHolder){
            FourHolder fourHolder = (FourHolder) holder;
        }
        if (holder instanceof FiveHolder){
            FiveHolder fiveHolder = (FiveHolder) holder;
        }
    }

    //子项item的数量
    @Override
    public int getItemCount() {
        return 5;
    }

    class OneHolder extends RecyclerView.ViewHolder{
        private Button button1;
        private Button button2;
        private Button button3;
        private Button button4;
        private Button button5;
        private Button button6;
        int[] flag = new int[6];
        public OneHolder(View view){
            super(view);
            button1 = (Button)view.findViewById(R.id.btn_one);
            button2 = (Button)view.findViewById(R.id.btn_two);
            button3 = (Button)view.findViewById(R.id.btn_three);
            button4 = (Button)view.findViewById(R.id.btn_four);
            button5 = (Button)view.findViewById(R.id.btn_five);
            button6 = (Button)view.findViewById(R.id.btn_six);
            for(int i=0;i<flag.length;i++){
                flag[i]=0;
            }
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[0] = flag[0]%2+1;
                    setButton(button1,flag[0],0,1);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[1] = flag[1]%2+1;
                    setButton(button2,flag[1],0,2);
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[2] = flag[2]%2+1;
                    setButton(button3,flag[2],0,3);
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[3] = flag[3]%2+1;
                    setButton(button4,flag[3],0,4);
                }
            });
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[4] = flag[4]%2+1;
                    setButton(button5,flag[4],0,5);
                }
            });
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[5] = flag[5]%2+1;
                    setButton(button6,flag[5],0,6);
                }
            });
        }
    }

    class TwoHolder extends RecyclerView.ViewHolder{
        private Button button1;
        private Button button2;
        private Button button3;
        private Button button4;
        private Button button5;
        private Button button6;
        int[] flag = new int[6];
        public TwoHolder(View view){
            super(view);
            button1 = (Button)view.findViewById(R.id.btn_one);
            button2 = (Button)view.findViewById(R.id.btn_two);
            button3 = (Button)view.findViewById(R.id.btn_three);
            button4 = (Button)view.findViewById(R.id.btn_four);
            button5 = (Button)view.findViewById(R.id.btn_five);
            button6 = (Button)view.findViewById(R.id.btn_six);
            for(int i=0;i<flag.length;i++){
                flag[i]=0;
            }
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[0] = flag[0]%2+1;
                    setButton(button1,flag[0],1,1);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[1] = flag[1]%2+1;
                    setButton(button2,flag[1],1,2);
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[2] = flag[2]%2+1;
                    setButton(button3,flag[2],1,3);
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[3] = flag[3]%2+1;
                    setButton(button4,flag[3],1,4);
                }
            });
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[4] = flag[4]%2+1;
                    setButton(button5,flag[4],1,5);
                }
            });
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[5] = flag[5]%2+1;
                    setButton(button6,flag[5],1,6);
                }
            });
        }
    }

    class ThreeHolder extends RecyclerView.ViewHolder{
        private Button button1;
        private Button button2;
        private Button button3;
        private Button button4;
        private Button button5;
        private Button button6;
        private Button button7;
        private Button button8;
        private Button button9;
        private Button button10;
        private Button button11;
        private Button button12;
        int[] flag = new int[12];
        public ThreeHolder(View view){
            super(view);
            button1 = (Button)view.findViewById(R.id.btn_one);
            button2 = (Button)view.findViewById(R.id.btn_two);
            button3 = (Button)view.findViewById(R.id.btn_three);
            button4 = (Button)view.findViewById(R.id.btn_four);
            button5 = (Button)view.findViewById(R.id.btn_five);
            button6 = (Button)view.findViewById(R.id.btn_six);
            button7 = (Button)view.findViewById(R.id.btn_seven);
            button8 = (Button)view.findViewById(R.id.btn_eight);
            button9 = (Button)view.findViewById(R.id.btn_nine);
            button10 = (Button)view.findViewById(R.id.btn_ten);
            button11 = (Button)view.findViewById(R.id.btn_eleven);
            button12 = (Button)view.findViewById(R.id.btn_twelve);
            for(int i=0;i<flag.length;i++){
                flag[i]=0;
            }
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[0] = flag[0]%2+1;
                    setButton(button1,flag[0],2,1);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[1] = flag[1]%2+1;
                    setButton(button2,flag[1],2,2);
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[2] = flag[2]%2+1;
                    setButton(button3,flag[2],2,3);
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[3] = flag[3]%2+1;
                    setButton(button4,flag[3],2,4);
                }
            });
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[4] = flag[4]%2+1;
                    setButton(button5,flag[4],2,5);
                }
            });
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[5] = flag[5]%2+1;
                    setButton(button6,flag[5],2,6);
                }
            });
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[6] = flag[6]%2+1;
                    setButton(button7,flag[6],2,7);
                }
            });
            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[7] = flag[7]%2+1;
                    setButton(button8,flag[7],2,8);
                }
            });
            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[8] = flag[8]%2+1;
                    setButton(button9,flag[8],2,9);
                }
            });
            button10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[9] = flag[9]%2+1;
                    setButton(button10,flag[9],2,10);
                }
            });
            button11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[10] = flag[10]%2+1;
                    setButton(button11,flag[10],2,11);
                }
            });
            button12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[11] = flag[11]%2+1;
                    setButton(button12,flag[11],2,12);
                }
            });
        }
    }

    class FourHolder extends RecyclerView.ViewHolder{
        private Button button1;
        private Button button2;
        int[] flag = new int[2];

        public FourHolder(View view){
            super(view);
            button1 = (Button)view.findViewById(R.id.btn_one);
            button2 = (Button)view.findViewById(R.id.btn_two);
            for(int i=0;i<flag.length;i++){
                flag[i]=0;
            }
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  flag[0] = flag[0]%2+1;
                  setButton(button1,flag[0],3,1);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[1] = flag[1]%2+1;
                    setButton(button2,flag[1],3,2);
                }
            });
        }
    }

    public void setButton(Button button,int flag,int kind,int num){
        if(flag%2==1){
            button.setActivated(true);
            button.setTextColor(0x8059bb0c);
            judge[kind].put(Integer.toString(num),Integer.toString(1));
        }else{
            button.setActivated(false);
            button.setTextColor(0xFF000000);
            judge[kind].put(Integer.toString(num),Integer.toString(0));
        }
    }

    class FiveHolder extends RecyclerView.ViewHolder{
        private Button button1;
        //四种选项的所有选项放入builder 选项种类-选项
        StringBuilder allselect = new StringBuilder();
        public FiveHolder(View view){
            super(view);
            button1 = (Button)view.findViewById(R.id.btn_one);
            //实现点击，将数据存入数据库。同时穿梭(回调实现)
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0;i<4;i++){
                        for (Map.Entry<String, String> entry : judge[i].entrySet()) {
                            if(entry.getValue().equals("1")){
                                allselect.append(i+1).append("-").append(entry.getKey()).append("|");
                            }
                        }
                    }
                    if(mOnItemClickLitener!=null){
                        mOnItemClickLitener.onItemClick(allselect.toString());
                    }
                    allselect.setLength(0);//清空
                }
            });
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
