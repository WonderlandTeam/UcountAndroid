package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.service.AccountService;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;
/**
 * Created by liuyu on 2017/11/6.
 */
@EFragment
public class HomeFragment extends Fragment {

    @RestService
    AccountService accountService;

    private GridView gridView;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter adapter;
    private int[] icons = {R.mipmap.zhifubao, R.mipmap.gonghang, R.mipmap.xiaoyuan, R.mipmap.xianjin,
            R.mipmap.tubiao, R.mipmap.zichanfuzhai, R.mipmap.xianjinliuliang, R.mipmap.caiwufenxi,
            R.mipmap.yusuan, R.mipmap.jihua, R.mipmap.jianyi, R.mipmap.licai};
    private String[] text = {"支付宝明细", "工行卡明细", "校园卡明细", "现金明细", "收支盈余", "资产负债", "现金流量",
            "财务分析", "预算", "计划", "消费建议", "理财"};


    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);

        gridView = (GridView) view.findViewById(R.id.home_gridView);
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        //加载适配器
        String[] form = {"image", "text"};
        int[] to = {R.id.home_grid_item_image, R.id.home_grid_item_text};
        adapter = new SimpleAdapter(getActivity(), data_list, R.layout.home_grid_item, form, to);
        gridView.setAdapter(adapter);
        //监听item每一项
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "你点击了第" + i, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //准备数据源
    public List<Map<String, Object>> getData() {
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icons[i]);
            map.put("text", text[i]);
            data_list.add(map);
        }
        return data_list;
    }

}
