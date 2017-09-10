package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import team.wonderland.ucount.ucount_android.json.TaskAddJson;
import team.wonderland.ucount.ucount_android.service.TaskService;
import team.wonderland.ucount.ucount_android.util.TimePickerDialog;

/**
 * Created by liuyu on 2017/9/3.
 */
@EFragment
public class PlanTaskNewFragment extends Fragment {
    private CardView cv_date;
    private CircleButton save;

    private EditText et_content;
    private TextView tv_date;
    private EditText et_num;

    private String createTime;
    private String username;

    @RestService
    TaskService taskService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_task_new_fragment, container, false);

        username = getActivity().getSharedPreferences("user",0).getString("USERNAME","");

        et_content = view.findViewById(R.id.plan_task_new_edittext_name);
        tv_date = view.findViewById(R.id.plan_task_new_textview_date);
        et_num = view.findViewById(R.id.plan_task_new_edittext_num);


        final Calendar startDate = Calendar.getInstance();
        startDate.set(2017, 8, 1);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(2037, 11, 31);
        cv_date = view.findViewById(R.id.plan_task_new_cardview_date);
        cv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间选择器
                final TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                       tv_date.setText((date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
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

        save = view.findViewById(R.id.plan_task_new_bt_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveTask();
            }
        });
        return view;


    }

    @Background
    public void saveTask(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        createTime = year + "-" + month + "-" + day;

        String deadline = tv_date.getText().toString();

        String content = et_content.getText().toString();

        if (content.equals("")){
            showErrorInfo("内容不能为空");
        }else if(deadline.equals("")){
            showErrorInfo("截止日期不能为空");
        }else if(et_num.getText().equals("")){
            showErrorInfo("金额不能为空");
        }else{
            Log.i("tag","测试测试");
            Log.i("nowDate",createTime);
            TaskAddJson taskAddJson = new TaskAddJson(username,content,createTime,deadline,Double.parseDouble(et_num.getText().toString()));
            try {
                taskService.addTask(taskAddJson);
                saveSuccess();
            }catch(ResponseException e){
                showErrorInfo(e.getMessage());
            }
        }

    }

    @UiThread
    public void saveSuccess(){
        Toast.makeText(getActivity(), "新建成功", Toast.LENGTH_SHORT).show();
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.plan_fragment_container, new PlanTaskFragment_())
                .commit();
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
