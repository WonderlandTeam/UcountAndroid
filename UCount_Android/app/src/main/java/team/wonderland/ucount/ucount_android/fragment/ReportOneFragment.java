package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import com.squareup.timessquare.CalendarPickerView;
import team.wonderland.ucount.ucount_android.R;

import java.util.Calendar;
import java.util.Date;

/**
 * 收支表
 * Created by CLL on 17/9/2.
 */
public class ReportOneFragment extends Fragment {
    private EditText beginDate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_one, container, false);
        EditText beginDate=view.findViewById(R.id.begin_date_text);
        beginDate.setOnClickListener(new OnDateTextClicker());

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final CalendarPickerView calendar = view.findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);
        calendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("date",view.toString());

            }
        });
        return view;
    }

    public class OnDateTextClicker implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            //TODO
        }
    }
}
