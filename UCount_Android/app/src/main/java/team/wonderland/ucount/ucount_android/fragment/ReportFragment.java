package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import team.wonderland.ucount.ucount_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyu on 2017/8/22.
 */

public class ReportFragment extends Fragment {
    private ViewPager mViewPager;
    private NavigationTabStrip mTopNavigationTabStrip;
    private List<View> viewList;

    public  ReportFragment(String context){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_fragment,container,false);
        initUI(view);
        setUI(inflater,view);
        return view;
    }

    private void initUI(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp);
        mTopNavigationTabStrip = (NavigationTabStrip) view.findViewById(R.id.nts_top);
    }

    private void setUI(LayoutInflater inflater, final View view) {
        View view1 = inflater.inflate(R.layout.report_one, null);
        View view2 = inflater.inflate(R.layout.report_two,null);
        View view3 = inflater.inflate(R.layout.report_three, null);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(final ViewGroup container, final int position, final Object object) {
//                ((ViewPager) container).removeView((View) object);
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                return viewList.get(position);
            }
        });

        mTopNavigationTabStrip.setTabIndex(0, true);
        mTopNavigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                Log.i("tag","切换"+title);
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });
    }
}
