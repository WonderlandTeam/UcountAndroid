package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.Reply;

import java.util.List;

/**
 * Created by CLL on 17/9/1.
 */
public class MyReplyRecyclerAdapter extends RecyclerView.Adapter<MyReplyRecyclerAdapter.MyReplyViewHolder> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;
    private List<Reply> replies;
    private Context context;

    public MyReplyRecyclerAdapter(List<Reply> replies, Context context) {
        this.replies=replies;
        this.context = context;
    }

    @Override
    public MyReplyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.my_reply_item,parent,false);
        MyReplyViewHolder myReplyViewHolder=new MyReplyViewHolder(v);
        v.setOnClickListener(this);
        return myReplyViewHolder;
    }

    @Override
    public void onBindViewHolder(MyReplyViewHolder holder, int position) {
        MyReplyViewHolder.reply_header.setText(replies.get(position).getTitle());
        String text="对 "+replies.get(position).getUsername()+" 的回复:";
        MyReplyViewHolder.reply_user.setText(text);
    }

    @Override
    public int getItemCount() {
        return replies.size();
    }

    static class MyReplyViewHolder extends RecyclerView.ViewHolder{
        static TextView reply_header;
        static TextView reply_user;
        static Button reply_more;

        public MyReplyViewHolder(View itemView) {
            super(itemView);
            reply_header=itemView.findViewById(R.id.my_reply_content);
            reply_user=itemView.findViewById(R.id.my_reply_user);
            reply_more=itemView.findViewById(R.id.my_reply_more);

            reply_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO 进入帖子详情界面
                }
            });
        }
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
//        if (mOnItemClickListener != null) {
//            //注意这里使用getTag方法获取position
//            mOnItemClickListener.onItemClick(v, (int) v.getTag());
//        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
