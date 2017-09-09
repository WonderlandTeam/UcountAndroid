package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.androidannotations.annotations.EFragment;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.util.ChartTableView;

/**
 * Created by CLL on 17/9/2.
 */
@EFragment
public class ReportTwoFragment extends Fragment {
    private ChartTableView chartTableView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_two, container, false);
        chartTableView=view.findViewById(R.id.chartTableView);
        return view;
    }
}
