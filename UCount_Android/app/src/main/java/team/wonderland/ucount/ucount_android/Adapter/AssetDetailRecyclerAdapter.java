package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.activity.MainActivity;
import team.wonderland.ucount.ucount_android.fragment.AssetItem;
import team.wonderland.ucount.ucount_android.fragment.GlobalVariables;
import team.wonderland.ucount.ucount_android.json.BillInfoJson;

/**
 * Created by liuyu on 2017/8/30.
 */

public class AssetDetailRecyclerAdapter extends RecyclerView.Adapter<AssetDetailRecyclerAdapter.AccountViewHolder> {

    private List<BillInfoJson> assetItems;
    private Context context;
    private String[] earns;
    private OnItemClickListener mOnItemClickListener=null;

    public AssetDetailRecyclerAdapter(List<BillInfoJson> assetItems,Context context) {
        this.assetItems = assetItems;
        this.context=context;

        Resources resources = MainActivity.resources;
        earns = resources.getStringArray(R.array.earn);
    }

    @Override
    public AssetDetailRecyclerAdapter.AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.asset_detail_item,parent,false);
        AssetDetailRecyclerAdapter.AccountViewHolder nvh=new AssetDetailRecyclerAdapter.AccountViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(final AssetDetailRecyclerAdapter.AccountViewHolder holder,final int position) {
        final int j=position;

        AccountViewHolder.date.setText(assetItems.get(position).getTime());
        String type = assetItems.get(position).getType();
        AssetDetailRecyclerAdapter.AccountViewHolder.type.setText(type);
        boolean isEarn = false;
        for(int i=0;i<earns.length;i++){
            if(type.equals(earns[i])){
                isEarn = true;
                break;
            }
        }
        if(isEarn){
            AssetDetailRecyclerAdapter.AccountViewHolder.num.setTextColor(MainActivity.resources.getColor(R.color.text_red));
        }
        AssetDetailRecyclerAdapter.AccountViewHolder.num.setText(String.valueOf(assetItems.get(position).getAmount()));
        AccountViewHolder.description.setText(assetItems.get(position).getTrader());
        AccountViewHolder.img.setImageResource(GlobalVariables.getSrcID(assetItems.get(position).getType()));

        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onItemLongOnClick(holder.itemView,position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return assetItems.size();
    }

    static class AccountViewHolder extends RecyclerView.ViewHolder{
        static CardView cardView;
        static TextView date;
        static TextView type;
        static TextView num;
        static TextView description;
        static ImageView img;

        public AccountViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.asset_detail_item_cardview);
            date = (TextView)itemView.findViewById(R.id.asset_detail_item_tv_date);
            type = (TextView)itemView.findViewById(R.id.asset_detail_item_tv_type);
            num = (TextView)itemView.findViewById(R.id.asset_detail_item_tv_num);
            description = (TextView)itemView.findViewById(R.id.asset_detail_item_tv_description);
            img = (ImageView)itemView.findViewById(R.id.asset_detail_item_img);
        }
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
        void onItemLongOnClick(View view, int position);
    }

    public void setOnItemClickListener(AssetDetailRecyclerAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void removeItem(int pos){
        assetItems.remove(pos);
        notifyItemRemoved(pos);
    }
}

