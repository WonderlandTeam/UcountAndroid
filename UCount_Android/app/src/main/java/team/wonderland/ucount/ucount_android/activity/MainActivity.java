package team.wonderland.ucount.ucount_android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
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
import team.wonderland.ucount.ucount_android.fragment.*;

import static team.wonderland.ucount.ucount_android.R.id.fragment_container;
import static team.wonderland.ucount.ucount_android.R.layout.activity_main;

/**
 * Created by liuyu on 2017/8/19.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static String PACKAGE_NAME;
    public static Resources resources;

    private DrawerLayout mDrawerLayout = null;
    private TextView tabAsset;
    private TextView tabReport;
    private TextView tabPlan;
    private TextView tabMoney;

    private Button quitButton;
    private Button bt_modifyPassword;
    private Button bt_post;
    private Button bt_reply;
    private Button bt_praise;
    private Button bt_collection;
    private Button bt_message;

    private SharedPreferences sp;

    private FrameLayout ly_content;

    private AssetFragment assetFragment;
    private ReportFragment reportFragment;
    private PlanFragment planFragment;
    private MoneyFragment moneyFragment;
    private FragmentTransaction transaction;

    private ModifyPasswordFragment modifyPasswordFragment;
    private MyPostsFragment myPostsFragment;
    private MyMessageFragment myMessageFragment;
    private MyCollectionFragment myCollectionFragment;
    private MyReplyFragment myReplyFragment;
    private MyPraiseFragment myPraiseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        sp = this.getSharedPreferences("userInfo",0);

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

        tabAsset.performClick();

        //个人中心按钮
        quitButton = (Button)findViewById(R.id.quit_btn);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳回到登录界面
                Intent intent=new Intent(MainActivity.this,LoginActivity_.class);
                startActivity(intent);

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USERNAME","");
                editor.putString("PASSWORD","");
                editor.putBoolean("HAVELOGINED",false);
                editor.commit();
            }
        });

        bt_modifyPassword= (Button) findViewById(R.id.password_more);
        bt_modifyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                modifyPasswordFragment = new ModifyPasswordFragment();
                transaction.add(fragment_container, modifyPasswordFragment);
                transaction.commit();
            }
        });

        bt_post= (Button) findViewById(R.id.post_more);
        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myPostsFragment=new MyPostsFragment();
                transaction.add(fragment_container, myPostsFragment);
                transaction.commit();
            }
        });

        bt_collection= (Button) findViewById(R.id.collection_more);
        bt_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myCollectionFragment=new MyCollectionFragment();
                transaction.add(fragment_container, myCollectionFragment);
                transaction.commit();
            }
        });

        bt_praise= (Button) findViewById(R.id.praise_more);
        bt_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myPraiseFragment=new MyPraiseFragment();
                transaction.add(fragment_container, myPraiseFragment);
                transaction.commit();
            }
        });

        bt_message= (Button) findViewById(R.id.message_more);
        bt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myMessageFragment=new MyMessageFragment();
                transaction.add(fragment_container, myMessageFragment);
                transaction.commit();
            }
        });

        bt_reply= (Button) findViewById(R.id.reply_more);
        bt_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View left_menu=findViewById(R.id.left_menu);
                mDrawerLayout.closeDrawer(left_menu);
                transaction = getSupportFragmentManager().beginTransaction();
                myReplyFragment=new MyReplyFragment();
                transaction.add(fragment_container, myReplyFragment);
                transaction.commit();
            }
        });

        // 获得包名和资源，方便后面的程序使用
        PACKAGE_NAME = getApplicationContext().getPackageName();
        resources = getResources();
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
        ly_content = (FrameLayout) findViewById(fragment_container);

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


    @Override
    public void onClick(View v) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch(v.getId()){
            case R.id.txt_asset:
                selected();
                tabAsset.setSelected(true);
                assetFragment = new AssetFragment();
                transaction.add(fragment_container, assetFragment);
                break;

            case R.id.txt_report:
                selected();
                tabReport.setSelected(true);
                reportFragment = new ReportFragment();
                transaction.add(fragment_container, reportFragment);
                break;

            case R.id.txt_plan:
                selected();
                tabPlan.setSelected(true);
                planFragment = new PlanFragment();
                transaction.add(fragment_container, planFragment);
                break;

            case R.id.txt_money:
                selected();
                tabMoney.setSelected(true);
                moneyFragment = new MoneyFragment();
                transaction.add(fragment_container, moneyFragment);
                break;
        }

        transaction.commit();
    }


}
