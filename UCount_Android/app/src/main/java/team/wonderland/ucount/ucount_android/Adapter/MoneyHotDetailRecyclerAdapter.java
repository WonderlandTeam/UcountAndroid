package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.fragment.Post;

/**
 * Created by liuyu on 2017/9/2.
 */

public class MoneyHotDetailRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static enum ITEM_TYPE{
        ITEM_TYPE_POST,
        ITEM_TYPE_REMARK;
    }

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Post> posts;

    public MoneyHotDetailRecyclerAdapter(Context context,List<Post> posts){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.posts = posts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_TYPE_POST.ordinal()){
            return new PostViewHolder(mLayoutInflater.inflate(R.layout.money_hot_article_item,parent,false));
        }else{
            return new PostViewHolder(mLayoutInflater.inflate(R.layout.money_hot_remark_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //TODO:在这里加载组件要显示的内容
        if(holder instanceof PostViewHolder){
//            ((Item1ViewHolder) holder).mTextView.setText(titles[position]);
        }else if(holder instanceof RemarkViewHolder){

        }
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0){
            return ITEM_TYPE.ITEM_TYPE_POST.ordinal();
        }else{
            return ITEM_TYPE.ITEM_TYPE_REMARK.ordinal();
        }
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{
        public PostViewHolder(View itemView){
            super(itemView);
        }
    }

    public static class RemarkViewHolder extends RecyclerView.ViewHolder{
        public RemarkViewHolder(View itemView){
            super(itemView);
        }
    }
}
