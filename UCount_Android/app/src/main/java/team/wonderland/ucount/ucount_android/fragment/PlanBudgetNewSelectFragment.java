package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/9/3.
 */

public class PlanBudgetNewSelectFragment extends Fragment {
    private RadioGroup rg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_budget_new_selecttype, container, false);

        rg=(RadioGroup) view.findViewById(R.id.plan_budget_new_selecttype_radiogroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rbtn_cost_1:

                        break;
                    case R.id.rbtn_cost_2:

                        break;
                    case R.id.rbtn_cost_3:

                        break;
                    default:
                        break;
                }
            }
        });
        return view;
        }
}
