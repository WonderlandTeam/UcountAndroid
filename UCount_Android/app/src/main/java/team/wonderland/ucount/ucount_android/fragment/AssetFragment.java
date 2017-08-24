package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/21.
 */


public class AssetFragment extends Fragment {
    private TextView txtDetail,txtNew,txtTotal,txtIn,txtOut;
    private RecyclerView recyclerView;
    private AssetRecyclerAdapter adapter;
    private List<Account> accounts;
    public  AssetFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_fragment,container,false);
        txtDetail = (TextView)view.findViewById(R.id.asset_txt_detail);
        txtNew = (TextView)view.findViewById(R.id.asset_txt_new);
        txtTotal = (TextView)view.findViewById(R.id.asset_txt_total);
        txtIn = (TextView)view.findViewById(R.id.asset_txt_in);
        txtOut = (TextView)view.findViewById(R.id.asset_txt_out);
        recyclerView = (RecyclerView)view.findViewById(R.id.asset_recyclerview);

        initData();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AssetRecyclerAdapter(accounts,getActivity());
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL));
        return view;
    }

    public void initData(){
        accounts = new ArrayList<>();
        accounts.add(new Account("现金",0.0,R.mipmap.xianjin));
        accounts.add(new Account("银行卡",0.0,R.mipmap.yinhangka));
        accounts.add(new Account("校园卡",0.0,R.mipmap.xiaoyuan));
        accounts.add(new Account("支付宝",0.0,R.mipmap.zhifubao));
    }
}
