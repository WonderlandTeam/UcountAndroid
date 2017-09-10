package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.Message;

import java.util.List;

/**
 * Created by CLL on 17/9/1.
 */
public class MyMessageRecyclerAdapter extends RecyclerView.Adapter<MyMessageRecyclerAdapter.MyMessageViewHolder>
        implements View.OnClickListener {
    private OnItemClickListener mOnItemClickListener = null;
    private List<Message> messages;
    private Context context;

    public MyMessageRecyclerAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public MyMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.my_message_item,parent,false);
        MyMessageViewHolder myMessageViewHolder=new MyMessageViewHolder(v);
        v.setOnClickListener(this);
        return myMessageViewHolder;
    }

    @Override
    public void onBindViewHolder(MyMessageViewHolder holder, int position) {
        MyMessageViewHolder.message_img.setImageResource(messages.get(position).getImgId());
        MyMessageViewHolder.message_header.setText(messages.get(position).getHeader());
        MyMessageViewHolder.message_context.setText(messages.get(position).getContext());
        MyMessageViewHolder.message_time.setText(messages.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MyMessageViewHolder extends RecyclerView.ViewHolder {
        static ImageView message_img;
        static CardView cardView;
        static TextView message_header;
        static TextView message_context;
        static TextView message_time;

        public MyMessageViewHolder(View itemView) {
            super(itemView);
            message_img=itemView.findViewById(R.id.message_item_img);
            cardView=itemView.findViewById(R.id.message_item_card);
            message_header=itemView.findViewById(R.id.message_item_header);
            message_context=itemView.findViewById(R.id.message_item_context);
            message_time=itemView.findViewById(R.id.message_item_time);
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
