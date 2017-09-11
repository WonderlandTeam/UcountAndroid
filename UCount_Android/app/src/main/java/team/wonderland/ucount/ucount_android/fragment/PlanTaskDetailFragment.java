package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.TaskInfoJson;
import team.wonderland.ucount.ucount_android.json.TaskModifyJson;
import team.wonderland.ucount.ucount_android.service.TaskService;

/**
 * Created by liuyu on 2017/9/4.
 */
@EFragment
public class PlanTaskDetailFragment extends Fragment {

    private ImageView back;
    private TextView tv_title;
    private EditText et_money;
    private TextView tv_starttime;
    private TextView tv_deadline;
    private TextView tv_have;
    private TextView tv_daily;
    private TextView tv_review;

    private CardView cv_date;
    private CircleButton save;

    private double money;

    private TaskInfoJson taskInfoJson;

    @RestService
    TaskService taskService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_task_detail_fragment, container, false);

        taskInfoJson = (TaskInfoJson)getArguments().get("task");

        back = view.findViewById(R.id.plan_task_detail_back);
        tv_title = view.findViewById(R.id.plan_task_detail_title);
        et_money = view.findViewById(R.id.plan_task_detail_edittext_money);
        tv_deadline = view.findViewById(R.id.plan_task_detail_textview_date);
        tv_starttime = view.findViewById(R.id.plan_task_detail_textview_startdate);
        tv_have = view.findViewById(R.id.plan_task_detail_edittext_have);
        tv_daily = view.findViewById(R.id.plan_task_detail_edittext_daily);
        cv_date = view.findViewById(R.id.plan_task_detail_cardview_date);
        tv_review = view.findViewById(R.id.plan_task_detail_revise);
        save = view.findViewById(R.id.plan_task_detail_bt_save);


        tv_title.setText(taskInfoJson.getTaskContent());
        et_money.setText(String.valueOf(taskInfoJson.getUpper()));
        et_money.setEnabled(false);
        tv_starttime.setText(taskInfoJson.getCreateTime());
        tv_deadline.setText(taskInfoJson.getDeadline());
        tv_have.setText(String.valueOf(taskInfoJson.getSavedMoney()));
        tv_daily.setText(String.valueOf(taskInfoJson.getHaveToSaveEveryday()));


        tv_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save.setVisibility(View.VISIBLE);
                et_money.setEnabled(true);
                et_money.setFocusable(true);
            }
        });

        save.setVisibility(View.INVISIBLE);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_money.getText().toString().equals("")){
                    showErrorInfo("金额不能为空");
                }else{
                    money = Double.parseDouble(et_money.getText().toString());
                    if(money <=0 ){
                        showErrorInfo("金额必须大于0");
                    }else{
                        revise();
                    }
                }

            }
        });

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
                        tv_deadline.setText((date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .setLabel("年", "月", "日", "", "", "")
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Background
    void revise(){
        try{
            taskService.updateTask(taskInfoJson.getId(), new TaskModifyJson(money));
            reviseSuccess();
        }catch (ResponseException e){

        }
    }

    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    void reviseSuccess(){
        Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("planFragment","task");
        PlanFragment fragment = new PlanFragment();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.plan_fragment_container, fragment)
                .commit();
    }
}
