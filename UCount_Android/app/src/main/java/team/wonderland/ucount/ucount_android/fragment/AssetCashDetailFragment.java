package team.wonderland.ucount.ucount_android.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/30.
 */

public class AssetCashDetailFragment extends Fragment {
    private ImageView back;
    private CircleButton add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_cash_detail_fragment, container, false);
        back = (ImageView)view.findViewById(R.id.asset_cash_detail_back);
        add = (CircleButton)view.findViewById(R.id.asset_cash_detail_bt_add);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_container, new AssetAddFragment()).commit();
            }
        });

        return view;
    }

}
