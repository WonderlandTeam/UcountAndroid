package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import team.wonderland.ucount.ucount_android.Adapter.MyPraiseRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.MyPost;
import uk.co.imallan.jellyrefresh.JellyRefreshLayout;
import uk.co.imallan.jellyrefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CLL on 17/8/30.
 */
public class MyPraiseFragment extends Fragment {
    private JellyRefreshLayout jellyRefreshLayout;
    private MyPraiseRecyclerAdapter myPraiseRecyclerAdapter;
    private List<MyPost> myPosts;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.my_praise_fragment,container,false);
        jellyRefreshLayout=view.findViewById(R.id.praise_loading);

        jellyRefreshLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                initData();
            }
        });
        initData();
        recyclerView=view.findViewById(R.id.praise_recycler);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myPraiseRecyclerAdapter=new MyPraiseRecyclerAdapter(myPosts,getActivity());
        recyclerView.setAdapter(myPraiseRecyclerAdapter);
        myPraiseRecyclerAdapter.setOnItemClickListener(new MyPraiseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        return view;
    }

    void initData(){
        // TODO: 17/9/8 获得该用户点赞的帖子
        myPosts=new ArrayList<>();
        myPosts.add(new MyPost("理财需要关注什么","Chen","10:20",10,5));
        myPosts.add(new MyPost("大学生的消费观","Wang","11:11",6,4));
    }
}
