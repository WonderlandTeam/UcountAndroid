package team.wonderland.ucount.ucount_android.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;

import at.markushi.ui.CircleButton;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.BudgetAddJson;
import team.wonderland.ucount.ucount_android.service.BudgetService;

/**
 * Created by liuyu on 2017/9/3.
 */
@EFragment
public class PlanBudgetNewFragment extends Fragment {
    private CircleButton save;
    private ImageView back;
    private CardView cv_type;
    private CardView cv_money;
    private CardView cv_date;
    private TextView tv_type;
    private EditText et_money;
    private TextView tv_date;

    private String[] titles = {"饮食", "日用", "水电气", "通讯网费", "电子设备", "交通", "衣帽鞋服", "护肤品",
            "彩妆", "首饰", "培训", "书", "文具", "图像影音", "组织活动","捐款","恋爱","社交","兴趣"};
    private String consumeType;
    private String username;
    private double consumeMoney;
    private String consumeTime;
    BudgetAddJson budgetAddJson;

    @RestService
    BudgetService budgetService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_budget_new_fragment, container, false);

        save = view.findViewById(R.id.plan_budget_new_bt_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=getActivity().getSharedPreferences("user",0).getString("USERNAME","");
                consumeType=tv_type.getText().toString();
                consumeMoney=Double.parseDouble(et_money.getText().toString());
                consumeTime=tv_date.getText().toString();
                budgetAddJson=new BudgetAddJson(username,consumeType,consumeMoney,consumeTime);
                //添加预算
                addBudget();
            }
        });

        back = view.findViewById(R.id.plan_budget_new_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        cv_type = view.findViewById(R.id.plan_budget_new_cardview_type);
        cv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("预算类型")
                        //设置单选框监听
                        .setSingleChoiceItems(titles, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                consumeType = titles[which];//根据which决定选择了哪一个子项
                                tv_type.setText(consumeType);
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });

        tv_type = view.findViewById(R.id.plan_budget_new_textview_type);

        et_money = view.findViewById(R.id.plan_budget_new_edittext);

        cv_money = view.findViewById(R.id.plan_budget_new_cardview_num);
        cv_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_money.requestFocus();
            }
        });

        cv_date = view.findViewById(R.id.plan_budget_new_cardview_date);
        tv_date = view.findViewById(R.id.plan_budget_new_tv_date);
        final Calendar startDate = Calendar.getInstance();
        startDate.set(2017, 8, 1);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(2037, 11, 31);
        cv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间选择器
                final TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        tv_date.setText((date.getYear()+1900)+"-"+(date.getMonth()+1));
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

        return view;
    }

    @Background
    void addBudget(){
        try {
            Map<String, Object> result = budgetService.addBudget(budgetAddJson);
            returnToPlanBudgetFragment();
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }

    }

    //返回到预算主界面
    @UiThread
    void returnToPlanBudgetFragment(){
        Looper.prepare();
        Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.plan_fragment_container, new PlanBudgetFragment_())
                .commit();
        Looper.loop();
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error){
        Looper.prepare();
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
        Log.e("addNewBudget:", error);
        Looper.loop();
    }
}
