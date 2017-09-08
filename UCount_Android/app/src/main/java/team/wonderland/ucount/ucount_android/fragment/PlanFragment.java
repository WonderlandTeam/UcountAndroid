package team.wonderland.ucount.ucount_android.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;
import java.util.List;

import team.wonderland.ucount.ucount_android.Adapter.FragAdapter;
import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/22.
 */

public class PlanFragment extends Fragment {
    private ViewPager mViewPager;
    private NavigationTabStrip mTopNavigationTabStrip;
    private List<Fragment> fragments;
    private Fragment budget;
    private Fragment task;

    public PlanFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_fragment, container, false);
        initUI(view);
        setUI(inflater, view);
        return view;
    }

    private void initUI(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.plan_vp);
        mTopNavigationTabStrip = (NavigationTabStrip) view.findViewById(R.id.plan_nts_top);
        budget = new PlanBudgetFragment_();
        task = new PlanTaskFragment();
    }

    private void setUI(LayoutInflater inflater, final View view) {
        fragments = new ArrayList<>();
        fragments.add(budget);
        fragments.add(task);

        FragAdapter fragAdapter = new FragAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(fragAdapter);

        mTopNavigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                Log.i("tag", "切换" + title);
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });
        mTopNavigationTabStrip.setTabIndex(0, true);
        mTopNavigationTabStrip.setViewPager(mViewPager);
    }
}
