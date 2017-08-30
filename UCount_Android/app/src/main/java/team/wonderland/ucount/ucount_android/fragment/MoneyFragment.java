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

public class MoneyFragment extends Fragment {
    private String context;
    private TextView mTextView;

    public  MoneyFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_fragment,container,false);
        return view;
    }
}
