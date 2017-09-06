package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.ReportItem;

import java.util.List;

/**
 * Created by CLL on 17/9/6.
 */
public class ReportItemAdapter extends RecyclerView.Adapter<ReportItemAdapter.ReportItemViewHolder> implements View.OnClickListener{
    private List<ReportItem> reportItems;
    private Context context;

    public ReportItemAdapter(List<ReportItem> reportItems, Context context) {
        this.reportItems = reportItems;
        this.context = context;
    }

    @Override
    public ReportItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.report_one_item,parent,false);
        ReportItemViewHolder reportItemViewHolder=new ReportItemViewHolder(v);
        v.setOnClickListener(this);
        return reportItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ReportItemViewHolder holder, int position) {
        Log.i("input",reportItems.get(position).getType());
        ReportItemViewHolder.icon.setImageResource(reportItems.get(position).getIcon_id());
        ReportItemViewHolder.type.setText(reportItems.get(position).getType());
        ReportItemViewHolder.percent.setText(""+reportItems.get(position).getPercent()+"%");
        ReportItemViewHolder.money.setText(""+reportItems.get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return reportItems.size();
    }

    @Override
    public void onClick(View view) {

    }

    static class ReportItemViewHolder extends RecyclerView.ViewHolder{
        static ImageView icon;
        static TextView type;
        static TextView percent;
        static TextView money;

        public ReportItemViewHolder(View itemView) {
            super(itemView);
            icon= itemView.findViewById(R.id.report_item_icon);
            type=itemView.findViewById(R.id.report_item_type);
            percent=itemView.findViewById(R.id.report_item_percent);
            money=itemView.findViewById(R.id.report_item_money);
        }

    }
}
