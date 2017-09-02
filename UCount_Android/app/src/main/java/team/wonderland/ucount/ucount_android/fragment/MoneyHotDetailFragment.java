package team.wonderland.ucount.ucount_android.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.wx.goodview.GoodView;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/9/1.
 */

public class MoneyHotDetailFragment extends Fragment{

    GoodView mGoodView;
    ImageView good;
    ImageView star;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_hot_detail_fragment, container, false);

        mGoodView = new GoodView(getContext());
        good = view.findViewById(R.id.money_hot_detail_good);
        star = view.findViewById(R.id.money_hot_detail_star);

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                good.setImageResource(R.mipmap.ic_good_clicked);
                mGoodView.setTextInfo("+1",Color.parseColor("#e74c3c"), 12);
                mGoodView.show(good);

//                Toast.makeText(getContext(), "默认Toast样式",
//                        Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
