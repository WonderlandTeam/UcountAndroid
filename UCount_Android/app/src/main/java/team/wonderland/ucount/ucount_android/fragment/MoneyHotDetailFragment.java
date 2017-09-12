package team.wonderland.ucount.ucount_android.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wx.goodview.GoodView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

import team.wonderland.ucount.ucount_android.Adapter.AssetDetailRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.MoneyHotDetailRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.PostInfoJson;
import team.wonderland.ucount.ucount_android.json.PostReplyJson;
import team.wonderland.ucount.ucount_android.service.PostService;

/**
 * Created by liuyu on 2017/9/1.
 */
@EFragment
public class MoneyHotDetailFragment extends Fragment{

    private GoodView mGoodView;
    private ImageView good;
    private ImageView star;
    private boolean goodClicked = false;
    private boolean starClicked = false;
    private RecyclerView recyclerView;
    private MoneyHotDetailRecyclerAdapter adapter;
    private ImageView back;
    private TextView tv_remark;

    private PostInfoJson postInfoJson;
    private String username;
    private List<PostReplyJson> posts;
    @RestService
    PostService postService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_hot_detail_fragment, container, false);

        //TODO: 这里获得了postInfoJson和username
        postInfoJson = (PostInfoJson) getArguments().get("post");
        username = getActivity().getSharedPreferences("user",0).getString("USERNAME","");

        mGoodView = new GoodView(getContext());
        good = view.findViewById(R.id.money_hot_detail_good);
        star = view.findViewById(R.id.money_hot_detail_star);
        tv_remark = view.findViewById(R.id.money_hot_detail_remark_send);

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goodClicked == false) {
                    good.setImageResource(R.mipmap.ic_good_clicked);
                    mGoodView.setTextInfo("+1", Color.parseColor("#e74c3c"), 12);
                    mGoodView.show(good);
                    goodClicked = true;
                    //TODO: 点赞 PostService.praisePost()
                }else{
                    good.setImageResource(R.mipmap.ic_good);
                    mGoodView.setTextInfo("取消赞", Color.parseColor("#e74c3c"), 12);
                    mGoodView.show(good);
                    goodClicked = false;
                    //TODO: 取消点赞 PostService.cancelPraisePost()
                }
//                Toast.makeText(getContext(), "默认Toast样式",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(starClicked == false) {
                    star.setImageResource(R.mipmap.ic_star_clicked);
                    mGoodView.setTextInfo("收藏成功", Color.parseColor("#f4e842"), 12);
                    mGoodView.show(star);
                    starClicked = true;
                    //TODO: 收藏 PostService.collectPost()
                }else{
                    star.setImageResource(R.mipmap.ic_star);
                    mGoodView.setTextInfo("取消收藏", Color.parseColor("#f4e842"), 12);
                    mGoodView.show(star);
                    starClicked = false;
                    //TODO: 取消收藏 PostService.deleteCollection()
                }
            }
        });

        tv_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 发表评论 PostService.replyPost

            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.money_hot_detail_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        back = (ImageView)view.findViewById(R.id.money_hot_detail_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        initData(inflater);

        return view;
    }

    private void setHeaderView(LayoutInflater inflater,RecyclerView view){
        View header = inflater.inflate(R.layout.money_hot_article_item, view, false);
        ((TextView)header.findViewById(R.id.money_hot_article_title)).setText(postInfoJson.getTitle());
        ((TextView)header.findViewById(R.id.money_hot_article_author)).setText(postInfoJson.getUsername());
        ((TextView)header.findViewById(R.id.money_hot_article_date)).setText(postInfoJson.getTime());
        ((TextView)header.findViewById(R.id.money_hot_article_content)).setText(postInfoJson.getContent());
        adapter.setHeaderView(header);
    }

        @Background
        public void initData(LayoutInflater inflater){
            try {
                posts = postService.getPostReplies(postInfoJson.getPostId(),username);
                Log.i("moneyhotdetail",posts.toString());
                initRecyclerAdapter(inflater);
            }catch(ResponseException e){
                showErrorInfo(e.getMessage());
            }

        }

    @UiThread
    void initRecyclerAdapter(LayoutInflater inflater){
        adapter = new MoneyHotDetailRecyclerAdapter(posts,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyItemDecoration());
        setHeaderView(inflater,recyclerView);
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}
