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
public class MyCollectionRecyclerAdapter extends RecyclerView.Adapter<MyCollectionRecyclerAdapter.MyCollectionViewHolder> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;
    private List<MyPost> posts;
    private Context context;

    public MyCollectionRecyclerAdapter(List<MyPost> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public MyCollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.my_collection_item,parent,false);
        MyCollectionViewHolder myCollectionViewHolder=new MyCollectionViewHolder(v);
        v.setOnClickListener(this);
        return myCollectionViewHolder;
    }

    @Override
    public void onBindViewHolder(MyCollectionViewHolder holder, int position) {
        MyCollectionViewHolder.collection_header.setText(posts.get(position).getHeader());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class MyCollectionViewHolder extends RecyclerView.ViewHolder{
        static TextView collection_header;
        static Button collection_more;

        public MyCollectionViewHolder(View itemView) {
            super(itemView);
            collection_header=itemView.findViewById(R.id.my_collection_title);
            collection_more=itemView.findViewById(R.id.my_collection_more);
            collection_more.setOnClickListener(new View.OnClickListener() {
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
