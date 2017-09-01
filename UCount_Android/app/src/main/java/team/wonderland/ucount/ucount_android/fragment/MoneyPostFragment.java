package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/31.
 */

public class MoneyPostFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_post_fragment, container,false);
        return view;
    }
}
