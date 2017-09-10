package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.fragment.Post;

/**
 * Created by liuyu on 2017/8/31.
 */

public class MoneyHotRecyclerAdapter extends RecyclerView.Adapter<MoneyHotRecyclerAdapter.MoneyHotViewHolder>
        implements View.OnClickListener{


    private List<Post> posts;
    private Context context;
    private MoneyHotRecyclerAdapter.OnItemClickListener mOnItemClickListener = null;

    public MoneyHotRecyclerAdapter(List<Post> posts,Context context) {
        this.posts = posts;
        this.context=context;
    }

    @Override
    public MoneyHotRecyclerAdapter.MoneyHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.money_hot_recyclerview_item,parent,false);
        MoneyHotRecyclerAdapter.MoneyHotViewHolder nvh=new MoneyHotRecyclerAdapter.MoneyHotViewHolder(v);
        v.setOnClickListener(this);
        return nvh;
    }

    @Override
    public void onBindViewHolder(MoneyHotRecyclerAdapter.MoneyHotViewHolder holder, int position) {
        MoneyHotViewHolder.title.setText(posts.get(position).getTitle());
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class MoneyHotViewHolder extends RecyclerView.ViewHolder{
        static CardView cardView;
        static TextView title;

        public MoneyHotViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.money_hot_recyclerview_item_cardview);
            title = (TextView)itemView.findViewById(R.id.money_hot_recyclerview_item_title);
        }
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void setOnItemClickListener(MoneyHotRecyclerAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
