package team.wonderland.ucount.ucount_android.fragment;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import org.androidannotations.annotations.UiThread;
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
    private BudgetAddJson totalBudgetAddJson;
    private BudgetModifyJson totalBudgetModifyJson;
    private long id;

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
                        initBudget();
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
        initBudget();

        Log.i("tag","planbudgetfragment start");
        return view;
    }

    /*
     *获得用户某个月份的所有预算
     */
    @Background
    void initBudget() {
        budgets = new ArrayList<>();
        date = dateTextView.getText().toString().replace(",", "-").replace("月", "");
        try {
            budgets = budgetService.getBudgetsByUser(username, date);
//            String content = result.get("content").toString();
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<BudgetInfoJson>>() {
//            }.getType();
//            budgets = gson.fromJson(content, type);

            Log.i("tag","测试");
            Log.i("tag",budgets.toString());

            if(budgets==null){
                budgets = new ArrayList<BudgetInfoJson>();
            }else{
                for(int i=0;i<budgets.size();i++){
                    if(budgets.get(i).getConsumeType().equals("总预算")){
                        totalBudgetInfoJson = budgets.get(i);
                        //显示总预算
                        showTotalBudget(totalBudgetInfoJson);

                        budgets.remove(i);
                    }else{
                        totalBudgetInfoJson = null;
                    }
                }
            }
            //显示预算列表
            initRecyclerView();

        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
    }

    @UiThread
    void showTotalBudget(BudgetInfoJson totalBudgetInfoJson){
        mPercentageRing.setTargetPercent((int)(totalBudgetInfoJson.getRemain()/totalBudgetInfoJson.getBudgetMoney()));
        totalTextView.setText(String.valueOf(totalBudgetInfoJson.getRemain()));
        id = totalBudgetInfoJson.getId();

        mPercentageRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                if(totalTextView.getText().equals("点击设置")){
                    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
                    View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
                    localBuilder.setView(dialog);
                    TextView tv = (TextView) dialog.findViewById(R.id.asset_new_dialog_tv);
                    final EditText et_num = (EditText) dialog.findViewById(R.id.asset_new_dialog_et);
                    et_num.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    tv.setText("请输入金额");
                    localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                        {
                            totalnum = Double.parseDouble(et_num.getText().toString());
                            username = getActivity().getSharedPreferences("user", 0).getString("USERNAME", "");
                            date = dateTextView.getText().toString().replace(",", "-").replace("月", "");
                            totalBudgetAddJson = new BudgetAddJson(username,"总预算",totalnum,date);

                            //新建总预算
                            newTotalBudget(totalBudgetAddJson);

                            paramAnonymousDialogInterface.dismiss();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                        {
                            paramAnonymousDialogInterface.dismiss();
                        }
                    }).create().show();
                }else {
                    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
                    View dialog = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
                    localBuilder.setView(dialog);
                    TextView tv = (TextView) dialog.findViewById(R.id.asset_new_dialog_tv);
                    final EditText et_num = (EditText) dialog.findViewById(R.id.asset_new_dialog_et);
                    et_num.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    tv.setText("总预算修改为");
                    localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                        {
                            totalnum = Double.parseDouble(et_num.getText().toString());
                            totalBudgetModifyJson = new BudgetModifyJson(totalnum);

                            reviewTotalBudget(id,totalBudgetModifyJson);

                            paramAnonymousDialogInterface.dismiss();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                        {
                            paramAnonymousDialogInterface.dismiss();
                        }
                    }).create().show();
                }
            }
        });
    }

    @UiThread
    void initRecyclerView(){
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

            @Override
            public void onItemLongOnClick(View view, int position) {
                showPopMenu(view,position);
            }
        });

        newBudget.attachToRecyclerView(recyclerView);
    }

    @Background
    void reviewTotalBudget(long id, BudgetModifyJson totalBudgetModifyJson){
        try {
            String result = budgetService.updateBudget(id, totalBudgetModifyJson);
            reviewTotalBudgetSuccess(totalBudgetModifyJson);
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    @Background
    void newTotalBudget(BudgetAddJson totalBudgetAddJson){
        try {
            Long budgetId = budgetService.addBudget(totalBudgetAddJson);
            newTotalBudgetSuccess();
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    @UiThread
    void reviewTotalBudgetSuccess(BudgetModifyJson totalBudgetModifyJson){
        double total = totalBudgetModifyJson.getMoney();
        totalTextView.setText(String.valueOf(total));
    }

    @UiThread
    void newTotalBudgetSuccess(){
        mPercentageRing.setTargetPercent(100);
        totalTextView.setText(String.valueOf(totalBudgetAddJson.getConsumeMoney()));
    }

    @UiThread
    //显示错误信息
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    public void showPopMenu(View view,final int pos){
        PopupMenu popupMenu = new PopupMenu(getActivity(),view);
        popupMenu.getMenuInflater().inflate(R.menu.item_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                deleteItem(pos);
                adapter.removeItem(pos);
                return false;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getActivity().getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    @Background
    void deleteItem(int pos){
        try {
            String result = budgetService.deleteBudget(budgets.get(pos).getId());
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }
}
