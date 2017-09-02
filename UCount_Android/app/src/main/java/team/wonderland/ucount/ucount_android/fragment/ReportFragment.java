package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import team.wonderland.ucount.ucount_android.Adapter.FragAdapter;
import team.wonderland.ucount.ucount_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyu on 2017/8/22.
 */

public class ReportFragment extends Fragment {
    private ViewPager mViewPager;
    private NavigationTabStrip mTopNavigationTabStrip;
    private List<Fragment> fragments;
    private Fragment report1;
    private Fragment report2;
    private Fragment report3;

    public ReportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_fragment,container,false);
        initUI(view);
        setUI(inflater,view);
        return view;
    }

    private void initUI(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.report_vp);
        mTopNavigationTabStrip = (NavigationTabStrip) view.findViewById(R.id.report_nts_top);
        report1 = new ReportOneFragment();
        report2 = new ReportTwoFragment();
        report3 = new ReportTwoFragment();
    }

    private void setUI(LayoutInflater inflater, final View view) {

        fragments = new ArrayList<>();
        fragments.add(report1);
        fragments.add(report2);
        fragments.add(report3);

        FragAdapter fragAdapter= new FragAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(fragAdapter);

        mTopNavigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                Log.i("tag","切换"+title);
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });
        mTopNavigationTabStrip.setTabIndex(0, true);
        mTopNavigationTabStrip.setViewPager(mViewPager);
    }

}
