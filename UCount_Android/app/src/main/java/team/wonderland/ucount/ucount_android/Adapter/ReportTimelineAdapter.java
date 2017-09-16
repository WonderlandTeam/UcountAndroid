package team.wonderland.ucount.ucount_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.MoneyFlow;

import java.util.List;

/**
 * Created by CLL on 17/9/2.
 */
public class ReportTimelineAdapter extends BaseAdapter {
    private Context context;
    private List<MoneyFlow> moneyFlowList;
    /**
     * 布局渲染器
     **/
    private LayoutInflater inflater;


    public ReportTimelineAdapter(Context context, List<MoneyFlow> moneyFlowList) {
        super();
        this.context = context;
        this.moneyFlowList = moneyFlowList;
    }

    @Override
    public int getCount() {
        return moneyFlowList.size();
    }

    @Override
    public Object getItem(int i) {
        return moneyFlowList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ReportTimelineViewHolder reportTimelineViewHolder = null;
        inflater = LayoutInflater.from(viewGroup.getContext());
        if (null == view) {
            boolean tag = moneyFlowList.get(i).isIn();
            //收入
            if (tag) {
                // 加载左边布局
                view = inflater.inflate(R.layout.report_timeline_left_item, null);
                // 创建一个VIewHolder用以缓存
                reportTimelineViewHolder = new ReportTimelineViewHolder(view, true);

                // 设置holder标签
                view.setTag(reportTimelineViewHolder);
            }
            //支出
            else {
                view = inflater.inflate(R.layout.report_timeline_right_item, null);
                // 创建一个VIewHolder用以缓存
                reportTimelineViewHolder = new ReportTimelineViewHolder(view, false);

                // 设置holder标签
                view.setTag(reportTimelineViewHolder);
            }
        }
        else {// 如果不为空，就直接从缓存中去取
            reportTimelineViewHolder =  (ReportTimelineViewHolder) view.getTag();//本来直接这样取是没有问题的，但是会发现有重叠的bug

            // 所以接下来的处理是为了解决会出现的bug，如果有同学不明白的话，可以进下面的注释掉，然后运行一遍，就会发现问题了
            // 如何来解释这个呢？如果一屏幕有三个item，那么holder就将缓存三个item，到第四个的时候，
            // 就会将holder中原来缓存的第一个去覆盖第四个，这样就会出现，本来应该是右边出现的布局跑到了左边，还跟下面出现的布局重合的现象
            // 建议注释掉之后看效果，这样有点讲不明白
            if (moneyFlowList.get(i).isIn() && !reportTimelineViewHolder.isLeftOrRight) {
                // 加载左边布局
                view = inflater.inflate(R.layout.report_timeline_left_item, null);
                // 创建一个VIewHolder用以缓存
                reportTimelineViewHolder = new ReportTimelineViewHolder(view,true);
                reportTimelineViewHolder.type = (TextView) view
                        .findViewById(R.id.type);
                reportTimelineViewHolder.time = (TextView) view
                        .findViewById(R.id.time);
                reportTimelineViewHolder.img_center = (ImageView) view
                        .findViewById(R.id.img_center);


                // 设置holder标签
                view.setTag(reportTimelineViewHolder);
            }else if(!moneyFlowList.get(i).isIn()&& reportTimelineViewHolder.isLeftOrRight){
                // 加载右边布局
                view = inflater.inflate(R.layout.report_timeline_right_item, null);
                // 创建一个VIewHolder用以缓存
                reportTimelineViewHolder = new ReportTimelineViewHolder(view,true);
                reportTimelineViewHolder.type = (TextView) view
                        .findViewById(R.id.type);
                reportTimelineViewHolder.time = (TextView) view
                        .findViewById(R.id.time);
                reportTimelineViewHolder.img_center = (ImageView) view
                        .findViewById(R.id.img_center);

                // 设置holder标签
                view.setTag(reportTimelineViewHolder);
            }else{

            }
        }
        String description="";
        // 先遍历列表中的对象，一个个来填充
        if(moneyFlowList.get(i).isIn()){
            description=description+moneyFlowList.get(i).getType()+""+moneyFlowList.get(i).getMoney()+"元";
        }
        else{
            description=description+moneyFlowList.get(i).getType()+" 支出"+moneyFlowList.get(i).getMoney()+"元";
        }
        // 下面填充数据
        reportTimelineViewHolder.type.setText(description);
        reportTimelineViewHolder.time.setText(moneyFlowList.get(i).getTime());
        reportTimelineViewHolder.img_center.setImageResource(moneyFlowList.get(i).getImg_id());


        return view;
    }

    public class ReportTimelineViewHolder {
        TextView type;
        TextView time;
        ImageView img_center;
        boolean isLeftOrRight;

        public ReportTimelineViewHolder(View itemView, boolean isLeftOrRight) {
            super();
            this.isLeftOrRight = isLeftOrRight;
            type = itemView.findViewById(R.id.type);
            time = itemView.findViewById(R.id.time);
            img_center = itemView.findViewById(R.id.img_center);
        }
    }
}
