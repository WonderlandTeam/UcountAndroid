package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.PlanTaskRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.util.PercentageRing;
import team.wonderland.ucount.ucount_android.util.Task;

/**
 * Created by liuyu on 2017/9/2.
 */

public class PlanTaskFragment extends Fragment {

    private RecyclerView recyclerView;
    private PlanTaskRecyclerAdapter adapter;
    private List<Task> tasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_task_fragment, container, false);

        initData();

        recyclerView = view.findViewById(R.id.plan_task_recyclerview);

        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置适配器
        adapter = new PlanTaskRecyclerAdapter(tasks,getActivity());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PlanTaskRecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.plan_fragment_container, new PlanTaskDetailFragment())
                        .commit();
            }
        });
        return view;
    }

    public void initData(){
        //TODO: 获得所有任务 TaskService.getTasksByUser
        tasks = new ArrayList<>();
        tasks.add(new Task("买一双鞋","2017-12-31",2000,340,30));
    }

}
