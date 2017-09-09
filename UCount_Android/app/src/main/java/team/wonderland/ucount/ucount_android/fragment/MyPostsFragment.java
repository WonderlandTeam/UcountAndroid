package team.wonderland.ucount.ucount_android.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.rest.spring.annotations.RestService;
import team.wonderland.ucount.ucount_android.Adapter.MyPostRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.MyPost;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.service.PostService;
import uk.co.imallan.jellyrefresh.JellyRefreshLayout;
import uk.co.imallan.jellyrefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 我的帖子
 * Created by CLL on 17/8/30.
 */
@EFragment
public class MyPostsFragment extends Fragment {
    private JellyRefreshLayout jellyRefreshLayout;
    private MyPostRecyclerAdapter myPostRecyclerAdapter;
    private List<MyPost> myPosts;
    private RecyclerView recyclerView;
    private String userName;

    @RestService
    PostService postService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_posts_fragment, container, false);
        jellyRefreshLayout = view.findViewById(R.id.post_loading);

        jellyRefreshLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                initData();
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences("user", 0);
        userName = preferences.getString("USERNAME", "sigma");

        myPosts = new ArrayList<>();

        initData();

        recyclerView = view.findViewById(R.id.post_recycler);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myPostRecyclerAdapter = new MyPostRecyclerAdapter(myPosts, getActivity());
        recyclerView.setAdapter(myPostRecyclerAdapter);
        myPostRecyclerAdapter.setOnItemClickListener(new MyPostRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        return view;
    }

    @Background
    void initData() {
        myPosts.add(new MyPost("理财需要关注什么", "Chen", "10:20", 10, 5));
        myPosts.add(new MyPost("大学生的消费观", "Wang", "11:11", 6, 4));
        try {
            Map<String, Object> contents = postService.getPostsSharedByUser(userName);
            String json=contents.get("content").toString();
            Log.i("json",json);
            // TODO: 17/9/8 闪退
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<PostInfoJson>>() {
//            }.getType();
//            List<PostInfoJson> postInfoJsons=gson.fromJson(json,type);
//            for(PostInfoJson postInfoJson:postInfoJsons){
//                //缺少评论数
//                myPosts.add(new MyPost(postInfoJson.getTitle(),postInfoJson.getUsername(),
//                        postInfoJson.getTime(),postInfoJson.getSupportNum(),postInfoJson.getSupportNum()));
//            }

        } catch (ResponseException e) {
            Log.i("error", e.getMessage());
        }


    }
}
