package team.wonderland.ucount.ucount_android.fragment;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.melnykov.fab.FloatingActionButton;


import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.rest.spring.annotations.RestService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import team.wonderland.ucount.ucount_android.Adapter.PlanBudgetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.BudgetAddJson;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;
import team.wonderland.ucount.ucount_android.json.BudgetModifyJson;
import team.wonderland.ucount.ucount_android.service.BudgetService;
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

    private BudgetInfoJson totalBudgetInfoJson;
//    private BudgetAddJson totalBudgetAddJson;
//    private BudgetModifyJson totalBudgetModifyJson;

    private double totalnum;

    private String username;
    private String date;



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
        mPercentageRing.setTargetPercent(0);
        totalTextView.setText("点击设置");


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
            }else{
                for(int i=0;i<budgets.size();i++){
                    if(budgets.get(i).getConsumeType().equals("总预算")){
                        totalBudgetInfoJson = budgets.get(i);
                        budgets.remove(i);
                    }else{
                        totalBudgetInfoJson = null;
                    }
                }
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

        //跳转到查看预算界面
        adapter.setOnItemClickListener(new PlanBudgetRecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                Bundle bundle = new Bundle();
                Fragment fragment = new PlanBudgetReviewFragment_();
                bundle.putSerializable("budgetInfo",budgets.get(position));
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.plan_fragment_container, fragment)
                        .commit();
            }
        });

        newBudget.attachToRecyclerView(recyclerView);

        if(totalBudgetInfoJson!=null){
            mPercentageRing.setTargetPercent((int)(totalBudgetInfoJson.getRemain()/totalBudgetInfoJson.getBudgetMoney()));
            totalTextView.setText(String.valueOf(totalBudgetInfoJson.getRemain()));
        }
//
//        mPercentageRing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                if(totalBudgetInfoJson==null){
//                    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
//                    View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
//                    localBuilder.setView(dialog);
//                    TextView tv = (TextView) dialog.findViewById(R.id.asset_new_dialog_tv);
//                    final EditText et_num = (EditText) dialog.findViewById(R.id.asset_new_dialog_et);
//                    et_num.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
//                    tv.setText("请输入金额");
//                    localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//                    {
//                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//                        {
//                            totalnum = Double.parseDouble(et_num.getText().toString());
//                            username = getActivity().getSharedPreferences("user", 0).getString("USERNAME", "");
//                            date = dateTextView.getText().toString().replace(",", "-").replace("月", "");
//                            totalBudgetAddJson = new BudgetAddJson(username,"总预算",totalnum,date);
//                            new bTask().execute();
//                            paramAnonymousDialogInterface.dismiss();
//                        }
//                    }).setNegativeButton("取消", new DialogInterface.OnClickListener()
//                    {
//                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//                        {
//                            paramAnonymousDialogInterface.dismiss();
//                        }
//                    }).create().show();
//                }else {
//                    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
//                    View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
//                    localBuilder.setView(dialog);
//                    TextView tv = (TextView) dialog.findViewById(R.id.asset_new_dialog_tv);
//                    final EditText et_num = (EditText) dialog.findViewById(R.id.asset_new_dialog_et);
//                    et_num.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
//                    tv.setText("总预算修改为");
//                    localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//                    {
//                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//                        {
//                            totalnum = Double.parseDouble(et_num.getText().toString());
//                            BudgetModifyJson budgetModifyJson = new BudgetModifyJson();
//                            reviewTotalBudget(totalBudgetInfoJson.getId(),budgetModifyJson);
//                            paramAnonymousDialogInterface.dismiss();
//                        }
//                    }).setNegativeButton("取消", new DialogInterface.OnClickListener()
//                    {
//                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//                        {
//                            paramAnonymousDialogInterface.dismiss();
//                        }
//                    }).create().show();
//                }
//            }
//        });
    }

    void reviewTotalBudget(Long id,BudgetModifyJson budgetModifyJson){
        try {
            Map<String, Object> result = budgetService.updateBudget(id, budgetModifyJson);

        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    void newTotalBudget(BudgetAddJson budgetAddJson){
        try {
            Map<String, Object> result = budgetService.addBudget(budgetAddJson);
            newTotalBudgetSuccess(budgetAddJson);
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    void reviewTotalBudgetSuccess(BudgetInfoJson totalBudgetInfoJson,BudgetModifyJson budgetModifyJson){
        Looper.prepare();
        double total = budgetModifyJson.getMoney();
        double consum = totalBudgetInfoJson.getConsume();
        mPercentageRing.setTargetPercent((int) ((total-consum)/total) );
        totalTextView.setText(String.valueOf(total));
        Looper.loop();
    }

    void newTotalBudgetSuccess(BudgetAddJson budgetAddJson){
        Looper.prepare();
        mPercentageRing.setTargetPercent(100);
        totalTextView.setText(String.valueOf(budgetAddJson.getConsumeMoney()));
        Looper.loop();
    }

    //显示错误信息
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
