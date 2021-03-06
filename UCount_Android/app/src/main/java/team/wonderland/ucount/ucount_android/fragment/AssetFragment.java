package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
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

    private DotProgressBar dotProgressBar;
    String username;

    @RestService
    AccountService accountService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_fragment,container,false);
        txtDetail = (TextView)view.findViewById(R.id.asset_txt_detail);
        txtNew = (TextView)view.findViewById(R.id.asset_txt_new);
        txtTotal = (TextView)view.findViewById(R.id.asset_txt_totalnum);
        txtIn = (TextView)view.findViewById(R.id.asset_txt_innum);
        txtOut = (TextView)view.findViewById(R.id.asset_txt_outnum);
        recyclerView = (RecyclerView)view.findViewById(R.id.asset_recyclerview);
        dotProgressBar = view.findViewById(R.id.asset_dot_progress_bar);
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
                Bundle bundle = new Bundle();
                bundle.putString("accountType","total");
                Fragment fragment = new AssetDetailFragment_();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_container, fragment).commit();
            }
        });
        return view;
    }


    /**
     * 获得用户所有账户信息
     */
    @Background
    void initAsset() {
        showLoading();
        try {
            accounts = accountService.getAccountsByUser(username);
            //显示账户列表
            showRecyclerView();
        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
        hideLoading();
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
                Bundle bundle = new Bundle();
                bundle.putSerializable("account",accounts.get(position));
                bundle.putString("accountType",accounts.get(position).getType());
                Fragment fragment = new AssetDetailFragment_();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .add(R.id.fragment_container, fragment).commit();
            }

            @Override
            public void onItemLongOnClick(View view, int position) {
                showPopMenu(view,position);
            }
        });

        DecimalFormat df = new DecimalFormat("0.00");
        double income = 0;
        double outcome = 0;
        double balance = 0;
        //遍历获得所有账户的总支出和总收入
        for(int i=0;i<accounts.size();i++){
            income += accounts.get(i).getIncome();
            outcome += accounts.get(i).getExpend();
            balance += accounts.get(i).getBalance();
        }
        txtTotal.setText(String.valueOf(df.format(balance)));
        txtIn.setText(String.valueOf(df.format(income)));
        txtOut.setText(String.valueOf(df.format(outcome)));
    }

    @UiThread
    public void showPopMenu(View view,final int pos){
        PopupMenu popupMenu = new PopupMenu(getActivity(),view);
        popupMenu.getMenuInflater().inflate(R.menu.item_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                deleteAccount(accounts.get(pos).getAccountId());
                adapter.removeItem(pos);
                adapter.notifyItemChanged(pos);
                return false;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getActivity().getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    //删除账户
    @Background
    void deleteAccount(long id){
        try {
            accountService.deleteAccount(id);
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    //显示加载动画
    @UiThread
    void showLoading(){
        dotProgressBar.setVisibility(View.VISIBLE);
    }

    @UiThread
    void hideLoading(){
        dotProgressBar.setVisibility(View.INVISIBLE);
    }
}
