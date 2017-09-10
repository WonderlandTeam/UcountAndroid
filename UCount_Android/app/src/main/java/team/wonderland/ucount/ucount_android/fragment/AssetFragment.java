package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.AccountInfoJson;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;
import team.wonderland.ucount.ucount_android.service.AccountService;

/**
 * Created by liuyu on 2017/8/21.
 */

@EFragment(R.layout.asset_fragment)
public class AssetFragment extends Fragment {
    private TextView txtDetail,txtNew,txtTotal,txtIn,txtOut;
    private RecyclerView recyclerView;
    private AssetRecyclerAdapter adapter;
    private List<AccountInfoJson> accounts = new ArrayList<>();

    String username;

    @RestService
    AccountService accountService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_fragment,container,false);
        txtDetail = (TextView)view.findViewById(R.id.asset_txt_detail);
        txtNew = (TextView)view.findViewById(R.id.asset_txt_new);
        txtTotal = (TextView)view.findViewById(R.id.asset_txt_total);
        txtIn = (TextView)view.findViewById(R.id.asset_txt_in);
        txtOut = (TextView)view.findViewById(R.id.asset_txt_out);
        recyclerView = (RecyclerView)view.findViewById(R.id.asset_recyclerview);

        username = getActivity().getSharedPreferences("user", 0).getString("USERNAME", "");
        initAsset();

        txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_container, new AssetNewFragment_()).commit();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.fragment_container, new AssetNewFragment());
        }
        });

        txtDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_container, new AssetDetailFragment()).commit();
            }
        });
        return view;
    }


    /**
     * 获得用户所有账户信息
     */
    @Background
    void initAsset() {
        try {
            Map<String, Object> result = accountService.getAccountsByUser(username);
            String content = result.get("content").toString();
            Gson gson = new Gson();
            Type type = new TypeToken<List<AccountInfoJson>>() {
            }.getType();
            List<AccountInfoJson> accounts = gson.fromJson(content, type);

            Log.i("tag",accounts.toString());
            Log.i("tag","加载测试");
            //显示账户列表
            showRecyclerView();

        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
    }

    @UiThread
    void showRecyclerView(){
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置适配器
        adapter = new AssetRecyclerAdapter(accounts,getActivity());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AssetRecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_container, new AssetImportCardFragment()).commit();
            }
        });
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}
