package team.wonderland.ucount.ucount_android.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import team.wonderland.ucount.ucount_android.R;
import team.wonderland.ucount.ucount_android.fragment.AssetFragment;

/**
 * Created by liuyu on 2017/8/19.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout mDrawerLayout = null;
    private TextView tabAsset;
    private TextView tabReport;
    private TextView tabPlan;
    private TextView tabMoney;

    private FrameLayout ly_content;

    private AssetFragment f1,f2,f3,f4;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            /**
             * 当抽屉滑动状态改变的时候被调用
             * 状态值是STATE_IDLE（闲置--0）, STATE_DRAGGING（拖拽的--1）, STATE_SETTLING（固定--2）中之一。
             * 抽屉打开的时候，点击抽屉，drawer的状态就会变成STATE_DRAGGING，然后变成STATE_IDLE
             */
            @Override
            public void onDrawerStateChanged(int arg0) {
                Log.i("drawer", "drawer的状态：" + arg0);
            }

            /**
             * 当抽屉被滑动的时候调用此方法
             * arg1 表示 滑动的幅度（0-1）
             */
            @Override
            public void onDrawerSlide(View arg0, float arg1) {
                Log.i("drawer", arg1 + "");
            }

            /**
             * 当一个抽屉被完全打开的时候被调用
             */
            @Override
            public void onDrawerOpened(View arg0) {
                Log.i("drawer", "抽屉被完全打开了！");
            }

            /**
             * 当一个抽屉完全关闭的时候调用此方法
             */
            @Override
            public void onDrawerClosed(View arg0) {
                Log.i("drawer", "抽屉被完全关闭了！");
            }
        });


        bindView();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDrawerLayout!=null){
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
                mDrawerLayout.closeDrawers();
            }else super.onBackPressed();
        }
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        tabAsset = (TextView)this.findViewById(R.id.txt_asset);
        tabReport = (TextView)this.findViewById(R.id.txt_report);
        tabPlan = (TextView)this.findViewById(R.id.txt_plan);
        tabMoney = (TextView)this.findViewById(R.id.txt_money);
        ly_content = (FrameLayout) findViewById(R.id.fragment_container);

        tabAsset.setOnClickListener(this);
        tabReport.setOnClickListener(this);
        tabPlan.setOnClickListener(this);
        tabMoney.setOnClickListener(this);

    }

    //重置所有文本的选中状态
    public void selected(){
        tabAsset.setSelected(false);
        tabReport.setSelected(false);
        tabPlan.setSelected(false);
        tabMoney.setSelected(false);
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }
        if(f4!=null){
            transaction.hide(f4);
        }
    }

    @Override
    public void onClick(View v) {
        //FragmentTransaction transaction = getFragmentManager().beginTransaction();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch(v.getId()){
            case R.id.txt_asset:
                selected();
                tabAsset.setSelected(true);
                if(f1==null){
                    f1 = new AssetFragment("第一个Fragment");
                    transaction.add(R.id.fragment_container,f1);
                }else{
                    transaction.show(f1);
                }
                break;

            case R.id.txt_report:
                selected();
                tabReport.setSelected(true);
                if(f2==null){
                    f2 = new AssetFragment("第二个Fragment");
                    transaction.add(R.id.fragment_container,f2);
                }else{
                    transaction.show(f2);
                }
                break;

            case R.id.txt_plan:
                selected();
                tabPlan.setSelected(true);
                if(f3==null){
                    f3 = new AssetFragment("第三个Fragment");
                    transaction.add(R.id.fragment_container,f3);
                }else{
                    transaction.show(f3);
                }
                break;

            case R.id.txt_money:
                selected();
                tabMoney.setSelected(true);
                if(f4==null){
                    f4 = new AssetFragment("第四个Fragment");
                    transaction.add(R.id.fragment_container,f4);
                }else{
                    transaction.show(f4);
                }
                break;
        }

        transaction.commit();
    }


}
