package team.wonderland.ucount.ucount_android.fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.rest.spring.annotations.RestService;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.BalanceSheetJson;
import team.wonderland.ucount.ucount_android.service.StatementService;
import team.wonderland.ucount.ucount_android.util.ChartTableView;

import java.util.Map;

/**
 * Created by CLL on 17/9/2.
 */
@EFragment
public class ReportTwoFragment extends Fragment {
    private ChartTableView chartTableView;

    private BalanceSheetJson balanceSheetJson;

    private String userName;

    @RestService
    StatementService statementService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_two, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences("user", 0);
        userName = preferences.getString("USERNAME", "sigma");


        chartTableView=view.findViewById(R.id.chartTableView);

        new aTask().execute();
        return view;
    }

    @Background
    void getData(){
        try {
            Map<String, Object> contents = statementService.getBalanceSheet(userName,"2016-10-1","2017-7-1");
            String json = contents.get("content").toString();
            Log.i("json", json);
            // TODO: 17/9/9 转json闪退
            balanceSheetJson = new Gson().fromJson(json, BalanceSheetJson.class);
            if (balanceSheetJson == null) {
                Log.i("balanceSheetJson", "null");
            }

        } catch (ResponseException e) {
            Log.i("error", e.getMessage());
        }
    }

    /**
     * 异步刷新
     */
    private class aTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void o) {
            super.onPostExecute(o);
            if(balanceSheetJson!=null) {
                chartTableView.setData(balanceSheetJson);
                chartTableView.showData();
            }
            else{
                Log.i("balanceSheetJson","null");
            }
            Log.i("tag", "调用后");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getData();
            Log.i("tag", "调用后台数据");
            return null;
        }
    }
}
