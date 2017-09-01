package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import team.wonderland.ucount.ucount_android.Adapter.MyMessageRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.Message;
import uk.co.imallan.jellyrefresh.JellyRefreshLayout;
import uk.co.imallan.jellyrefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的消息
 * Created by CLL on 17/8/30.
 */
public class MyMessageFragment extends Fragment {
    private JellyRefreshLayout jellyRefreshLayout;
    private MyMessageRecyclerAdapter myMessageRecyclerAdapter;
    private List<Message> messages;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.my_message_fragment,container,false);
        jellyRefreshLayout=view.findViewById(R.id.message_loading);

        jellyRefreshLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                initData();
            }
        });

        initData();

        recyclerView=view.findViewById(R.id.message_recycler);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myMessageRecyclerAdapter=new MyMessageRecyclerAdapter(messages,getActivity());
        recyclerView.setAdapter(myMessageRecyclerAdapter);

        myMessageRecyclerAdapter.setOnItemClickListener(new MyMessageRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO
            }
        });

        return view;
    }

    void initData(){
        messages=new ArrayList<>();
        messages.add(new Message(R.mipmap.ic_message_green,"任务完成","成功购买耳机一副","今天12:00"));
        messages.add(new Message(R.mipmap.ic_message_yellow,"预算提醒","距离本月消费预算还剩下50元","今天9:32"));
        //结束刷新
        jellyRefreshLayout.setRefreshing(false);
    }
}
