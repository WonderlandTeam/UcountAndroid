package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.MoneyHotRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;

/**
 * Created by liuyu on 2017/8/31.
 */

public class MoneyKnowledgeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MoneyHotRecyclerAdapter adapter;
    private List<Post> posts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_knowledge_fragment, container,false);

        initData();

        recyclerView = (RecyclerView) view.findViewById(R.id.money_knowledge_recyclerview);
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
                bundle.putInt("num",position);
                Fragment fragment = new MoneyKnowledgeDetailFragment();
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
        posts.add(new Post("大学生理财真人秀:不做“穷学生”，只需这四步","","",""));
        posts.add(new Post("政府工作报告对股市影响解读汇总:2017全年看呈U型","","",""));
        posts.add(new Post("银行理财要蔫了，但还有更安全的理财方式!","","",""));
        posts.add(new Post("理财知识 | 基础理财知识普及","","",""));
        posts.add(new Post("理财全靠机器?理财经理怎么办?","","",""));
        posts.add(new Post("理财和不理财，人生会有多大差别?| 直播","","",""));
        posts.add(new Post("理财基础知识","","",""));
        posts.add(new Post("理财扩展知识","","",""));
    }
}
