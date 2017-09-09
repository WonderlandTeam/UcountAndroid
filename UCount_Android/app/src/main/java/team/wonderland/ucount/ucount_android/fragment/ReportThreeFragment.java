package team.wonderland.ucount.ucount_android.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.rest.spring.annotations.RestService;
import team.wonderland.ucount.ucount_android.Adapter.ReportTimelineAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.MoneyFlow;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.CashFlowJson;
import team.wonderland.ucount.ucount_android.service.StatementService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by CLL on 17/9/2.
 */
@EFragment
public class ReportThreeFragment extends Fragment {
    private ListView listView;
    private List<MoneyFlow> moneyFlows;
    private ReportTimelineAdapter reportTimelineAdapter;
    private String userName;
    @RestService
    StatementService statementService;
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

        SharedPreferences preferences=getActivity().getSharedPreferences("user",0);
        userName=preferences.getString("USERNAME","sigma");
        Log.i("userName",userName);

        initData();
        moneyFlows=new ArrayList<>();

        reportTimelineAdapter=new ReportTimelineAdapter(getContext(),moneyFlows);
        listView.setAdapter(reportTimelineAdapter);

        return view;
    }

    @Background
    void initData(){
        //stub data
        moneyFlows.add(new MoneyFlow(true,1000,"工资","2017-8-1", R.drawable.type_get_2));
        moneyFlows.add(new MoneyFlow(false,50,"支付宝","2017-8-4",R.mipmap.alipay));
        moneyFlows.add(new MoneyFlow(false,100,"银行卡","2017-8-5",R.mipmap.card_pay));
        moneyFlows.add(new MoneyFlow(true,1000,"工资","2017-8-1", R.drawable.type_get_2));
        moneyFlows.add(new MoneyFlow(false,50,"支付宝","2017-8-4",R.mipmap.alipay));
        moneyFlows.add(new MoneyFlow(false,100,"银行卡","2017-8-5",R.mipmap.card_pay));
        moneyFlows.add(new MoneyFlow(true,1000,"工资","2017-8-1", R.drawable.type_get_2));
        moneyFlows.add(new MoneyFlow(false,50,"支付宝","2017-8-4",R.mipmap.alipay));
        moneyFlows.add(new MoneyFlow(false,100,"银行卡","2017-8-5",R.mipmap.card_pay));
        //TODO get data from server
        try {
            Map<String,Object> contents=statementService.getCashFlowsStatement(userName,"2017-6-1","2017-6-2");
            String json=contents.get("content").toString();
            Log.i("json",json);
            Gson gson = new Gson();
            Type type = new TypeToken<List<CashFlowJson>>() {
            }.getType();
            List<CashFlowJson> cashFlowJsons=gson.fromJson(json, type);
            if(cashFlowJsons!=null) {
                for (CashFlowJson cashFlowJson : cashFlowJsons) {

                }
            }
            else{
                Log.i("list",null);
            }
        } catch (ResponseException e) {
            Log.i("error",e.getMessage());
            showErrorInfo(e.getMessage());
        }

    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Looper.prepare();
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        Looper.loop();

    }
}
