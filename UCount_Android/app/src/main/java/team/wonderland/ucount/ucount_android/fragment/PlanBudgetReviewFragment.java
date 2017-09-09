package team.wonderland.ucount.ucount_android.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import org.androidannotations.annotations.Background;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.json.BudgetAddJson;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;
import team.wonderland.ucount.ucount_android.service.BudgetService;

/**
 * Created by liuyu on 2017/9/9.
 */

public class PlanBudgetReviewFragment extends Fragment {

    private CircleButton save;
    private TextView headerreview;
    private TextView headertitle;
    private ImageView back;
    private CardView cv_type;
    private CardView cv_total;
    private CardView cv_left;
    private CardView cv_date;
    private TextView tv_type;
    private EditText et_total;
    private EditText et_left;
    private TextView tv_date;

    private String[] titles = {"饮食", "日用", "水电气", "通讯网费", "电子设备", "交通", "衣帽鞋服", "护肤品",
            "彩妆", "首饰", "培训", "书", "文具", "图像影音", "组织活动","捐款","恋爱","社交","兴趣"};
    private String consumeType;
    private String username;
    private double total;
    private double left;
    private String consumeTime;

    private BudgetInfoJson budgetInfoJson;

    BudgetService budgetService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_budget_review_fragment, container, false);


        save = view.findViewById(R.id.plan_budget_review_bt_save);
        save.setVisibility(View.GONE);
        headerreview = view.findViewById(R.id.plan_budget_review_header_review);
        headertitle = view.findViewById(R.id.plan_budget_review_header_title);

        headerreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                headertitle.setText("修改预算");
                save.setVisibility(View.VISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=getActivity().getSharedPreferences("user",0).getString("USERNAME","");
                consumeType=tv_type.getText().toString();
                total=Double.parseDouble(et_total.getText().toString());
                left=Double.parseDouble(et_left.getText().toString());
                consumeTime=tv_date.getText().toString();
                //TODO: id
                budgetInfoJson=new BudgetInfoJson(1l,username,consumeType,total,consumeTime,total-left,left);
                //TODO: 修改预算
                //reviewBudget();
            }
        });

        back = view.findViewById(R.id.plan_budget_review_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        tv_type = view.findViewById(R.id.plan_budget_review_textview_type);
        cv_type = view.findViewById(R.id.plan_budget_review_cardview_type);

        et_total = view.findViewById(R.id.plan_budget_review_edittext_total);
        cv_total = view.findViewById(R.id.plan_budget_review_cardview_total);


        et_left = view.findViewById(R.id.plan_budget_review_edittext_num);
        cv_left = view.findViewById(R.id.plan_budget_review_cardview_num);

        cv_date = view.findViewById(R.id.plan_budget_review_cardview_date);
        tv_date = view.findViewById(R.id.plan_budget_review_tv_date);


        return view;
    }

//    @Background
//    void reviewBudget(){
//        Map<String,Object> result=budgetService.addBudget(budgetReviewJson);
//
//        if(result.containsKey("content")){
//            returnToPlanBudgetFragment();
//        }else{
//            showErrorInfo((String) result.get("error"));
//        }
//
//    }
}
