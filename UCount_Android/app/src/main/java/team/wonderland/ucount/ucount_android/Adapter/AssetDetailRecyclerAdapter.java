package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
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
import team.wonderland.ucount.ucount_android.fragment.AssetItem;

/**
 * Created by liuyu on 2017/8/30.
 */

public class AssetDetailRecyclerAdapter extends RecyclerView.Adapter<AssetDetailRecyclerAdapter.AccountViewHolder> {

    private List<AssetItem> assetItems;
    private Context context;

    public AssetDetailRecyclerAdapter(List<AssetItem> assetItems,Context context) {
        this.assetItems = assetItems;
        this.context=context;
    }

    @Override
    public AssetDetailRecyclerAdapter.AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.asset_detail_item,parent,false);
        AssetDetailRecyclerAdapter.AccountViewHolder nvh=new AssetDetailRecyclerAdapter.AccountViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(AssetDetailRecyclerAdapter.AccountViewHolder holder, int position) {
        final int j=position;

        AccountViewHolder.date.setText(assetItems.get(position).getDate());
        AssetDetailRecyclerAdapter.AccountViewHolder.type.setText(assetItems.get(position).getType());
        AssetDetailRecyclerAdapter.AccountViewHolder.num.setText(String.valueOf(assetItems.get(position).getNum()));

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

        public AccountViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.asset_detail_item_cardview);
            date = (TextView)itemView.findViewById(R.id.asset_detail_item_tv_date);
            type = (TextView)itemView.findViewById(R.id.asset_detail_item_tv_type);
            num = (TextView)itemView.findViewById(R.id.asset_detail_item_tv_num);
        }
    }
}

