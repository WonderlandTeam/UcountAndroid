package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import team.wonderland.ucount.ucount_android.Adapter.ReportTimelineAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.MoneyFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CLL on 17/9/2.
 */
public class ReportThreeFragment extends Fragment {
    private ListView listView;
    private List<MoneyFlow> moneyFlows;
    private ReportTimelineAdapter reportTimelineAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_three, container, false);
        listView=view.findViewById(R.id.timeLine);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO
            }
        });
        listView.setDividerHeight(0);
        initData();
        reportTimelineAdapter=new ReportTimelineAdapter(getContext(),moneyFlows);
        listView.setAdapter(reportTimelineAdapter);

        return view;
    }

    void initData(){
        moneyFlows=new ArrayList<>();
        moneyFlows.add(new MoneyFlow(true,1000,"工资","2017-8-1", R.drawable.type_get_2));
        moneyFlows.add(new MoneyFlow(false,50,"支付宝","2017-8-4",R.mipmap.alipay));
        moneyFlows.add(new MoneyFlow(false,100,"银行卡","2017-8-5",R.mipmap.card_pay));
        moneyFlows.add(new MoneyFlow(true,1000,"工资","2017-8-1", R.drawable.type_get_2));
        moneyFlows.add(new MoneyFlow(false,50,"支付宝","2017-8-4",R.mipmap.alipay));
        moneyFlows.add(new MoneyFlow(false,100,"银行卡","2017-8-5",R.mipmap.card_pay));
        moneyFlows.add(new MoneyFlow(true,1000,"工资","2017-8-1", R.drawable.type_get_2));
        moneyFlows.add(new MoneyFlow(false,50,"支付宝","2017-8-4",R.mipmap.alipay));
        moneyFlows.add(new MoneyFlow(false,100,"银行卡","2017-8-5",R.mipmap.card_pay));
    }
}
