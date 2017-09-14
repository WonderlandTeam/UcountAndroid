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
import android.widget.EditText;
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
import team.wonderland.ucount.ucount_android.json.PostReplyAddJson;
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
    private EditText et_remark;
    private PostInfoJson postInfoJson;
    private String username;
    private List<PostReplyJson> posts;
    private LayoutInflater inflater;

    @RestService
    PostService postService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_hot_detail_fragment, container, false);

        this.inflater = inflater;

        postInfoJson = (PostInfoJson) getArguments().get("post");
        username = getActivity().getSharedPreferences("user",0).getString("USERNAME","");

        mGoodView = new GoodView(getContext());
        good = view.findViewById(R.id.money_hot_detail_good);
        star = view.findViewById(R.id.money_hot_detail_star);
        tv_remark = view.findViewById(R.id.money_hot_detail_remark_send);
        et_remark = view.findViewById(R.id.money_hot_detail_remark);
        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goodClicked == false) {
                    setGood();
                }else{
                    cancleGood();
                }
            }
        });

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(starClicked == false) {
                    setStar();
                }else{
                    cancleStar();
                }
            }
        });

        tv_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String remark = et_remark.getText().toString();
                if(remark.equals("")){
                    showErrorInfo("评论不能为空哦");
                }else{
                    remark(remark);
                }
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


    //点赞
    @Background
    void setGood(){
        try {
            postService.praisePost(postInfoJson.getPostId(),username);
            setGoodSuccess();
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    //点赞成功
    @UiThread
    void setGoodSuccess(){
        good.setImageResource(R.mipmap.ic_good_clicked);
        mGoodView.setTextInfo("+1", Color.parseColor("#e74c3c"), 12);
        mGoodView.show(good);
        goodClicked = true;
    }

    //取消点赞
    @Background
    void cancleGood(){
        try {
            postService.cancelPraisePost(postInfoJson.getPostId(),username);
            cancleGoodSuccess();
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    //取消点赞成功
    @UiThread
    void cancleGoodSuccess(){
        good.setImageResource(R.mipmap.ic_good);
        mGoodView.setTextInfo("取消赞", Color.parseColor("#e74c3c"), 12);
        mGoodView.show(good);
        goodClicked = false;
    }


    //收藏
    @Background
    void setStar(){
        try {
            postService.collectPost(postInfoJson.getPostId(),username);
            setStarSuccess();
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    //收藏成功
    @UiThread
    void setStarSuccess(){
        star.setImageResource(R.mipmap.ic_star_clicked);
        mGoodView.setTextInfo("收藏成功", Color.parseColor("#f4e842"), 12);
        mGoodView.show(star);
        starClicked = true;
    }

    //取消收藏
    @Background
    void cancleStar(){
        try {
            postService.deleteCollection(postInfoJson.getPostId(),username);
            cancleStarSuccess();
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    //取消收藏成功
    @UiThread
    void cancleStarSuccess(){
        star.setImageResource(R.mipmap.ic_star);
        mGoodView.setTextInfo("取消收藏", Color.parseColor("#f4e842"), 12);
        mGoodView.show(star);
        starClicked = false;
    }

    //发布评论
    @Background
    void remark(String remark){
        try {
            PostReplyAddJson postReplyAddJson = new PostReplyAddJson(username,remark);
            postService.replyPost(postInfoJson.getPostId(),postReplyAddJson);
            remarkSuccess();
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    //发布评论成功
    @UiThread
    void remarkSuccess(){
        Toast.makeText(getActivity(), "发布评论成功", Toast.LENGTH_SHORT).show();
        initData(inflater);
    }
}
