package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.MoneyHotRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
/**
 * Created by liuyu on 2017/8/31.
 */

public class MoneyHotFragment extends Fragment {
    private RecyclerView recyclerView;
    private MoneyHotRecyclerAdapter adapter;
    private List<Post> posts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_hot_fragment, container, false);

        initData();

        recyclerView = (RecyclerView) view.findViewById(R.id.money_hot_recyclerview);
        //添加适配器
        adapter = new MoneyHotRecyclerAdapter(posts,getActivity());
        recyclerView.setAdapter(adapter);

        //设置布局管理器 , 将布局设置成纵向
        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linerLayoutManager);




        adapter.setOnItemClickListener(new AssetRecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                Bundle bundle = new Bundle();
                bundle.putSerializable("post",posts.get(position));
                Fragment fragment = new Fragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.money_fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        recyclerView.addItemDecoration(new MyItemDecoration());

        return view;
    }

    public void initData(){
        posts = new ArrayList<>();
        posts.add(new Post("说说信用卡还贷款","2017.8.31","wind",".............."));
        posts.add(new Post("P2P还款方式那么多，总有一款适合你","2017.8.31","飘飘",".............."));
        posts.add(new Post("如何通过自己的爱好赚钱","2017.8.31","黄飘",".............."));
        posts.add(new Post("腾讯信用和芝麻信用哪个更重要","2017.8.31","飘",".............."));
    }
}
