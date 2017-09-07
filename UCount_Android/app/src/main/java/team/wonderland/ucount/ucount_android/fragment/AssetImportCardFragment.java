package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/9/4.
 */

public class AssetImportCardFragment extends Fragment {

    private ImageView back;
    private Button confirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_importcard_fragment, container, false);

        back = view.findViewById(R.id.asset_importcard_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        confirm = view.findViewById(R.id.asset_importcard_btnLogin);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 导入支付宝或银行卡，（这个页面是让用户输入支付宝或银行卡的账号和密码）
            }
        });

        return view;
    }
}
