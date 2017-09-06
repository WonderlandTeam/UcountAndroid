package team.wonderland.ucount.ucount_android.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

import at.markushi.ui.CircleButton;
import org.androidannotations.annotations.EFragment;
import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/9/3.
 */
@EFragment
public class PlanBudgetNewFragment extends Fragment {
    CircleButton save;
    ImageView back;
    CardView cv_type;
    TextView tv_type;
    EditText et_money;
    CardView cv_money;
    String[] titles = {"饮食", "日用", "水电气", "通讯网费", "电子设备", "交通", "衣帽鞋服", "护肤品",
            "彩妆", "首饰", "培训", "书", "文具", "图像影音", "组织活动","捐款","恋爱","社交","兴趣"};
    String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_budget_new_fragment, container, false);

        save = view.findViewById(R.id.plan_budget_new_bt_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.plan_fragment_container, new PlanBudgetFragment())
                        .commit();
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
                                type = titles[which];//根据which决定选择了哪一个子项
                                tv_type.setText(type);
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
        return view;
    }
}
