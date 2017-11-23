package team.wonderland.ucount.ucount_android.fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;
import team.wonderland.ucount.ucount_android.Adapter.ReportTimelineAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.MoneyFlow;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.CashFlowJson;
import team.wonderland.ucount.ucount_android.service.StatementService;
import team.wonderland.ucount.ucount_android.util.MoneyFlowType;

import java.util.ArrayList;
import java.util.List;

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


        SharedPreferences preferences=getActivity().getSharedPreferences("user",0);
        userName=preferences.getString("USERNAME","sigma");
        Log.i("userName",userName);

        moneyFlows=new ArrayList<>();

        new aTask().execute();



        return view;
    }

    void initListView(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO
            }
        });
        listView.setDividerHeight(0);
        reportTimelineAdapter=new ReportTimelineAdapter(getContext(),moneyFlows);
        listView.setAdapter(reportTimelineAdapter);
    }

    void initData(){
        //stub data
//        moneyFlows.add(new MoneyFlow(true,1000,"工资","2017-8-1", R.drawable.type_get_2));
//        moneyFlows.add(new MoneyFlow(false,50,"支付宝","2017-8-4",R.mipmap.alipay));
//        moneyFlows.add(new MoneyFlow(false,100,"银行卡","2017-8-5",R.mipmap.card_pay));
//        moneyFlows.add(new MoneyFlow(true,1000,"工资","2017-8-1", R.drawable.type_get_2));
//        moneyFlows.add(new MoneyFlow(false,50,"支付宝","2017-8-4",R.mipmap.alipay));
//        moneyFlows.add(new MoneyFlow(false,100,"银行卡","2017-8-5",R.mipmap.card_pay));
//        moneyFlows.add(new MoneyFlow(true,1000,"工资","2017-8-1", R.drawable.type_get_2));
//        moneyFlows.add(new MoneyFlow(false,50,"支付宝","2017-8-4",R.mipmap.alipay));
//        moneyFlows.add(new MoneyFlow(false,100,"银行卡","2017-8-5",R.mipmap.card_pay));

        //get data from server
        try {
            List<CashFlowJson> cashFlowJsons = statementService.getCashFlowsStatement(userName, "2017-09-01", "2017-11-23");
//            String json=contents.get("content").toString();
//            Log.i("json",json);
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<CashFlowJson>>() {
//            }.getType();
//            List<CashFlowJson> cashFlowJsons=gson.fromJson(json, type);
//            Log.i("jsonSize","" + cashFlowJsons.size());
            if(cashFlowJsons != null) {
                for (CashFlowJson cashFlowJson : cashFlowJsons) {
                    moneyFlows.add(new MoneyFlow(isIn(cashFlowJson.getBillType()),cashFlowJson.getMoney(),cashFlowJson.getBillType(),
                            cashFlowJson.getTime(),getIconID(cashFlowJson.getBillType())));
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

    private class aTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void o) {
            super.onPostExecute(o);
            initListView();
            Log.i("tag", "调用后");
            reportTimelineAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            initData();
            Log.i("tag", "调用后台数据");
            return null;
        }
    }

    private int getIconID(String billType){
        int icon_id=R.drawable.type_get_1;
        switch(MoneyFlowType.stringToBillType(billType)){

            case SALARY:
                icon_id=R.drawable.type_get_3;
                break;
            case MANAGEMENT_INCOME:
                icon_id=R.drawable.type_get_4;
                break;
            case ALIMONY:
                icon_id=R.drawable.type_get_2;
                break;
            case OTHER_INCOME:
                icon_id=R.drawable.type_get_1;
                break;
            case COMMODITY:
                icon_id=R.drawable.type_cost_18;
                break;
            case UTILITIES:
                icon_id=R.drawable.type_cost_19;
                break;
            case COMMUNICATION:
                icon_id=R.drawable.type_cost_18;
                break;
            case DIET:
                icon_id=R.drawable.type_cost_1;
                break;
            case ELECTRONIC:
                icon_id=R.drawable.type_cost_3;
                break;
            case TRAFFIC:
                icon_id=R.drawable.type_cost_6;
                break;
            case CLOTHING:
                icon_id=R.drawable.type_cost_7;
                break;
            case CREAM:
                icon_id=R.drawable.type_cost_8;
                break;
            case COSMETICS:
                icon_id=R.drawable.type_cost_9;
                break;
            case JEWELRY:
                icon_id=R.drawable.type_cost_10;
                break;
            case TRAINING:
                icon_id=R.drawable.type_cost_11;
                break;
            case BOOK:
                icon_id=R.drawable.type_cost_12;
                break;
            case STATIONERY:
                icon_id=R.drawable.type_cost_13;
                break;
            case PRINT:
                icon_id=R.drawable.type_cost_14;
                break;
            case ACTIVITY:
                icon_id=R.drawable.type_cost_15;
                break;
            case ENTERTAINMENT:
                icon_id=R.drawable.type_cost_15;
                break;
            case MANAGEMENT_EXPENDITURE:
                icon_id=R.drawable.type_get_4;
                break;
            case DONATION:
                icon_id=R.drawable.type_cost_16;
                break;
            case OTHER_DONATION:
                icon_id=R.drawable.type_cost_16;
                break;
            case OTHER_EXPENDITURE:
                icon_id=R.drawable.type_get_1;
                break;
        }
        return icon_id;
    }

    private boolean isIn(String billType){
        switch (MoneyFlowType.stringToBillType(billType)){

            case SALARY:
                return true;
            case MANAGEMENT_INCOME:
                return true;
            case ALIMONY:
                return true;
            case OTHER_INCOME:
                return true;
            case COMMODITY:
                break;
            case UTILITIES:
                break;
            case COMMUNICATION:
                break;
            case DIET:
                break;
            case ELECTRONIC:
                break;
            case TRAFFIC:
                break;
            case CLOTHING:
                break;
            case CREAM:
                break;
            case COSMETICS:
                break;
            case JEWELRY:
                break;
            case TRAINING:
                break;
            case BOOK:
                break;
            case STATIONERY:
                break;
            case PRINT:
                break;
            case ACTIVITY:
                break;
            case ENTERTAINMENT:
                break;
            case MANAGEMENT_EXPENDITURE:
                break;
            case DONATION:
                break;
            case OTHER_DONATION:
                break;
            case OTHER_EXPENDITURE:
                break;
        }
        return false;
    }
}
