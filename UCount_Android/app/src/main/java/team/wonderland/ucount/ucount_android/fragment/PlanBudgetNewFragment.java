package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/9/3.
 */

public class PlanBudgetNewFragment extends Fragment {
    CircleButton save;
    ImageView back;
    CardView type;

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

        type = view.findViewById(R.id.plan_budget_new_cardview_type);
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.plan_fragment_container, new PlanBudgetNewSelectFragment())
                        .commit();
            }
        });



        return view;
    }
}
