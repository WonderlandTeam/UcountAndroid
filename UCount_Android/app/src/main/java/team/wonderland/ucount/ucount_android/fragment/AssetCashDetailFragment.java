package team.wonderland.ucount.ucount_android.fragment;


import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import org.androidannotations.rest.spring.annotations.RestService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.Adapter.AssetDetailRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.BillInfoJson;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;
import team.wonderland.ucount.ucount_android.service.BillService;
import team.wonderland.ucount.ucount_android.util.PercentageRing;

/**
 * Created by liuyu on 2017/8/30.
 */

@EFragment(R.layout.asset_cash_detail_fragment)
public class AssetCashDetailFragment extends Fragment {
    private ImageView back;
    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private AssetDetailRecyclerAdapter adapter;
    private List<AssetItem> assetItems;
    private SwipeRefreshLayout swipeRefreshLayout;

    @RestService
    BillService billService;

    BillInfoJson billInfoJson;

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_container, new AssetAddFragment_()).commit();
            }
        });

        initData();

        recyclerView = (RecyclerView)view.findViewById(R.id.asset_cash_detail_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AssetDetailRecyclerAdapter(assetItems,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL));

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.asset_cash_detail_refresh);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.text_green,
                R.color.text_green_darker
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO: 获得用户某类账户中所有账目 BillService.getBillsByAccount
                //重新获取完网络数据刷新Adapter，完成后需要调用onRefreshComplete方法取消滑出来的圆形进度
                assetItems.add(new AssetItem("8月31日","交通","5.00"));
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        add.attachToRecyclerView(recyclerView);
        return view;
    }

    public void initData(){
        //TODO: 获得用户某类账户中所有账目 BillService.getBillsByAccount
        assetItems = new ArrayList<>();
        assetItems.add(new AssetItem("2017年\n8月3日\n14:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月4日\n15:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月5日\n16:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月6日\n14:34","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月7日\n06:16","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月8日\n14:03","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月9日\n23:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月9日\n23:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月9日\n23:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月9日\n23:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月9日\n23:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月9日\n23:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月9日\n23:23","餐饮","15.00"));
        assetItems.add(new AssetItem("2017年\n8月9日\n23:23","餐饮","15.00"));


//        TODO 在这里设定接口需要的参数

    }

    @Background
    public void initBillDetail(){
        try {
            //TODO 不确定是否分页
//            Map<String, Object> result = billService.
//            String content = result.get("content").toString();
            Gson gson = new Gson();
            Type type = new TypeToken<List<BillInfoJson>>() {
            }.getType();


        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Looper.prepare();
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

}
