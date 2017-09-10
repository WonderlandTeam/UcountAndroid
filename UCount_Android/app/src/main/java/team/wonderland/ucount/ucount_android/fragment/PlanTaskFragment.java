package team.wonderland.ucount.ucount_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ServiceCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import team.wonderland.ucount.ucount_android.Adapter.AssetRecyclerAdapter;
import team.wonderland.ucount.ucount_android.Adapter.PlanTaskRecyclerAdapter;
import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.exception.ResponseException;
import team.wonderland.ucount.ucount_android.json.TaskInfoJson;
import team.wonderland.ucount.ucount_android.service.TaskService;
import team.wonderland.ucount.ucount_android.util.PercentageRing;
import team.wonderland.ucount.ucount_android.util.Task;

/**
 * Created by liuyu on 2017/9/2.
 */
@EFragment
public class PlanTaskFragment extends Fragment {

    private RecyclerView recyclerView;
    private PlanTaskRecyclerAdapter adapter;
    private List<TaskInfoJson> tasks;
    private FloatingActionButton newTask;

    private String username;

    @RestService
    TaskService taskService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_task_fragment, container, false);

        recyclerView = view.findViewById(R.id.plan_task_recyclerview);

        newTask = view.findViewById(R.id.plan_task_bt_add);
        newTask.attachToRecyclerView(recyclerView);
        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.plan_fragment_container, new PlanTaskNewFragment_())
                        .commit();
            }
        });

        username = getActivity().getSharedPreferences("user",0).getString("USERNAME","");
        initData();

        return view;
    }

    @Background
    public void initData(){
        try {
            tasks = taskService.getTasksByUser(username);
            initRecyclerView();
        }catch(ResponseException e){
            showErrorInfo(e.getMessage());
        }

    }

    @UiThread
    public void initRecyclerView(){
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
    }

    @UiThread
    void showErrorInfo(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}
