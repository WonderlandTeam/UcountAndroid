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
import team.wonderland.ucount.ucount_android.util.Budget;

/**
 * Created by liuyu on 2017/9/3.
 */

public class PlanBudgetRecyclerAdapter extends RecyclerView.Adapter<PlanBudgetRecyclerAdapter.PlanBudgetViewHolder>
        implements View.OnClickListener{

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
        v.setOnClickListener(this);
        return nvh;
    }


    @Override
    public void onBindViewHolder(PlanBudgetRecyclerAdapter.PlanBudgetViewHolder holder, int position) {
        PlanBudgetViewHolder.icon.setImageResource(GlobalVariables.getSrcID(budgets.get(position).getConsumeType()));
        PlanBudgetViewHolder.typename.setText(budgets.get(position).getConsumeType());
        PlanBudgetViewHolder.num.setText(String.valueOf(budgets.get(position).getRemain()));

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
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

