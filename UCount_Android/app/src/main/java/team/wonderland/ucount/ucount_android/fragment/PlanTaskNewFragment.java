package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.util.TimePickerDialog;

/**
 * Created by liuyu on 2017/9/3.
 */

public class PlanTaskNewFragment extends Fragment {
    private TimePickerDialog timePickerDialog;
    private CardView cv_date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_task_new_fragment, container, false);

        cv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.showDatePickerDialog();
            }
        });

        return view;
    }
}
