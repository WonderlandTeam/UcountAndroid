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

public class AssetRecyclerAdapter extends RecyclerView.Adapter<AssetRecyclerAdapter.AccountViewHolder> {

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
        return nvh;
    }

    @Override
    public void onBindViewHolder(final AssetRecyclerAdapter.AccountViewHolder holder,final int position) {
        final int j=position;

        AccountViewHolder.accountImg.setImageResource(GlobalVariables.getSrcID(accounts.get(position).getType()));
        AccountViewHolder.accountName.setText(accounts.get(position).getCardID());
        AccountViewHolder.accountTotal.setText(String.valueOf(String.valueOf(accounts.get(position).getBalance())));

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
        void onItemLongOnClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void removeItem(int pos){
        accounts.remove(pos);
        notifyItemRemoved(pos);
    }

}
