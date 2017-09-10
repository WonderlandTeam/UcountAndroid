package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.fragment.GlobalVariables;
import team.wonderland.ucount.ucount_android.json.AccountInfoJson;

/**
 * Created by liuyu on 2017/8/23.
 */

public class AssetRecyclerAdapter extends RecyclerView.Adapter<AssetRecyclerAdapter.AccountViewHolder>
        implements View.OnClickListener{

    private List<AccountInfoJson> accounts;
    private Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public AssetRecyclerAdapter(List<AccountInfoJson> accounts,Context context) {
        this.accounts = accounts;
        this.context=context;
    }

    @Override
    public AssetRecyclerAdapter.AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.asset_item,parent,false);
        AccountViewHolder nvh=new AccountViewHolder(v);
        v.setOnClickListener(this);
        return nvh;
    }

    @Override
    public void onBindViewHolder(AssetRecyclerAdapter.AccountViewHolder holder, int position) {
        final int j=position;

        AccountViewHolder.accountImg.setImageResource(GlobalVariables.getSrcID(accounts.get(position).getType()));
        AccountViewHolder.accountName.setText(accounts.get(position).getCardID());
        AccountViewHolder.accountTotal.setText(String.valueOf(String.valueOf(accounts.get(position).getBalance())));

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    static class AccountViewHolder extends RecyclerView.ViewHolder{
        static CardView cardView;
        static ImageView accountImg;
        static TextView accountName;
        static TextView accountTotal;

        public AccountViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.asset_item_cardview);
            accountImg = (ImageView)itemView.findViewById(R.id.asset_img_account);
            accountName = (TextView)itemView.findViewById(R.id.asset_txt_accountname);
            accountTotal = (TextView)itemView.findViewById(R.id.asset_txt_accounttotal);
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
