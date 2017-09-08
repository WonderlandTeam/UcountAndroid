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
public class MyPraiseRecyclerAdapter extends RecyclerView.Adapter<MyPraiseRecyclerAdapter.MyPraiseViewHolder> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;
    private List<MyPost> posts;
    private Context context;

    public MyPraiseRecyclerAdapter(List<MyPost> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public MyPraiseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.my_praise_item,parent,false);
        MyPraiseViewHolder myPraiseViewHolder=new MyPraiseViewHolder(v);
        v.setOnClickListener(this);
        return myPraiseViewHolder;
    }

    @Override
    public void onBindViewHolder(MyPraiseViewHolder holder, int position) {
        MyPraiseViewHolder.praise_header.setText(posts.get(position).getHeader());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class MyPraiseViewHolder extends RecyclerView.ViewHolder{
        static TextView praise_header;
        static Button praise_more;

        public MyPraiseViewHolder(View itemView) {
            super(itemView);
            praise_header=itemView.findViewById(R.id.my_praise_title);
            praise_more=itemView.findViewById(R.id.my_praise_more);
            praise_more.setOnClickListener(new View.OnClickListener() {
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
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
