package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.PlanBudgetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.util.Budget;
import team.wonderland.ucount.ucount_android.util.PercentageRing;

/**
 * Created by liuyu on 2017/9/2.
 */

public class PlanBudgetFragment extends Fragment {

    private PercentageRing mPercentageRing;
    private TextView dateTextView;
    private CircleButton newBudget;
    private RecyclerView recyclerView;
    private PlanBudgetRecyclerAdapter adapter;
    private List<Budget> budgets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_budget_fragment, container, false);

        newBudget = view.findViewById(R.id.plan_budget_bt_add);
        dateTextView = (TextView) view.findViewById(R.id.plan_budget_date);
        mPercentageRing = (PercentageRing) view.findViewById(R.id.plan_budget_circle);
        //设置目标百分比为30
        //TODO:余额占预算的百分比
        mPercentageRing.setTargetPercent(30);
        mPercentageRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                        dateTextView.setText((date.getYear()+1900)+","+(date.getMonth()+1)+"月");
                    }
                })
                        .setType(new boolean[]{true,true,false,false,false,false})
                        .setLabel("年","月","","","","")
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
                        .replace(R.id.plan_fragment_container, new PlanBudgetNewFragment())
                        .commit();
            }
        });

        initData();

        recyclerView = view.findViewById(R.id.plan_budget_recyclerview);
        //设置布局管理器 , 将布局设置成纵向
        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linerLayoutManager);
        //设置适配器
        adapter = new PlanBudgetRecyclerAdapter(budgets,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyItemDecoration());

//        adapter.setOnItemClickListener(new PlanBudgetRecyclerAdapter.OnItemClickListener(){
//            @Override
//            public void onItemClick(View view , int position){
//                getFragmentManager().beginTransaction()
//                        .addToBackStack(null)  //将当前fragment加入到返回栈中
//                        .replace(R.id.fragment_container, new AssetCashDetailFragment()).commit();
//            }
//        });
        return view;
    }

    public void initData(){
        budgets = new ArrayList<>();
        budgets.add(new Budget("type_cost_1","餐饮",1000));
        budgets.add(new Budget("type_cost_2","日用",1000));
        budgets.add(new Budget("type_cost_4","手机通讯",1000));
    }
}
