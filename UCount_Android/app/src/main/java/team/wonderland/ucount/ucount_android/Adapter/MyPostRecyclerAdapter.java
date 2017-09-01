package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.MyPost;

import java.util.List;

/**
 * Created by CLL on 17/9/1.
 */
public class MyPostRecyclerAdapter extends RecyclerView.Adapter<MyPostRecyclerAdapter.MyPostViewHolder> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;
    private List<MyPost> posts;
    private Context context;

    public MyPostRecyclerAdapter(List<MyPost> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public MyPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.my_post_item,parent,false);
        MyPostViewHolder myPostViewHolder=new MyPostViewHolder(v);
        v.setOnClickListener(this);
        return myPostViewHolder;
    }

    @Override
    public void onBindViewHolder(MyPostViewHolder holder, int position) {
        MyPostViewHolder.post_header.setText(posts.get(position).getHeader());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class MyPostViewHolder extends RecyclerView.ViewHolder{
        static TextView post_header;
        static Button post_more;

        public MyPostViewHolder(View itemView) {
            super(itemView);
            post_header=itemView.findViewById(R.id.my_post_title);
            post_more=itemView.findViewById(R.id.my_post_more);
            post_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO
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
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
