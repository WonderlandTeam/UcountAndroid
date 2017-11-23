package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.fragment.GlobalVariables;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;

/**
 * Created by liuyu on 2017/9/3.
 */

public class PlanBudgetRecyclerAdapter extends RecyclerView.Adapter<PlanBudgetRecyclerAdapter.PlanBudgetViewHolder>
        {

    private List<BudgetInfoJson> budgets;
    private Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public PlanBudgetRecyclerAdapter(List<BudgetInfoJson> budgets,Context context) {
        this.budgets = budgets;
        this.context=context;
    }

    @Override
    public PlanBudgetRecyclerAdapter.PlanBudgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.plan_budget_item,parent,false);
        PlanBudgetViewHolder nvh=new PlanBudgetViewHolder(v);
        return nvh;
    }


    @Override
    public void onBindViewHolder(final PlanBudgetRecyclerAdapter.PlanBudgetViewHolder holder, final int position) {
        PlanBudgetViewHolder.icon.setImageResource(GlobalVariables.getSrcID(budgets.get(position).getConsumeType()));
        PlanBudgetViewHolder.typename.setText(budgets.get(position).getConsumeType());
        PlanBudgetViewHolder.num.setText(String.valueOf((int)(budgets.get(position).getRemain() * 100) / 100.0));

        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onItemLongOnClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return budgets.size();
    }

    static class PlanBudgetViewHolder extends RecyclerView.ViewHolder{
        static ImageView icon;
        static TextView typename;
        static TextView num;

        public PlanBudgetViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView)itemView.findViewById(R.id.plan_budget_item_icon);
            typename = (TextView)itemView.findViewById(R.id.plan_budget_item_typename);
            num = (TextView)itemView.findViewById(R.id.plan_budget_item_num);
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
        budgets.remove(pos);
        notifyItemRemoved(pos);
    }
}

