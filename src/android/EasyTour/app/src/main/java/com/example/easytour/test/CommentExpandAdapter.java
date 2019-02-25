package com.example.easytour.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.easytour.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 黄家慧 on 2018/12/20.
 */

public class CommentExpandAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "CommentExpandAdapter";
    private List<CommentDetailBean> commentBeanList;
    private List<ReplyDetailBean> replyBeanList;
    private Context context;
    private int pageIndex = 1;

    public CommentExpandAdapter(Context context, List<CommentDetailBean> commentBeanList) {
        this.context = context;
        this.commentBeanList = commentBeanList;
    }

    @Override
    public int getGroupCount() {
        return commentBeanList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if(commentBeanList.get(i).getReplyList() == null){
            return 0;
        }else {
            return commentBeanList.get(i).getReplyList().size()>0 ? commentBeanList.get(i).getReplyList().size():0;
        }

    }

    @Override
    public Object getGroup(int i) {
        return commentBeanList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return commentBeanList.get(i).getReplyList().get(i1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    boolean isLike = false;

    @Override
    public View getGroupView(final int groupPosition, boolean isExpand, View convertView, ViewGroup viewGroup) {
        final GroupHolder groupHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item_layout, viewGroup, false);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        Glide.with(context).load(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(R.mipmap.ic_launcher)
                .centerCrop();
        groupHolder.tv_name.setText(commentBeanList.get(groupPosition).getAuthor());
        groupHolder.tv_where.setText("我对"+commentBeanList.get(groupPosition).getWhere()+"有话说：");
        groupHolder.tv_content.setText(commentBeanList.get(groupPosition).getData());
        groupHolder.iv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLike){
                    isLike = false;
                    groupHolder.iv_like.setColorFilter(null);
                }else {
                    isLike = true;
                    groupHolder.iv_like.setColorFilter(Color.parseColor("#FF5C5C"));
                }
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        final ChildHolder childHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_reply_item_layout,viewGroup, false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        }
        else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        String replyUser = commentBeanList.get(groupPosition).getReplyList().get(childPosition).getUsername();
        String replyto = commentBeanList.get(groupPosition).getReplyList().get(childPosition).getStatus();
        if(!TextUtils.isEmpty(replyUser)&&replyto.equals("null")){
            childHolder.tv_name.setText(replyUser + ":");
        }else if(!TextUtils.isEmpty(replyUser)&&!replyto.equals("null")) {
            childHolder.tv_name.setText(replyUser + "对"+replyto+"说:");
        }else {
            childHolder.tv_name.setText("匿名"+":");
        }


        childHolder.tv_content.setText(commentBeanList.get(groupPosition).getReplyList().get(childPosition).getContent());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class GroupHolder{
        private TextView tv_name, tv_content, tv_where;
        private ImageView iv_like;
        public GroupHolder(View view) {
            tv_content = (TextView) view.findViewById(R.id.comment_item_content);
            tv_name = (TextView) view.findViewById(R.id.comment_item_userName);
            tv_where = (TextView) view.findViewById(R.id.comment_item_where);
            iv_like = (ImageView) view.findViewById(R.id.comment_item_like);
        }
    }

    private class ChildHolder{
        private TextView tv_name, tv_content;
        public ChildHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.reply_item_user);
            tv_content = (TextView) view.findViewById(R.id.reply_item_content);
        }
    }

    /**
     * 评论成功后插入
     * @param commentDetailBean 评论的数据
     */
    public void addTheCommentData(CommentDetailBean commentDetailBean){
        if(commentDetailBean!=null){

            commentBeanList.add(commentDetailBean);
            notifyDataSetChanged();
        }else {
            throw new IllegalArgumentException("评论数据为空!");
        }

    }

    /**
     *
     * @param replyDetailBean 回复的数据
     * @param groupPosition 当前需要回复的评论所在的位置
     */
    public void addTheReplyData(final ReplyDetailBean replyDetailBean, int groupPosition){
        if(replyDetailBean!=null){
            Log.e(TAG, "addTheReplyData: >>>>该刷新回复列表了:"+replyDetailBean.toString() );
            if(commentBeanList.get(groupPosition).getReplyList() != null ){
                commentBeanList.get(groupPosition).getReplyList().add(replyDetailBean);
            }else {
                List<ReplyDetailBean> replyList = new ArrayList<>();
                replyList.add(replyDetailBean);
                commentBeanList.get(groupPosition).setReplyList(replyList);
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        OkHttpClient client = new OkHttpClient();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("tipid",Integer.toString(replyDetailBean.getTipid()))
                                .add("content",replyDetailBean.getContent())
                                .add("userid",replyDetailBean.getUserid())
                                .add("username",replyDetailBean.getUsername())
                                .add("time",replyDetailBean.getTime())
                                .add("status",replyDetailBean.getStatus())
                                .build();
                        Request request = new Request.Builder()
                                .url("http://10.0.2.2:8080/EasytourServer/addPinglun.jsp")
                                .post(requestBody)
                                .build();
                        Response response = client.newCall(request).execute();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
            notifyDataSetChanged();
        }else {
            throw new IllegalArgumentException("回复数据为空!");
        }

    }

    /**
     *
     * @param replyBeanList 所有回复
     * @param groupPosition 当前的评论
     */
    private void addReplyList(List<ReplyDetailBean> replyBeanList, int groupPosition){
        if(commentBeanList.get(groupPosition).getReplyList() != null ){
            commentBeanList.get(groupPosition).getReplyList().clear();
            commentBeanList.get(groupPosition).getReplyList().addAll(replyBeanList);
        }else {

            commentBeanList.get(groupPosition).setReplyList(replyBeanList);
        }

        notifyDataSetChanged();
    }

}
