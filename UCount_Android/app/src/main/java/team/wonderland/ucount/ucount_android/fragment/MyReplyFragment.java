package team.wonderland.ucount.ucount_android.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.rest.spring.annotations.RestService;
import team.wonderland.ucount.ucount_android.Adapter.MyReplyRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.Reply;
import team.wonderland.ucount.ucount_android.service.PostService;
import uk.co.imallan.jellyrefresh.JellyRefreshLayout;
import uk.co.imallan.jellyrefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的评论
 * Created by CLL on 17/8/30.
 */
@EFragment
public class MyReplyFragment extends Fragment {
    private JellyRefreshLayout jellyRefreshLayout;
    private MyReplyRecyclerAdapter myReplyRecyclerAdapter;
    private List<Reply> replies;
    private RecyclerView recyclerView;
    private String userName;

    @RestService
    PostService postService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.my_reply_fragment,container,false);
        jellyRefreshLayout=view.findViewById(R.id.reply_loading);

        jellyRefreshLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                initData();
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences("user", 0);
        userName = preferences.getString("USERNAME", "sigma");

        initData();
        recyclerView=view.findViewById(R.id.reply_recycler);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myReplyRecyclerAdapter=new MyReplyRecyclerAdapter(replies,getActivity());
        recyclerView.setAdapter(myReplyRecyclerAdapter);
        myReplyRecyclerAdapter.setOnItemClickListener(new MyReplyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        return view;
    }

    void initData(){
        // TODO: 17/9/8 获得该用户评论的帖子
        replies=new ArrayList<>();
        replies.add(new Reply("大学生的消费观","对一点点等奶茶的消费","Wang"));
    }
}
