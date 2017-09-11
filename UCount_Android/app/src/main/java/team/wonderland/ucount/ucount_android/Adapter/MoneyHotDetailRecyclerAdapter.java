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
import team.wonderland.ucount.ucount_android.json.PostReplyJson;

/**
 * Created by liuyu on 2017/8/31.
 */

public class MoneyHotDetailRecyclerAdapter extends RecyclerView.Adapter<MoneyHotDetailRecyclerAdapter.MoneyHotViewHolder> {
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    private List<PostReplyJson> posts;
    private static View mHeaderView;
    private static View mFooterView;
    private Context context;


    public MoneyHotDetailRecyclerAdapter(List<PostReplyJson> posts, Context context) {
        this.posts = posts;
        this.context=context;
    }

    @Override
    public MoneyHotDetailRecyclerAdapter.MoneyHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) {
            return new MoneyHotDetailRecyclerAdapter.MoneyHotViewHolder(mHeaderView);
        }
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new MoneyHotDetailRecyclerAdapter.MoneyHotViewHolder(mFooterView);
        }

        View v= LayoutInflater.from(context).inflate(R.layout.money_hot_remark_item,parent,false);
        MoneyHotDetailRecyclerAdapter.MoneyHotViewHolder nvh=new MoneyHotDetailRecyclerAdapter.MoneyHotViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(MoneyHotDetailRecyclerAdapter.MoneyHotViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_NORMAL){
            if(holder instanceof MoneyHotViewHolder) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                ((MoneyHotViewHolder) holder).name.setText(posts.get(position-1).getUsername());
                ((MoneyHotViewHolder) holder).date.setText(posts.get(position-1).getTime());
                ((MoneyHotViewHolder) holder).content.setText(posts.get(position-1).getContent());
                return;
            }
            return;
        }else if(getItemViewType(position) == TYPE_HEADER){

            return;
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return posts.size();
        }else if(mHeaderView == null && mFooterView != null){
            return posts.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return posts.size() + 1;
        }else {
            return posts.size() + 2;
        }
    }

    static class MoneyHotViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name;
        TextView date;
        TextView content;


        public MoneyHotViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView){
                return;
            }
            if (itemView == mFooterView){
                return;
            }
            cardView = (CardView)itemView.findViewById(R.id.money_hot_remark_cardview);
            name = (TextView)itemView.findViewById(R.id.money_hot_remark_name);
            date = (TextView)itemView.findViewById(R.id.money_hot_remark_date);
            content = (TextView)itemView.findViewById(R.id.money_hot_remark_content);
            
        }
    }

    //HeaderView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }
        if (position == 0){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

}
