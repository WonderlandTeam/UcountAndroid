package team.wonderland.ucount.ucount_android.fragment;


import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import at.markushi.ui.CircleButton;
import team.wonderland.ucount.ucount_android.Adapter.AssetDetailRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.AccountInfoJson;
import team.wonderland.ucount.ucount_android.json.BillInfoJson;
import team.wonderland.ucount.ucount_android.json.BudgetInfoJson;
import team.wonderland.ucount.ucount_android.service.BillService;
import team.wonderland.ucount.ucount_android.util.EndLessOnScrollListener;
import team.wonderland.ucount.ucount_android.util.PercentageRing;

/**
 * Created by liuyu on 2017/8/30.
 */

@EFragment(R.layout.asset_cash_detail_fragment)
public class AssetDetailFragment extends Fragment {
    private ImageView back;
    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private int page = 0;
    private AssetDetailRecyclerAdapter adapter;
    private List<BillInfoJson> assetItems;
    private List<BillInfoJson> newassetItems;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Long accountID = 1l;
    private String accountType = "";
    private String username = "";
    private AccountInfoJson accountInfoJson=null;

    private TextView tv_in;
    private TextView tv_out;
    private TextView tv_in_title;
    private TextView tv_out_title;

    private boolean isTotalBill = false;
    private boolean canLoadMore = true;

    private final int ITEMS = 10;

    @RestService
    BillService billService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_cash_detail_fragment, container, false);
        back = (ImageView)view.findViewById(R.id.asset_cash_detail_back);
        add = (FloatingActionButton) view.findViewById(R.id.asset_cash_detail_bt_add);
        tv_in = view.findViewById(R.id.asset_cash_detail_txt_in);
        tv_out = view.findViewById(R.id.asset_cash_detail_txt_out);
        tv_in_title = view.findViewById(R.id.asset_cash_detail_txt_intitle);
        tv_out_title = view.findViewById(R.id.asset_cash_detail_txt_outtitle);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.asset_cash_detail_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.asset_cash_detail_refresh);


        accountType = (String)this.getArguments().get("accountType");


        if(!accountType.equals("现金")){
            add.setVisibility(View.INVISIBLE);
        }

        username = getActivity().getSharedPreferences("user", 0).getString("USERNAME", "");
        if(accountType.equals("total")){
            isTotalBill = true;

            tv_in.setText("");
            tv_out.setText("");
            tv_in_title.setText((Calendar.getInstance().getTime().getMonth()+1)+"月账目");
            tv_out_title.setText("");
            initTotalBillDetail();
        }else {
            accountInfoJson = (AccountInfoJson) this.getArguments().get("account");

            accountID = accountInfoJson.getAccountId();
            DecimalFormat df = new DecimalFormat("0.00");
            tv_in.setText(df.format(accountInfoJson.getIncome()));
            tv_out.setText(df.format(accountInfoJson.getExpend()));
            initBillDetail();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong("account",accountID);
                Fragment fragment = new AssetAddFragment_();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_container, fragment).commit();
            }
        });

        return view;
    }


    @Background
    public void initBillDetail(){
        try {
            assetItems = billService.getBillsByAccount(accountID,0,ITEMS,"id","ASC");
            Log.i("AssetDetailFragment",assetItems.toString());
            if(assetItems.size()<ITEMS){
                canLoadMore = false;
            }
            initRecyclerView();
 //           billService.getBillsByAccount(accountID,);
//            Map<String, Object> result = billService.
//            String content = result.get("content").toString();
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<BillInfoJson>>() {
//            }.getType();
        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
    }

    @Background
    public void initTotalBillDetail(){
        try {
            assetItems = billService.getBillsByUser(username);
            if(assetItems.size()<ITEMS){
                canLoadMore = false;
            }
            initRecyclerView();
        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
    }

    @UiThread
    void initRecyclerView(){
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        adapter = new AssetDetailRecyclerAdapter(assetItems,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyItemDecoration());

        swipeRefreshLayout.setColorSchemeResources(
                R.color.text_green,
                R.color.text_green_darker
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //重新获取完网络数据刷新Adapter，完成后需要调用onRefreshComplete方法取消滑出来的圆形进度
                //initBillDetail();
//                assetItems.add(new BillInfoJson(1l,"理财收入","天弘基金管理有限公司",0.24,"2017-09-06 07:22:36"));
//                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        add.attachToRecyclerView(recyclerView);

        //加载更多
        if(canLoadMore) {
            recyclerView.addOnScrollListener(new EndLessOnScrollListener(mLinearLayoutManager) {
                @Override
                public void onLoadMore(int currentPage) {
                    loadMoreData();
                }
            });
        }

        if(accountInfoJson!=null) {
            adapter.setOnItemClickListener(new AssetDetailRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                }

                //长按删除
                @Override
                public void onItemLongOnClick(View view, int position) {
                    showPopMenu(view, position);
                }
            });
        }
    }

    @Background
    //每次上拉加载的时候，给RecyclerView的后面添加了20条数据数据
    void loadMoreData(){
        try {
            page ++;
            if(isTotalBill){
                newassetItems=billService.getBillsByUser(username);
            }else {
                newassetItems=billService.getBillsByAccount(accountID, page, ITEMS, "id", "ASC");
            }
            if(newassetItems.size()<ITEMS){
                canLoadMore = false;
            }
            assetItems.addAll(newassetItems);
            loadMoreDataSuccess();
        } catch (ResponseException e) {
            showErrorInfo(e.getMessage());
        }
    }

    @UiThread
    void loadMoreDataSuccess(){
        adapter.notifyDataSetChanged();
    }

    //显示错误信息
    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    public void showPopMenu(View view,final int pos){
        PopupMenu popupMenu = new PopupMenu(getActivity(),view);
        popupMenu.getMenuInflater().inflate(R.menu.item_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                deleteAccountItem(assetItems.get(pos).getBillId());
                adapter.removeItem(pos);
                adapter.notifyItemChanged(pos);
                return false;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
            }
        });
        popupMenu.show();
    }

    //删除账户
    @Background
    void deleteAccountItem(long id){
        try {
            billService.deleteBill(accountInfoJson.getAccountId(),id);
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }
    }
}
