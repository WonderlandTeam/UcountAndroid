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
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.MoneyHotRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.PostInfoJson;
import team.wonderland.ucount.ucount_android.service.PostService;

/**
 * Created by liuyu on 2017/8/31.
 */
@EFragment
public class MoneyHotFragment extends Fragment {
    private RecyclerView recyclerView;
    private MoneyHotRecyclerAdapter adapter;
    private List<PostInfoJson> posts;

    @RestService
    PostService postService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_hot_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.money_hot_recyclerview);

        initData();

        return view;
    }

    @Background
    public void initData(){
        try {
            String username = this.getActivity().getSharedPreferences("user",0).getString("USERNAME","");
            posts = postService.getPosts(username,0,20,"time","DESC");
            initRecyclerView();
        }catch (ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    @UiThread
    void initRecyclerView(){
        //添加适配器
        adapter = new MoneyHotRecyclerAdapter(posts,getActivity());
        recyclerView.setAdapter(adapter);

        //设置布局管理器 , 将布局设置成纵向
        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linerLayoutManager);


        adapter.setOnItemClickListener(new MoneyHotRecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                Bundle bundle = new Bundle();
                bundle.putSerializable("post",posts.get(position));
                Fragment fragment = new MoneyHotDetailFragment_();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.money_fragment_container, fragment)
                        .commit();
            }
        });
        recyclerView.addItemDecoration(new MyItemDecoration());
    }

    @UiThread
    void showErrorInfo(String error){
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
