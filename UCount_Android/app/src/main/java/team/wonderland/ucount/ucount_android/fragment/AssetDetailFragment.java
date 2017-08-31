package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import team.wonderland.ucount.ucount_android.Adapter.AssetDetailRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.util.PercentageRing;

/**
 * Created by liuyu on 2017/8/30.
 */

public class AssetDetailFragment extends Fragment {

    private ImageView back;
    private RecyclerView recyclerView;
    private AssetDetailRecyclerAdapter adapter;
    private List<AssetItem> assetItems;
    private PercentageRing mPercentageRing;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_detail_fragment, container, false);
        back = (ImageView)view.findViewById(R.id.asset_detail_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        initData();

        recyclerView = (RecyclerView)view.findViewById(R.id.asset_detail_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AssetDetailRecyclerAdapter(assetItems,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL));

        mPercentageRing = (PercentageRing) view.findViewById(R.id.Circle);
        //设置目标百分比为30
        //TODO:余额占预算的百分比
        mPercentageRing.setTargetPercent(30);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.asset_detail_refresh);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.text_green,
                R.color.text_green_darker
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO:
                //重新获取完网络数据刷新Adapter，完成后需要调用onRefreshComplete方法取消滑出来的圆形进度
                assetItems.add(new AssetItem("8月31日","交通","5.00"));
                swipeRefreshLayout.setRefreshing(false);
            }
        });



        return view;
    }

    public void initData(){
        assetItems = new ArrayList<>();
        assetItems.add(new AssetItem("8月3日","餐饮","15.00"));
        assetItems.add(new AssetItem("8月4日","餐饮","15.00"));
        assetItems.add(new AssetItem("8月5日","餐饮","15.00"));
        assetItems.add(new AssetItem("8月6日","餐饮","15.00"));
        assetItems.add(new AssetItem("8月7日","餐饮","15.00"));
        assetItems.add(new AssetItem("8月8日","餐饮","15.00"));
        assetItems.add(new AssetItem("8月9日","餐饮","15.00"));
    }

}
