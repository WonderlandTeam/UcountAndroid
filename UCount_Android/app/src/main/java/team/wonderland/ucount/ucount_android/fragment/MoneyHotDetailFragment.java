package team.wonderland.ucount.ucount_android.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wx.goodview.GoodView;

import java.util.ArrayList;
import java.util.List;

import team.wonderland.ucount.ucount_android.Adapter.AssetDetailRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.MoneyHotDetailRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.json.PostInfoJson;

/**
 * Created by liuyu on 2017/9/1.
 */

public class MoneyHotDetailFragment extends Fragment{

    private GoodView mGoodView;
    private ImageView good;
    private ImageView star;
    private boolean goodClicked = false;
    private boolean starClicked = false;
    private RecyclerView recyclerView;
    private MoneyHotDetailRecyclerAdapter adapter;
    private List<PostInfoJson> posts;
    private ImageView back;
    private TextView tv_remark;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_hot_detail_fragment, container, false);

        mGoodView = new GoodView(getContext());
        good = view.findViewById(R.id.money_hot_detail_good);
        star = view.findViewById(R.id.money_hot_detail_star);
        tv_remark = view.findViewById(R.id.money_hot_detail_remark);

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goodClicked == false) {
                    good.setImageResource(R.mipmap.ic_good_clicked);
                    mGoodView.setTextInfo("+1", Color.parseColor("#e74c3c"), 12);
                    mGoodView.show(good);
                    goodClicked = true;
                }else{
                    good.setImageResource(R.mipmap.ic_good);
                    mGoodView.setTextInfo("取消赞", Color.parseColor("#e74c3c"), 12);
                    mGoodView.show(good);
                    goodClicked = false;

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
                }else{
                    star.setImageResource(R.mipmap.ic_star);
                    mGoodView.setTextInfo("取消收藏", Color.parseColor("#f4e842"), 12);
                    mGoodView.show(star);
                    starClicked = false;
                }
            }
        });

        tv_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 发表评论 PostService.replyPost
            }
        });
        initData();

        recyclerView = (RecyclerView)view.findViewById(R.id.money_hot_detail_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MoneyHotDetailRecyclerAdapter(getActivity(),posts);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyItemDecoration());

        back = (ImageView)view.findViewById(R.id.money_hot_detail_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }

        public void initData(){
            //TODO 获得所有热门的帖子 PostService.getPosts
            posts = new ArrayList<>();
        }
}
