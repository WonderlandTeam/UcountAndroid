package team.wonderland.ucount.ucount_android.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.melnykov.fab.FloatingActionButton;


import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.rest.spring.annotations.RestService;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.PlanBudgetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;
import team.wonderland.ucount.ucount_android.service.BudgetService;
import team.wonderland.ucount.ucount_android.util.Budget;
import team.wonderland.ucount.ucount_android.util.PercentageRing;

/**
 * Created by liuyu on 2017/9/2.
 */

@EFragment(R.layout.plan_budget_fragment)
public class PlanBudgetFragment extends Fragment {

    private PercentageRing mPercentageRing;
    private TextView totalTextView;
    private TextView dateTextView;
    private FloatingActionButton newBudget;
    private RecyclerView recyclerView;
    private PlanBudgetRecyclerAdapter adapter;
    private List<BudgetInfoJson> budgets = new ArrayList<>();

    FragmentActivity fragmentActivity;

    String username;
    String date;

    @RestService
    BudgetService budgetService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_budget_fragment, container, false);

        recyclerView = view.findViewById(R.id.plan_budget_recyclerview);
        newBudget = view.findViewById(R.id.plan_budget_bt_add);
        dateTextView = (TextView) view.findViewById(R.id.plan_budget_date);
        totalTextView = (TextView)view.findViewById(R.id.plan_budget_textview_leftmum);
        mPercentageRing = (PercentageRing) view.findViewById(R.id.plan_budget_circle);


        //设置目标百分比为30
        //TODO:(余额占预算的百分比,从逻辑层获得预算余额) BudgetService.getBudget
        mPercentageRing.setTargetPercent(0);
        totalTextView.setText("点击设置");
        mPercentageRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.plan_fragment_container, new PlanBudgetNewFragment_())
                        .commit();
            }
        });


        final Calendar startDate = Calendar.getInstance();
        startDate.set(2017, 8, 1);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(2037, 11, 31);
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间选择器
                final TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        dateTextView.setText((date.getYear() + 1900) + "," + (date.getMonth() + 1) + "月");

                        new aTask().execute();
                    }
                })
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .setLabel("年", "月", "", "", "", "")
                        .setRangDate(startDate, endDate)
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确认")//确认按钮文字
                        .setCancelColor(getResources().getColor(R.color.text_green))
                        .setSubmitColor(getResources().getColor(R.color.text_green))
                        .setLineSpacingMultiplier(3f)
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });

        newBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.plan_fragment_container, new PlanBudgetNewFragment_())
                        .commit();
            }
        });

        username = getActivity().getSharedPreferences("user", 0).getString("USERNAME", "");
        fragmentActivity = getActivity();

        //调用后台数据并且调用完成后刷新界面
        new aTask().execute();

        return view;
    }

    public void initData() {
        initBudget();//获得所选月份的预算
    }

    /*
     *获得用户某个月份的所有预算
     */
    void initBudget() {
        budgets = new ArrayList<>();
        date = dateTextView.getText().toString().replace(",", "-").replace("月", "");
        try {
            Map<String, Object> result = budgetService.getBudgetsByUser(username, date);
            String content = result.get("content").toString();
            Gson gson = new Gson();
            Type type = new TypeToken<List<BudgetInfoJson>>() {
            }.getType();
            budgets = gson.fromJson(content, type);

            if(budgets==null){
                budgets = new ArrayList<BudgetInfoJson>();
            }

        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
    }

    void initRecyclerView(){
        Log.i("tag","测试");
        Log.i("tag",budgets.toString());

        //设置适配器
        adapter = new PlanBudgetRecyclerAdapter(budgets, getActivity());
        recyclerView.setAdapter(adapter);
        //设置布局管理器 , 将布局设置成纵向
        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linerLayoutManager);

        recyclerView.addItemDecoration(new MyItemDecoration());

        adapter.setOnItemClickListener(new PlanBudgetRecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.plan_fragment_container, new PlanBudgetReviewFragment()).commit();
            }
        });


        newBudget.attachToRecyclerView(recyclerView);
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Looper.prepare();
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        Looper.loop();

    }

    private class aTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPostExecute(Void o) {
            super.onPostExecute(o);
            initRecyclerView();

            Log.i("tag", "调用后");
            Log.i("tag",budgets.toString());


            adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            initBudget();
            Log.i("tag","调用后台数据");
            return null;
        }
    }
}
