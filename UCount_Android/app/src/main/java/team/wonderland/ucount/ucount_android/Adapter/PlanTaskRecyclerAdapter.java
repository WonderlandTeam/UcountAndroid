package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.json.TaskInfoJson;
import team.wonderland.ucount.ucount_android.util.Task;

/**
 * Created by liuyu on 2017/8/31.
 */

public class PlanTaskRecyclerAdapter extends RecyclerView.Adapter<PlanTaskRecyclerAdapter.PlanTaskViewHolder> {


    private List<TaskInfoJson> tasks;
    private Context context;
    private PlanTaskRecyclerAdapter.OnItemClickListener mOnItemClickListener = null;

    public PlanTaskRecyclerAdapter(List<TaskInfoJson> tasks,Context context) {
        this.tasks = tasks;
        this.context=context;
    }

    @Override
    public PlanTaskRecyclerAdapter.PlanTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.plan_task_item,parent,false);
        PlanTaskRecyclerAdapter.PlanTaskViewHolder nvh=new PlanTaskRecyclerAdapter.PlanTaskViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(final PlanTaskRecyclerAdapter.PlanTaskViewHolder holder, final int position) {
        PlanTaskViewHolder.title.setText(tasks.get(position).getTaskContent());
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
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
        return tasks.size();
    }

    static class PlanTaskViewHolder extends RecyclerView.ViewHolder{
        static TextView title;

        public PlanTaskViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.plan_task_item_title);
        }
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
        void onItemLongOnClick(View view, int position);
    }


    public void setOnItemClickListener(PlanTaskRecyclerAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void removeItem(int pos){
        tasks.remove(pos);
        notifyItemRemoved(pos);
    }
}
