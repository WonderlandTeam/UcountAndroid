package team.wonderland.ucount.ucount_android.fragment;


import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.Adapter.AssetDetailRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.AccountInfoJson;
import team.wonderland.ucount.ucount_android.json.BillInfoJson;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;
import team.wonderland.ucount.ucount_android.service.BillService;
import team.wonderland.ucount.ucount_android.util.PercentageRing;

/**
 * Created by liuyu on 2017/8/30.
 */

@EFragment(R.layout.asset_cash_detail_fragment)
public class AssetDetailFragment extends Fragment {
    private ImageView back;
    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private AssetDetailRecyclerAdapter adapter;
    private List<BillInfoJson> assetItems;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Long accountID = 1l;
    private String accountType = "";
    private String username = "";
    @RestService
    BillService billService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_cash_detail_fragment, container, false);
        back = (ImageView)view.findViewById(R.id.asset_cash_detail_back);
        add = (FloatingActionButton) view.findViewById(R.id.asset_cash_detail_bt_add);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.asset_cash_detail_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.asset_cash_detail_refresh);

        accountID = (Long)this.getArguments().get("account");
        accountType = (String)this.getArguments().get("accountType");
        if(!accountType.equals("现金")){
            add.setVisibility(View.INVISIBLE);
        }
        username = getActivity().getSharedPreferences("user", 0).getString("USERNAME", "");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong("account",accountID);
                Fragment fragment = new AssetAddFragment_();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_container, fragment).commit();
            }
        });

        initBillDetail();

        return view;
    }

    public void initData(){
        //TODO: 获得用户某类账户中所有账目 BillService.getBillsByAccount
        //TODO 在这里设定接口需要的参数
    }

    @Background
    public void initBillDetail(){
        try {
            assetItems = billService.getBillsByAccount(accountID,0,20,"id","ASC");
            Log.i("tag","调用数据");
            Log.i("tag",assetItems.toString());

            initRecyclerView();
 //           billService.getBillsByAccount(accountID,);
//            Map<String, Object> result = billService.
//            String content = result.get("content").toString();
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<BillInfoJson>>() {
//            }.getType();
        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
    }

    @UiThread
    void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AssetDetailRecyclerAdapter(assetItems,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyItemDecoration());


        swipeRefreshLayout.setColorSchemeResources(
                R.color.text_green,
                R.color.text_green_darker
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO: 获得用户某类账户中所有账目 BillService.getBillsByAccount
                //重新获取完网络数据刷新Adapter，完成后需要调用onRefreshComplete方法取消滑出来的圆形进度
                initBillDetail();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        add.attachToRecyclerView(recyclerView);
    }


    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}
