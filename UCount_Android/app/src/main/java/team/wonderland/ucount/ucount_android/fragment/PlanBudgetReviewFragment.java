package team.wonderland.ucount.ucount_android.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.BudgetAddJson;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;
import team.wonderland.ucount.ucount_android.json.BudgetModifyJson;
import team.wonderland.ucount.ucount_android.service.BudgetService;

/**
 * Created by liuyu on 2017/9/9.
 */
@EFragment(R.layout.plan_budget_review_fragment)
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

    private String consumeType;
    private String username;
    private double total;
    private double left;
    private String consumeTime;

    private BudgetInfoJson budgetInfoJson;

    @RestService
    BudgetService budgetService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_budget_review_fragment, container, false);

        save = view.findViewById(R.id.plan_budget_review_bt_save);
        save.setVisibility(View.GONE);

        headerreview = view.findViewById(R.id.plan_budget_review_header_review);
        headertitle = view.findViewById(R.id.plan_budget_review_header_title);

        tv_type = view.findViewById(R.id.plan_budget_review_textview_type);
        cv_type = view.findViewById(R.id.plan_budget_review_cardview_type);

        et_total = view.findViewById(R.id.plan_budget_review_edittext_total);
        cv_total = view.findViewById(R.id.plan_budget_review_cardview_total);

        et_left = view.findViewById(R.id.plan_budget_review_edittext_num);
        cv_left = view.findViewById(R.id.plan_budget_review_cardview_num);

        cv_date = view.findViewById(R.id.plan_budget_review_cardview_date);
        tv_date = view.findViewById(R.id.plan_budget_review_tv_date);

        et_total.setEnabled(false);
        et_left.setEnabled(false);
        tv_date.setEnabled(false);

        back = view.findViewById(R.id.plan_budget_review_back);

        headerreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                headertitle.setText("修改预算");
                save.setVisibility(View.VISIBLE);
                et_total.setEnabled(true);
                et_total.requestFocus();
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

                total = Double.parseDouble(et_total.getText().toString());

                //将修改结果写入数据库
                reviewBudget();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("tag","back clicked");
                getFragmentManager().popBackStack();
            }
        });

        //在组件上显示数据数值
        budgetInfoJson = (BudgetInfoJson) this.getArguments().get("budgetInfo");
        tv_type.setText(budgetInfoJson.getConsumeType());
        et_total.setText(String.valueOf(budgetInfoJson.getBudgetMoney()));
        et_left.setText(String.valueOf((int)(budgetInfoJson.getRemain() * 100) / 100.0));
        tv_date.setText(budgetInfoJson.getBugdetTime());

        return view;
    }

    @Background
    void reviewBudget(){
        try {
            BudgetModifyJson budgetModifyJson = new BudgetModifyJson(total);
            Log.i("tag", budgetInfoJson.getId().toString());
            Log.i("tag", String.valueOf(budgetModifyJson.getMoney()));
            budgetService.updateBudget(budgetInfoJson.getId(), budgetModifyJson);
            returnToPlanBudgetFragment();
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    @UiThread
    void showErrorInfo(String error){
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    void returnToPlanBudgetFragment(){
        Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
        getFragmentManager().beginTransaction()
                .replace(R.id.plan_fragment_container, new PlanFragment())
                .commit();

    }
}
