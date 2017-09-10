package team.wonderland.ucount.ucount_android.fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.rest.spring.annotations.RestService;
import team.wonderland.ucount.ucount_android.Adapter.MyCollectionRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.entity.MyPost;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.PostInfoJson;
import team.wonderland.ucount.ucount_android.service.PostService;
import uk.co.imallan.jellyrefresh.JellyRefreshLayout;
import uk.co.imallan.jellyrefresh.PullToRefreshLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by CLL on 17/8/30.
 */
@EFragment
public class MyCollectionFragment extends Fragment {
    private JellyRefreshLayout jellyRefreshLayout;
    private MyCollectionRecyclerAdapter myCollectionRecyclerAdapter;
    private List<MyPost> posts;
    private RecyclerView recyclerView;
    private String userName;

    @RestService
    PostService postService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.my_collection_fragment,container,false);
        jellyRefreshLayout=view.findViewById(R.id.collection_loading);

        jellyRefreshLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                initData();
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences("user", 0);
        userName = preferences.getString("USERNAME", "sigma");

        posts=new ArrayList<>();

        recyclerView=view.findViewById(R.id.collection_recycler);

        //调用后台数据并且调用完成后刷新界面
        new aTask().execute();
        return view;
    }

    void initRecycleView(){
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myCollectionRecyclerAdapter=new MyCollectionRecyclerAdapter(posts,getActivity());
        recyclerView.setAdapter(myCollectionRecyclerAdapter);
        myCollectionRecyclerAdapter.setOnItemClickListener(new MyCollectionRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    void initData(){
        posts.add(new MyPost("理财需要关注什么","Chen","10:20",10,5));
        posts.add(new MyPost("大学生的消费观","Wang","11:11",6,4));

        try {
            Map<String, Object> contents = postService.getPostsCollectedByUser(userName);
            if(contents!=null) {
                String json = contents.get("content").toString();
                Log.i("json", json);
                Gson gson = new Gson();
                Type type = new TypeToken<List<PostInfoJson>>() {
                }.getType();
                List<PostInfoJson> postInfoJsons = gson.fromJson(json, type);
                for (PostInfoJson postInfoJson : postInfoJsons) {
                    //缺少评论数
                    posts.add(new MyPost(postInfoJson.getTitle(), postInfoJson.getUsername(),
                            postInfoJson.getTime(), postInfoJson.getSupportNum(), postInfoJson.getSupportNum()));
                }
            }
        } catch (ResponseException e) {
            Log.i("error", e.getMessage());
        }
    }

    private class aTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void o) {
            super.onPostExecute(o);
            initRecycleView();
            Log.i("tag", "调用后");
            myCollectionRecyclerAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            initData();
            Log.i("tag", "调用后台数据");
            return null;
        }
    }
}
