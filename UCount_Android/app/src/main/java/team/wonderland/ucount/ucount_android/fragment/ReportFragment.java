package team.wonderland.ucount.ucount_android.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/22.
 */

public class ReportFragment extends Fragment {
    private String context;
    private TextView mTextView;

    public  ReportFragment(String context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_fragment,container,false);
        return view;
    }
}
